package com.rfo.BASICLauncher;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.rfo.BASICLauncher.Run;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/* loaded from: classes.dex */
public class Basic extends Activity {
    public static final String DATABASES_DIR = "databases";
    public static final String DATA_DIR = "data";
    private static final String LOGTAG = "Basic";
    public static final int MANIFEST_PERMISSION_REQUEST = 0;
    public static final String SAMPLES_DIR = "Sample_Programs";
    public static final String SOURCE_DIR = "source";
    public static final String SOURCE_SAMPLES_PATH = "source/Sample_Programs";
    private static boolean apkCreateDataBaseDir;
    private static boolean apkCreateDataDir;
    public static TextStyle defaultTextStyle;
    public static ArrayList<Run.ProgramLine> lines;
    public static String mDefaultFirstLine;
    private Dialog mProgressDialog;
    private TextView mProgressText;
    private ImageView mSplash;
    public static String AppPath = "rfo-basic";
    public static boolean isAPK = false;
    public static boolean DoAutoRun = false;
    private static String filePath = "";
    private static String basePath = "";
    public static ContextManager mContextMgr = null;
    public static String mBasicPackage = "";

    public static boolean checkSDCARD(char mount) {
        String state = Environment.getExternalStorageState();
        if ("mounted".equals(state)) {
            return true;
        }
        if (!"mounted_ro".equals(state) || mount != 'r') {
            return false;
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] != 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_permission_denied), 0).show();
                    finish();
                    return;
                } else if (isAPK) {
                    createForAPK();
                    return;
                } else {
                    createForSB();
                    return;
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                return;
        }
    }

    public static String[] getPermissionsFromManifest(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("This should have never happened.", e);
        }
    }

    public static void setFilePaths(String basePath2) {
        if (basePath2.equals("none")) {
            basePath2 = Environment.getExternalStorageDirectory().getPath();
        }
        basePath = basePath2;
        filePath = new File(basePath2, AppPath).getPath();
    }

    public static String getBasePath() {
        return basePath;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static String getFilePath(String subdir, String subPath) {
        StringBuilder bldPath = new StringBuilder(filePath);
        if (subdir != null) {
            bldPath.append(File.separatorChar).append(subdir);
        }
        if (subPath != null) {
            bldPath.append(File.separatorChar).append(subPath);
        }
        File file = new File(bldPath.toString());
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return file.getAbsolutePath();
        }
    }

    public static String getSourcePath(String subPath) {
        return getFilePath(SOURCE_DIR, subPath);
    }

    public static String getSamplesPath(String subPath) {
        return getFilePath(SOURCE_SAMPLES_PATH, subPath);
    }

    public static String getDataPath(String subPath) {
        return getFilePath(DATA_DIR, subPath);
    }

    public static String getDataBasePath(String subPath) {
        return getFilePath(DATABASES_DIR, subPath);
    }

    public static String getAppFilePath(String subdir, String subPath) {
        StringBuilder sb = new StringBuilder(AppPath);
        if (subdir != null) {
            sb.append(File.separatorChar).append(subdir);
        }
        if (subPath != null) {
            sb.append(File.separatorChar).append(subPath);
        }
        String path = sb.toString();
        try {
            return new File(path.toString()).getCanonicalPath().substring(1);
        } catch (IOException e) {
            Log.w("Basic", "getAppFilePath - getCanonicalPath: " + e);
            return path;
        }
    }

    public static ContextManager getContextManager() {
        return mContextMgr;
    }

    public static void clearContextManager() {
        mContextMgr.clearProgramContexts();
    }

    private void initVars() {
        Context appContext = getApplicationContext();
        mContextMgr = new ContextManager(appContext);
        mBasicPackage = appContext.getPackageName();
        Resources res = getResources();
        AppPath = res.getString(R.string.app_path);
        isAPK = res.getBoolean(R.bool.is_apk);
        apkCreateDataDir = res.getBoolean(R.bool.apk_create_data_dir);
        apkCreateDataBaseDir = res.getBoolean(R.bool.apk_create_database_dir);
        DoAutoRun = false;
        mDefaultFirstLine = getString(R.string.display_text_default_first_line);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Basic", "onCreate: " + this);
        initVars();
        Settings.setDefaultValues(this, isAPK);
        defaultTextStyle = new TextStyle();
        setFilePaths(Settings.getBaseDrive(this));
        if (Build.VERSION.SDK_INT >= 23 && !permissionsGranted()) {
            return;
        }
        if (isAPK) {
            createForAPK();
        } else {
            createForSB();
        }
    }

    private boolean permissionsGranted() {
        String[] manifestPermissions = getPermissionsFromManifest(getApplicationContext());
        for (String perm : manifestPermissions) {
            if (!perm.equals("android.permission.ACCESS_MOCK_LOCATION") && ContextCompat.checkSelfPermission(this, perm) != 0) {
                ActivityCompat.requestPermissions(this, manifestPermissions, 0);
                return false;
            }
        }
        return true;
    }

    private void createForSB() {
        if (mContextMgr.getContext(2) != null) {
            finish();
            return;
        }
        InitDirs();
        clearProgram();
        Intent myIntent = getIntent();
        String FileName = myIntent.getStringExtra(LauncherShortcuts.EXTRA_LS_FILENAME);
        Bundle savedState = myIntent.getBundleExtra(Editor.EXTRA_RESTART);
        if (FileName == null && myIntent.getData() != null) {
            FileName = myIntent.getData().getPath();
        }
        if (FileName != null && !FileName.equals("")) {
            Intent intent = new AutoRun(this, FileName, false, null).load();
            DoAutoRun = true;
            startActivity(intent);
            finish();
        } else if (AreSamplesLoaded()) {
            DoAutoRun = false;
            Intent intent2 = new Intent(this, Editor.class);
            if (savedState != null) {
                intent2.putExtra(Editor.EXTRA_RESTART, savedState);
            }
            startActivity(intent2);
            finish();
        } else {
            runBackgroundLoader();
        }
    }

    private void createForAPK() {
        String ArgPath = "";
        Intent myIntent = getIntent();
        if (myIntent.getData() != null) {
            ArgPath = myIntent.getData().getPath();
        }
        if (!ArgPath.equals("")) {
            Run.called_with = getRelativePath(ArgPath, getDataPath(null));
        }
        runBackgroundLoader();
    }

    private void runBackgroundLoader() {
        int id;
        setContentView(R.layout.splash);
        this.mSplash = (ImageView) findViewById(R.id.splash);
        Resources res = getResources();
        boolean displaySplash = res.getBoolean(R.bool.splash_display);
        int imageID = R.drawable.blank;
        if (displaySplash && (id = res.getIdentifier("splash", "drawable", getPackageName())) > 0) {
            imageID = id;
        }
        this.mSplash.setImageResource(imageID);
        this.mProgressText = new TextView(this);
        this.mProgressDialog = new AlertDialog.Builder(this).setView(this.mProgressText).setCancelable(false).setIcon(R.drawable.icon).create();
        new Loader().execute("");
    }

    public static void InitDirs() {
        if (checkSDCARD('w')) {
            if (!isAPK) {
                new File(getSourcePath(null)).mkdirs();
                new File(getSamplesPath(null)).mkdirs();
                new File(getDataPath(null)).mkdirs();
                new File(getDataBasePath(null)).mkdirs();
            }
            if (isAPK && apkCreateDataDir) {
                new File(getDataPath(null)).mkdirs();
            }
            if (isAPK && apkCreateDataBaseDir) {
                new File(getDataBasePath(null)).mkdirs();
            }
        }
    }

    public static void clearProgram() {
        Run.running_bas = "";
        lines = new ArrayList<>();
        lines.add(new Run.ProgramLine(""));
        Editor.DisplayText = mDefaultFirstLine;
    }

    private static boolean AreSamplesLoaded() {
        String samplesPath = getSamplesPath(null);
        File sdDir = new File(samplesPath);
        sdDir.mkdirs();
        String[] FL = sdDir.list();
        if (!(FL == null || FL.length == 0)) {
            ArrayList<String> FL1 = new ArrayList<>(Arrays.asList(FL));
            Collections.sort(FL1);
            String f0 = FL1.get(0);
            if (f0.length() > 11) {
                String[] f = f0.substring(5).split("_");
                if (f.length > 1 && mContextMgr.getContext(1).getString(R.string.version).substring(0, 5).equals(f[0] + "." + f[1])) {
                    return true;
                }
            }
            int length = FL.length;
            for (int i = 0; i < length; i++) {
                new File(samplesPath + File.separatorChar + FL[i]).delete();
            }
        }
        return false;
    }

    public static String getRawFileName(String input) {
        if (input == null) {
            return "";
        }
        String output = input.toLowerCase(Locale.getDefault());
        int index = output.indexOf(".");
        return index != -1 ? output.substring(0, index) : output;
    }

    public static String getAlternateRawFileName(String input) {
        Locale locale = Locale.getDefault();
        int idx = input.lastIndexOf("/");
        if (idx >= 0) {
            return input.substring(idx + 1).toLowerCase(locale).replace(".", "_");
        }
        return input.toLowerCase(locale).replace(".", "_");
    }

    public static int getRawResourceID(String fileName) {
        String rawFileName;
        if (fileName == null) {
            fileName = "";
        }
        int resID = 0;
        int attempt = 1;
        while (resID == 0 && attempt <= 2) {
            if (attempt == 1) {
                rawFileName = getAlternateRawFileName(fileName);
            } else {
                rawFileName = attempt == 2 ? getRawFileName(fileName) : "";
            }
            if (!rawFileName.equals("")) {
                resID = mContextMgr.getContext(1).getResources().getIdentifier(mBasicPackage + ":raw/" + rawFileName, null, null);
            }
            attempt++;
        }
        return resID;
    }

    public static InputStream streamFromResource(String dir, String path) throws Exception {
        Context appContext = mContextMgr.getContext(1);
        int resID = getRawResourceID(path);
        if (resID != 0) {
            return appContext.getResources().openRawResource(resID);
        }
        return appContext.getAssets().open(getAppFilePath(dir, path));
    }

    public static BufferedReader getBufferedReader(String dir, String path, boolean enableDecryption) throws Exception {
        String filePath2;
        InputStream inputStream;
        if (dir == null) {
            filePath2 = path;
        } else {
            filePath2 = getFilePath(dir, path);
        }
        File file = new File(filePath2);
        if (file.exists()) {
            return new BufferedReader(new FileReader(file));
        }
        if (!isAPK || (inputStream = streamFromResource(dir, path)) == null) {
            return null;
        }
        if (mContextMgr.getContext(1).getResources().getBoolean(R.bool.apk_programs_encrypted) && enableDecryption) {
            inputStream = getDecryptedStream(inputStream);
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static BufferedInputStream getBufferedInputStream(String dir, String path) throws Exception {
        String filePath2;
        InputStream inputStream;
        if (dir == null) {
            filePath2 = path;
        } else {
            filePath2 = getFilePath(dir, path);
        }
        File file = new File(filePath2);
        if (file.exists()) {
            return new BufferedInputStream(new FileInputStream(file));
        }
        if (!isAPK || (inputStream = streamFromResource(dir, path)) == null) {
            return null;
        }
        return new BufferedInputStream(inputStream);
    }

    public static InputStream getDecryptedStream(InputStream inputStream) throws Exception {
        return new CipherInputStream(inputStream, new Encryption(2, mBasicPackage).cipher());
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x008b, code lost:
        return r16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getRelativePath(java.lang.String r16, java.lang.String r17) {
        /*
            r9 = r16
            r8 = r17
            java.io.File r13 = new java.io.File     // Catch: IOException -> 0x006f
            r0 = r16
            r13.<init>(r0)     // Catch: IOException -> 0x006f
            java.lang.String r13 = r13.getCanonicalPath()     // Catch: IOException -> 0x006f
            java.lang.String r14 = "/$"
            java.lang.String r15 = ""
            java.lang.String r9 = r13.replaceAll(r14, r15)     // Catch: IOException -> 0x006f
            java.io.File r13 = new java.io.File     // Catch: IOException -> 0x006f
            r0 = r17
            r13.<init>(r0)     // Catch: IOException -> 0x006f
            java.lang.String r13 = r13.getCanonicalPath()     // Catch: IOException -> 0x006f
            java.lang.String r14 = "/$"
            java.lang.String r15 = ""
            java.lang.String r8 = r13.replaceAll(r14, r15)     // Catch: IOException -> 0x006f
        L_0x002a:
            java.lang.String r13 = "/"
            java.lang.String r13 = java.util.regex.Pattern.quote(r13)
            java.lang.String[] r1 = r8.split(r13)
            java.lang.String r13 = "/"
            java.lang.String r13 = java.util.regex.Pattern.quote(r13)
            java.lang.String[] r12 = r9.split(r13)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = 0
        L_0x0044:
            int r13 = r12.length
            if (r5 >= r13) goto L_0x0089
            int r13 = r1.length
            if (r5 >= r13) goto L_0x0089
            r13 = r12[r5]
            r14 = r1[r5]
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0089
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r14 = r12[r5]
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = "/"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r4.append(r13)
            int r5 = r5 + 1
            goto L_0x0044
        L_0x006f:
            r6 = move-exception
            java.lang.String r13 = "Basic"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "getRelativePath - getCanonicalPath: "
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.StringBuilder r14 = r14.append(r6)
            java.lang.String r14 = r14.toString()
            android.util.Log.w(r13, r14)
            goto L_0x002a
        L_0x0089:
            if (r5 != 0) goto L_0x008c
        L_0x008b:
            return r16
        L_0x008c:
            r2 = 1
            java.io.File r3 = new java.io.File
            r3.<init>(r8)
            boolean r13 = r3.exists()
            if (r13 == 0) goto L_0x00b5
            boolean r2 = r3.isFile()
        L_0x009c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            int r13 = r1.length
            if (r13 == r5) goto L_0x00c5
            if (r2 == 0) goto L_0x00c1
            int r13 = r1.length
            int r13 = r13 - r5
            int r10 = r13 + -1
        L_0x00aa:
            r7 = 0
        L_0x00ab:
            if (r7 >= r10) goto L_0x00c5
            java.lang.String r13 = "../"
            r11.append(r13)
            int r7 = r7 + 1
            goto L_0x00ab
        L_0x00b5:
            java.lang.String r13 = "/"
            r0 = r17
            boolean r13 = r0.endsWith(r13)
            if (r13 == 0) goto L_0x009c
            r2 = 0
            goto L_0x009c
        L_0x00c1:
            int r13 = r1.length
            int r10 = r13 - r5
            goto L_0x00aa
        L_0x00c5:
            int r13 = r9.length()
            int r14 = r4.length()
            if (r13 <= r14) goto L_0x00da
            int r13 = r4.length()
            java.lang.String r13 = r9.substring(r13)
            r11.append(r13)
        L_0x00da:
            java.lang.String r16 = r11.toString()
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rfo.BASICLauncher.Basic.getRelativePath(java.lang.String, java.lang.String):java.lang.String");
    }

    public static int loadProgramFileToList(boolean isFullPath, String path, ArrayList<String> list) {
        String data;
        int size = 0;
        try {
            BufferedReader buf = getBufferedReader(isFullPath ? null : SOURCE_DIR, path, true);
            if (!isFullPath) {
                path = getSourcePath(path);
            }
            Run.running_bas = getRelativePath(path, getSourcePath(null));
            do {
                try {
                    data = buf.readLine();
                } catch (IOException e) {
                    data = null;
                }
                if (data != null) {
                    list.add(data);
                    size += data.length() + 1;
                    continue;
                }
            } while (data != null);
            if (list.isEmpty()) {
                list.add("!");
                size = 2;
            }
            return size;
        } catch (Exception e2) {
            return 0;
        }
    }

    public static String loadProgramListToString(ArrayList<String> list, int size) {
        StringBuilder sb = new StringBuilder(size);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next() + '\n');
        }
        return sb.toString();
    }

    public static void loadProgramFromString(String text, AddProgramLine APL) {
        if (APL == null) {
            APL = new AddProgramLine();
        }
        StringBuilder sb = new StringBuilder();
        int length = text.length();
        int offset = AddProgramLine.charCount;
        for (int k = 0; k < length; k++) {
            char c = text.charAt(k);
            if (c == '\n') {
                AddProgramLine.charCount = k + offset;
                APL.AddLine(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (sb.length() != 0) {
            AddProgramLine.charCount = length + offset;
            APL.AddLine(sb.toString());
        }
    }

    public static IOException closeStreams(InputStream in, OutputStream out) {
        IOException ex = null;
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                ex = e;
            }
        }
        if (out == null) {
            return ex;
        }
        try {
            out.flush();
            out.close();
            return ex;
        } catch (IOException e2) {
            if (ex == null) {
                return e2;
            }
            return ex;
        }
    }

    public static IOException closeStreams(Reader in, Writer out) {
        IOException ex = null;
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                ex = e;
            }
        }
        if (out == null) {
            return ex;
        }
        try {
            out.flush();
            out.close();
            return ex;
        } catch (IOException e2) {
            if (ex == null) {
                return e2;
            }
            return ex;
        }
    }

    /* loaded from: classes.dex */
    public class Loader extends AsyncTask<String, String, String> {
        private boolean mDisplayProgress;
        private String mProgressMarker;
        private Resources mRes;
        private int mUpdates;
        private int maxUpdateCount = 0;

        public Loader() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.mRes = Basic.this.getResources();
            String[] loadingMsg = this.mRes.getStringArray(R.array.loading_msg);
            this.mProgressMarker = this.mRes.getString(R.string.progress_marker);
            this.mDisplayProgress = (loadingMsg == null || loadingMsg.length == 0) ? false : true;
            if (this.mDisplayProgress) {
                String title = loadingMsg[0];
                int last = loadingMsg.length - 1;
                for (int m = 1; m < last; m++) {
                    title = title + '\n' + loadingMsg[m];
                }
                if (last > 0 && loadingMsg[last].length() > 0) {
                    title = title + '\n' + loadingMsg[last];
                }
                Basic.this.mProgressDialog.setTitle(title);
                Basic.this.mProgressDialog.show();
                Basic.this.mProgressText.setText("");
                this.mUpdates = 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onProgressUpdate(String... str) {
            if (this.mDisplayProgress) {
                this.mUpdates++;
                int length = str.length;
                for (int i = 0; i < length; i++) {
                    Basic.this.mProgressText.setText(((Object) Basic.this.mProgressText.getText()) + str[i]);
                }
                if (Basic.this.mProgressText.getHeight() > (Basic.this.mSplash.getHeight() * 2) / 3) {
                    StringBuilder msg = new StringBuilder("Continuing load (");
                    if (this.maxUpdateCount != 0) {
                        msg.append((this.mUpdates * 100) / this.maxUpdateCount);
                        msg.append(Format.COMMENT);
                    } else {
                        msg.append(this.mUpdates);
                    }
                    msg.append(")\n");
                    Basic.this.mProgressText.setText(msg);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String doInBackground(String... str) {
            Basic.this.startActivity(Basic.isAPK ? doBGforAPK() : doBGforSB());
            if (Basic.this.mProgressDialog != null) {
                Basic.this.mProgressDialog.dismiss();
            }
            Basic.this.finish();
            return "";
        }

        private Intent doBGforSB() {
            if (new File(Basic.getFilePath()).exists()) {
                copyAssets(Basic.AppPath);
                doFirstLoad();
            } else {
                doCantLoad();
            }
            Basic.DoAutoRun = false;
            Intent intent = new Intent(Basic.this, Editor.class);
            intent.putExtra(Editor.EXTRA_LOADPATH, Basic.SAMPLES_DIR);
            return intent;
        }

        private Intent doBGforAPK() {
            int delay;
            long startTime = System.currentTimeMillis();
            Basic.InitDirs();
            LoadGraphicsForAPK();
            Basic.lines = new ArrayList<>();
            LoadTheProgram();
            if (this.mRes.getBoolean(R.bool.splash_display) && (delay = this.mRes.getInteger(R.integer.splash_time) - ((int) (System.currentTimeMillis() - startTime))) > 1) {
                try {
                    Thread.sleep((long) delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Basic.DoAutoRun = true;
            return new Intent(Basic.this, Run.class);
        }

        private void copyAssets(String path) {
            String[] dirs = null;
            try {
                dirs = Basic.this.getAssets().list(path);
            } catch (IOException e) {
                Log.e("Basic", "copyAssets: " + e);
            }
            if (dirs != null) {
                if (dirs.length == 0) {
                    copyAssetToFile(path);
                    return;
                }
                new File(Basic.getBasePath() + File.separatorChar + path).mkdirs();
                int length = dirs.length;
                for (int i = 0; i < length; i++) {
                    copyAssets(path + File.separatorChar + dirs[i]);
                }
            }
        }

        private void copyAssetToFile(String path) {
            if (path.endsWith(".bas") || path.endsWith(".txt") || path.endsWith(".html")) {
                copyTextAssetToFile(path);
            } else {
                copyBinaryAssetToFile(path);
            }
        }

        private void copyTextAssetToFile(String path) {
            BufferedReader in = null;
            BufferedWriter out = null;
            publishProgress(this.mProgressMarker);
            File file = new File(Basic.getBasePath() + File.separatorChar + path);
            try {
                BufferedReader in2 = new BufferedReader(new InputStreamReader(Basic.this.getAssets().open(path)));
                try {
                    BufferedWriter out2 = new BufferedWriter(new FileWriter(file));
                    while (true) {
                        try {
                            String line = in2.readLine();
                            if (line == null) {
                                break;
                            }
                            out2.write(line + "\n");
                        } catch (IOException e) {
                            e = e;
                            out = out2;
                            in = in2;
                            Log.w("Basic", "copyTextAssetToFile: " + e);
                            Basic.closeStreams(in, out);
                        }
                    }
                    out = out2;
                    in = in2;
                } catch (IOException e2) {
                    e = e2;
                    in = in2;
                }
            } catch (IOException e3) {
                e = e3;
            }
            Basic.closeStreams(in, out);
        }

        private void copyBinaryAssetToFile(String path) {
            IOException e;
            int count;
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            File file = new File(Basic.getBasePath() + File.separatorChar + path);
            byte[] bytes = new byte[8192];
            try {
                BufferedInputStream in2 = new BufferedInputStream(Basic.this.getAssets().open(path));
                try {
                    BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(file));
                    do {
                        try {
                            count = in2.read(bytes, 0, 8192);
                            if (count > 0) {
                                out2.write(bytes, 0, count);
                                publishProgress(this.mProgressMarker);
                            }
                        } catch (IOException e2) {
                            e = e2;
                            out = out2;
                            in = in2;
                            Log.w("Basic", "copyBinaryAssetToFile: " + e);
                            Basic.closeStreams(in, out);
                        }
                    } while (count != -1);
                    out = out2;
                    in = in2;
                } catch (IOException e3) {
                    e = e3;
                    in = in2;
                }
            } catch (IOException e4) {
                e = e4;
            }
            Basic.closeStreams(in, out);
        }

        private void LoadGraphicsForAPK() {
            String[] loadFileNames = this.mRes.getStringArray(R.array.load_file_names);
            for (String fileName : loadFileNames) {
                if (!fileName.equals("")) {
                    Load1Graphic(fileName.endsWith(".db") ? Basic.DATABASES_DIR : Basic.DATA_DIR, fileName);
                }
            }
        }

        private void Load1Graphic(String dir, String fileName) {
            Exception e;
            int count;
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            File file = new File(Basic.getFilePath(dir, fileName));
            File parent = new File(file.getParent());
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (!parent.exists()) {
                Log.w("Basic", "Load1Graphic: can not create directory " + file.getPath());
                return;
            }
            byte[] bytes = new byte[8192];
            try {
                BufferedInputStream in2 = new BufferedInputStream(Basic.streamFromResource(dir, fileName), 8192);
                try {
                    BufferedOutputStream out2 = new BufferedOutputStream(new FileOutputStream(file));
                    do {
                        try {
                            count = in2.read(bytes, 0, 8192);
                            if (count > 0) {
                                out2.write(bytes, 0, count);
                                publishProgress(this.mProgressMarker);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            out = out2;
                            in = in2;
                            Log.w("Basic", "Load1Graphic: " + e);
                            Basic.closeStreams(in, out);
                        }
                    } while (count != -1);
                    out = out2;
                    in = in2;
                } catch (Exception e3) {
                    e = e3;
                    in = in2;
                }
            } catch (Exception e4) {
                e = e4;
            }
            Basic.closeStreams(in, out);
        }

        public void doFirstLoad() {
            String initialInfoText = Basic.this.getString(R.string.display_text_initial_info);
            String initialInfoAddendum = Basic.this.getString(R.string.display_text_addendum_dont_keep);
            String openComment = Basic.this.getString(R.string.display_text_open_comment);
            String closeComment = Basic.this.getString(R.string.display_text_close_comment);
            int level = Build.VERSION.SDK_INT;
            StringBuilder append = new StringBuilder().append(openComment).append(initialInfoText);
            if (level < 11) {
                initialInfoAddendum = "";
            }
            Editor.DisplayText = append.append(initialInfoAddendum).append(closeComment).toString();
        }

        public void doCantLoad() {
            Editor.DisplayText = Basic.this.getString(R.string.display_text_open_comment) + Basic.this.getString(R.string.display_text_cant_load) + Basic.this.getString(R.string.display_text_close_comment);
        }

        private void LoadTheProgram() {
            AddProgramLine APL = new AddProgramLine();
            try {
                InputStream inputStream = Basic.streamFromResource(Basic.SOURCE_DIR, this.mRes.getString(R.string.my_program));
                if (inputStream != null && this.mRes.getBoolean(R.bool.apk_programs_encrypted)) {
                    inputStream = Basic.getDecryptedStream(inputStream);
                }
                BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputStream));
                int count = 0;
                while (true) {
                    try {
                        try {
                            String line = buffreader.readLine();
                            if (line == null) {
                                break;
                            }
                            APL.AddLine(line);
                            count++;
                            if (count >= 200) {
                                publishProgress(this.mProgressMarker);
                                count = 0;
                            }
                        } catch (IOException e) {
                            Log.e("Basic", "LoadTheProgram - error reading, about line " + count + ": " + e);
                            if (buffreader != null) {
                                try {
                                    buffreader.close();
                                    return;
                                } catch (IOException e2) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        if (buffreader != null) {
                            try {
                                buffreader.close();
                            } catch (IOException e3) {
                            }
                        }
                        throw th;
                    }
                }
                if (buffreader != null) {
                    try {
                        buffreader.close();
                    } catch (IOException e4) {
                    }
                }
            } catch (Exception ex) {
                Log.e("Basic", "LoadTheProgram - error getting stream from resource: " + ex);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class TextStyle {
        public int mBackgroundColor;
        public int mHighlightColor;
        public int mLineColor;
        public float mSize;
        public int mTextColor;
        public Typeface mTypeface;

        public TextStyle() {
            refresh();
        }

        public TextStyle(TextStyle from, Typeface typeface) {
            this(from);
            this.mTypeface = typeface;
        }

        public TextStyle(TextStyle from) {
            this(from.mTextColor, from.mBackgroundColor, from.mLineColor, from.mHighlightColor, from.mSize, from.mTypeface);
        }

        public TextStyle(int fg, int bg, int lc, int hl, float size, Typeface typeface) {
            this.mTextColor = fg;
            this.mBackgroundColor = bg;
            this.mLineColor = lc;
            this.mHighlightColor = hl;
            this.mSize = size;
            this.mTypeface = typeface;
        }

        public void refresh() {
            Context appContext = Basic.mContextMgr.getContext(1);
            getScreenColors(appContext);
            this.mSize = Settings.getFont(appContext);
            this.mTypeface = Settings.getConsoleTypeface(appContext);
        }

        public boolean getCustomColors(Context appContext, int[] colors) {
            boolean useCustom = Settings.useCustomColors(appContext);
            if (useCustom) {
                String[] prefs = Settings.getCustomColors(appContext);
                for (int i = 0; i < 4; i++) {
                    String pref = prefs[i].trim().replace("0x", "#");
                    if (!pref.contains("#")) {
                        pref = "#" + pref;
                    }
                    try {
                        colors[i] = Color.parseColor(pref);
                    } catch (IllegalArgumentException e) {
                        Log.d("Basic", "getPrefColors: failed to parse <" + pref + ">");
                    }
                }
            }
            return useCustom;
        }

        public void getScreenColors(Context appContext) {
            Resources res = appContext.getResources();
            int[] colors = {res.getInteger(R.integer.color1), res.getInteger(R.integer.color2), res.getInteger(R.integer.color3), res.getInteger(R.integer.color4)};
            if (getCustomColors(appContext, colors)) {
                this.mTextColor = colors[1];
                this.mBackgroundColor = colors[2];
                this.mLineColor = colors[0];
            } else {
                String colorSetting = Settings.getColorScheme(appContext);
                if (colorSetting.equals("BW")) {
                    this.mTextColor = colors[0];
                    this.mBackgroundColor = colors[1];
                    this.mLineColor = this.mTextColor;
                } else if (colorSetting.equals("WB")) {
                    this.mTextColor = colors[1];
                    this.mBackgroundColor = colors[0];
                    this.mLineColor = this.mTextColor;
                } else if (colorSetting.equals("WBL")) {
                    this.mTextColor = colors[1];
                    this.mBackgroundColor = colors[2];
                    this.mLineColor = colors[0];
                }
                this.mLineColor &= -2130706433;
            }
            this.mHighlightColor = colors[3];
        }
    }

    /* loaded from: classes.dex */
    public static class ColoredTextAdapter extends ArrayAdapter<String> {
        private final Activity mActivity;
        private final ArrayList<String> mList;
        private TextStyle mTextStyle;

        public ColoredTextAdapter(Activity activity, ArrayList<String> alist, TextStyle style, Typeface typeface) {
            this(activity, alist, style);
            this.mTextStyle.mTypeface = typeface;
        }

        public ColoredTextAdapter(Activity activity, ArrayList<String> alist, TextStyle style) {
            super(activity, Settings.getLOadapter(activity), alist);
            this.mActivity = activity;
            this.mList = alist;
            this.mTextStyle = style;
        }

        public int getTextColor() {
            return this.mTextStyle.mTextColor;
        }

        public int getBackgroundColor() {
            return this.mTextStyle.mBackgroundColor;
        }

        public int getLineColor() {
            return this.mTextStyle.mLineColor;
        }

        public int getHighlightColor() {
            return this.mTextStyle.mHighlightColor;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                row = this.mActivity.getLayoutInflater().inflate(Settings.getLOadapter(this.mActivity), (ViewGroup) null);
            }
            TextView text = (TextView) row.findViewById(R.id.the_text);
            text.setTextColor(this.mTextStyle.mTextColor);
            text.setText(this.mList.get(position));
            if (this.mTextStyle.mTypeface != null) {
                text.setTypeface(this.mTextStyle.mTypeface);
            }
            text.setBackgroundColor(this.mTextStyle.mBackgroundColor);
            text.setHighlightColor(this.mTextStyle.mHighlightColor);
            return row;
        }
    }

    public static Toast toaster(Context context, CharSequence msg) {
        if (context == null || msg == null || msg.equals("")) {
            return null;
        }
        Toast toast = Toast.makeText(context, msg, 0);
        toast.setGravity(49, 0, 50);
        toast.show();
        return toast;
    }

    /* loaded from: classes.dex */
    public static class Encryption {
        public static final boolean ENABLE_DECRYPTION = true;
        private static final int ITERATION_COUNT = 19;
        public static final boolean NO_DECRYPTION = false;
        private static final byte[] SALT = {-87, -101, -56, 50, 86, 53, -29, 3};
        private Cipher mCipher;

        public Cipher cipher() {
            return this.mCipher;
        }

        public Encryption(int mode, String PW) throws Exception {
            this.mCipher = null;
            try {
                SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(PW.toCharArray(), SALT, 19));
                this.mCipher = Cipher.getInstance(key.getAlgorithm());
                this.mCipher.init(mode, key, new PBEParameterSpec(SALT, 19));
            } catch (Exception e) {
                throw e;
            }
        }
    }
}
