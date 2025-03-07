package com.rfo.gogocontours;

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
import com.rfo.gogocontours.Run;
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
    static /* synthetic */ Class class$0;
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

    public static boolean checkSDCARD(char c) {
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            return true;
        }
        if (!"mounted_ro".equals(externalStorageState) || c != 'r') {
            return false;
        }
        return true;
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 0:
                if (iArr[0] != 0) {
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

    public static void setFilePaths(String str) {
        if (str.equals("none")) {
            str = Environment.getExternalStorageDirectory().getPath();
        }
        basePath = str;
        filePath = new File(str, AppPath).getPath();
    }

    public static String getBasePath() {
        return basePath;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static String getFilePath(String str, String str2) {
        StringBuilder sb = new StringBuilder(filePath);
        if (str != null) {
            sb.append(File.separatorChar).append(str);
        }
        if (str2 != null) {
            sb.append(File.separatorChar).append(str2);
        }
        File file = new File(sb.toString());
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return file.getAbsolutePath();
        }
    }

    public static String getSourcePath(String str) {
        return getFilePath(SOURCE_DIR, str);
    }

    public static String getSamplesPath(String str) {
        return getFilePath(SOURCE_SAMPLES_PATH, str);
    }

    public static String getDataPath(String str) {
        return getFilePath(DATA_DIR, str);
    }

    public static String getDataBasePath(String str) {
        return getFilePath(DATABASES_DIR, str);
    }

    public static String getAppFilePath(String str, String str2) {
        StringBuilder sb = new StringBuilder(AppPath);
        if (str != null) {
            sb.append(File.separatorChar).append(str);
        }
        if (str2 != null) {
            sb.append(File.separatorChar).append(str2);
        }
        String sb2 = sb.toString();
        try {
            return new File(sb2.toString()).getCanonicalPath().substring(1);
        } catch (IOException e) {
            Log.w(LOGTAG, "getAppFilePath - getCanonicalPath: " + e);
            return sb2;
        }
    }

    public static ContextManager getContextManager() {
        return mContextMgr;
    }

    public static void clearContextManager() {
        mContextMgr.clearProgramContexts();
    }

    private void initVars() {
        Context applicationContext = getApplicationContext();
        mContextMgr = new ContextManager(applicationContext);
        mBasicPackage = applicationContext.getPackageName();
        Resources resources = getResources();
        AppPath = resources.getString(R.string.app_path);
        isAPK = resources.getBoolean(R.bool.is_apk);
        apkCreateDataDir = resources.getBoolean(R.bool.apk_create_data_dir);
        apkCreateDataBaseDir = resources.getBoolean(R.bool.apk_create_database_dir);
        DoAutoRun = false;
        mDefaultFirstLine = getString(R.string.display_text_default_first_line);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.v(LOGTAG, "onCreate: " + this);
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
        String[] permissionsFromManifest = getPermissionsFromManifest(getApplicationContext());
        if (permissionsFromManifest == null || permissionsFromManifest.length <= 0) {
            return true;
        }
        for (String str : permissionsFromManifest) {
            if (!str.equals("android.permission.ACCESS_MOCK_LOCATION") && ContextCompat.checkSelfPermission(this, str) != 0) {
                ActivityCompat.requestPermissions(this, permissionsFromManifest, 0);
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
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(LauncherShortcuts.EXTRA_LS_FILENAME);
        Bundle bundleExtra = intent.getBundleExtra(Editor.EXTRA_RESTART);
        if (stringExtra == null && intent.getData() != null) {
            stringExtra = intent.getData().getPath();
        }
        if (stringExtra != null && !stringExtra.equals("")) {
            Intent load = new AutoRun(this, stringExtra, false, null).load();
            DoAutoRun = true;
            startActivity(load);
            finish();
        } else if (AreSamplesLoaded()) {
            DoAutoRun = false;
            Intent intent2 = new Intent(this, Editor.class);
            if (bundleExtra != null) {
                intent2.putExtra(Editor.EXTRA_RESTART, bundleExtra);
            }
            startActivity(intent2);
            finish();
        } else {
            runBackgroundLoader();
        }
    }

    private void createForAPK() {
        String str = "";
        Intent intent = getIntent();
        if (intent.getData() != null) {
            str = intent.getData().getPath();
        }
        if (!str.equals("")) {
            Run.called_with = getRelativePath(str, getDataPath(null));
        }
        runBackgroundLoader();
    }

    private void runBackgroundLoader() {
        int i;
        setContentView(R.layout.splash);
        this.mSplash = (ImageView) findViewById(R.id.splash);
        Resources resources = getResources();
        if (!resources.getBoolean(R.bool.splash_display) || (i = resources.getIdentifier("splash", "drawable", getPackageName())) <= 0) {
            i = R.drawable.blank;
        }
        this.mSplash.setImageResource(i);
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
        File file = new File(samplesPath);
        file.mkdirs();
        String[] list = file.list();
        if (!(list == null || list.length == 0)) {
            ArrayList arrayList = new ArrayList(Arrays.asList(list));
            Collections.sort(arrayList);
            String str = (String) arrayList.get(0);
            if (str.length() > 11) {
                String[] split = str.substring(5).split("_");
                if (split.length > 1 && mContextMgr.getContext(1).getString(R.string.version).substring(0, 5).equals(String.valueOf(split[0]) + "." + split[1])) {
                    return true;
                }
            }
            int length = list.length;
            for (int i = 0; i < length; i++) {
                new File(String.valueOf(samplesPath) + File.separatorChar + list[i]).delete();
            }
        }
        return false;
    }

    public static String getRawFileName(String str) {
        if (str == null) {
            return "";
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        int indexOf = lowerCase.indexOf(".");
        return indexOf != -1 ? lowerCase.substring(0, indexOf) : lowerCase;
    }

    public static String getAlternateRawFileName(String str) {
        Locale locale = Locale.getDefault();
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf >= 0) {
            return str.substring(lastIndexOf + 1).toLowerCase(locale).replace(".", "_");
        }
        return str.toLowerCase(locale).replace(".", "_");
    }

    public static int getRawResourceID(String str) {
        String rawFileName;
        int i;
        if (str == null) {
            str = "";
        }
        int i2 = 0;
        int i3 = 1;
        while (i2 == 0 && i3 <= 2) {
            if (i3 == 1) {
                rawFileName = getAlternateRawFileName(str);
            } else {
                rawFileName = i3 == 2 ? getRawFileName(str) : "";
            }
            if (!rawFileName.equals("")) {
                i = mContextMgr.getContext(1).getResources().getIdentifier(String.valueOf(mBasicPackage) + ":raw/" + rawFileName, null, null);
            } else {
                i = i2;
            }
            i3++;
            i2 = i;
        }
        return i2;
    }

    public static InputStream streamFromResource(String str, String str2) throws Exception {
        Context context = mContextMgr.getContext(1);
        int rawResourceID = getRawResourceID(str2);
        if (rawResourceID != 0) {
            return context.getResources().openRawResource(rawResourceID);
        }
        return context.getAssets().open(getAppFilePath(str, str2));
    }

    public static BufferedReader getBufferedReader(String str, String str2, boolean z) throws Exception {
        String filePath2;
        InputStream streamFromResource;
        if (str == null) {
            filePath2 = str2;
        } else {
            filePath2 = getFilePath(str, str2);
        }
        File file = new File(filePath2);
        if (file.exists()) {
            return new BufferedReader(new FileReader(file));
        }
        if (!isAPK || (streamFromResource = streamFromResource(str, str2)) == null) {
            return null;
        }
        if (mContextMgr.getContext(1).getResources().getBoolean(R.bool.apk_programs_encrypted) && z) {
            streamFromResource = getDecryptedStream(streamFromResource);
        }
        return new BufferedReader(new InputStreamReader(streamFromResource));
    }

    public static BufferedInputStream getBufferedInputStream(String str, String str2) throws Exception {
        String filePath2;
        InputStream streamFromResource;
        if (str == null) {
            filePath2 = str2;
        } else {
            filePath2 = getFilePath(str, str2);
        }
        File file = new File(filePath2);
        if (file.exists()) {
            return new BufferedInputStream(new FileInputStream(file));
        }
        if (!isAPK || (streamFromResource = streamFromResource(str, str2)) == null) {
            return null;
        }
        return new BufferedInputStream(streamFromResource);
    }

    public static InputStream getDecryptedStream(InputStream inputStream) throws Exception {
        return new CipherInputStream(inputStream, new Encryption(2, mBasicPackage).cipher());
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x004f, code lost:
        return r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getRelativePath(java.lang.String r10, java.lang.String r11) {
        /*
            r3 = 0
            java.io.File r0 = new java.io.File     // Catch: IOException -> 0x0050
            r0.<init>(r10)     // Catch: IOException -> 0x0050
            java.lang.String r0 = r0.getCanonicalPath()     // Catch: IOException -> 0x0050
            java.lang.String r1 = "/$"
            java.lang.String r2 = ""
            java.lang.String r1 = r0.replaceAll(r1, r2)     // Catch: IOException -> 0x0050
            java.io.File r0 = new java.io.File     // Catch: IOException -> 0x00d4
            r0.<init>(r11)     // Catch: IOException -> 0x00d4
            java.lang.String r0 = r0.getCanonicalPath()     // Catch: IOException -> 0x00d4
            java.lang.String r2 = "/$"
            java.lang.String r4 = ""
            java.lang.String r0 = r0.replaceAll(r2, r4)     // Catch: IOException -> 0x00d4
        L_0x0023:
            java.lang.String r2 = "/"
            java.lang.String r2 = java.util.regex.Pattern.quote(r2)
            java.lang.String[] r5 = r0.split(r2)
            java.lang.String r2 = "/"
            java.lang.String r2 = java.util.regex.Pattern.quote(r2)
            java.lang.String[] r4 = r1.split(r2)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r2 = r3
        L_0x003d:
            int r7 = r4.length
            if (r2 >= r7) goto L_0x004d
            int r7 = r5.length
            if (r2 >= r7) goto L_0x004d
            r7 = r4[r2]
            r8 = r5[r2]
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x006a
        L_0x004d:
            if (r2 != 0) goto L_0x0085
        L_0x004f:
            return r10
        L_0x0050:
            r0 = move-exception
            r1 = r0
            r0 = r10
        L_0x0053:
            java.lang.String r2 = "Basic"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "getRelativePath - getCanonicalPath: "
            r4.<init>(r5)
            java.lang.StringBuilder r1 = r4.append(r1)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r2, r1)
            r1 = r0
            r0 = r11
            goto L_0x0023
        L_0x006a:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r8 = r4[r2]
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r7.<init>(r8)
            java.lang.String r8 = "/"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.append(r7)
            int r2 = r2 + 1
            goto L_0x003d
        L_0x0085:
            r4 = 1
            java.io.File r7 = new java.io.File
            r7.<init>(r0)
            boolean r0 = r7.exists()
            if (r0 == 0) goto L_0x00bf
            boolean r0 = r7.isFile()
        L_0x0095:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            int r7 = r5.length
            if (r7 == r2) goto L_0x00a5
            if (r0 == 0) goto L_0x00c9
            int r0 = r5.length
            int r0 = r0 - r2
            int r0 = r0 + -1
        L_0x00a3:
            if (r3 < r0) goto L_0x00cc
        L_0x00a5:
            int r0 = r1.length()
            int r2 = r6.length()
            if (r0 <= r2) goto L_0x00ba
            int r0 = r6.length()
            java.lang.String r0 = r1.substring(r0)
            r4.append(r0)
        L_0x00ba:
            java.lang.String r10 = r4.toString()
            goto L_0x004f
        L_0x00bf:
            java.lang.String r0 = "/"
            boolean r0 = r11.endsWith(r0)
            if (r0 == 0) goto L_0x00da
            r0 = r3
            goto L_0x0095
        L_0x00c9:
            int r0 = r5.length
            int r0 = r0 - r2
            goto L_0x00a3
        L_0x00cc:
            java.lang.String r2 = "../"
            r4.append(r2)
            int r3 = r3 + 1
            goto L_0x00a3
        L_0x00d4:
            r0 = move-exception
            r9 = r0
            r0 = r1
            r1 = r9
            goto L_0x0053
        L_0x00da:
            r0 = r4
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rfo.gogocontours.Basic.getRelativePath(java.lang.String, java.lang.String):java.lang.String");
    }

    public static int loadProgramFileToList(boolean z, String str, ArrayList<String> arrayList) {
        String str2;
        int i;
        int i2 = 0;
        try {
            BufferedReader bufferedReader = getBufferedReader(z ? null : SOURCE_DIR, str, true);
            if (!z) {
                str = getSourcePath(str);
            }
            Run.running_bas = getRelativePath(str, getSourcePath(null));
            while (true) {
                try {
                    str2 = bufferedReader.readLine();
                } catch (IOException e) {
                    str2 = null;
                }
                if (str2 != null) {
                    arrayList.add(str2);
                    i = str2.length() + 1 + i2;
                } else {
                    i = i2;
                }
                if (str2 == null) {
                    break;
                }
                i2 = i;
            }
            if (!arrayList.isEmpty()) {
                return i;
            }
            arrayList.add("!");
            return 2;
        } catch (Exception e2) {
            return 0;
        }
    }

    public static String loadProgramListToString(ArrayList<String> arrayList, int i) {
        StringBuilder sb = new StringBuilder(i);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(String.valueOf(it.next()) + '\n');
        }
        return sb.toString();
    }

    public static void loadProgramFromString(String str, AddProgramLine addProgramLine) {
        if (addProgramLine == null) {
            addProgramLine = new AddProgramLine();
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = AddProgramLine.charCount;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\n') {
                AddProgramLine.charCount = i2 + i;
                addProgramLine.AddLine(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(charAt);
            }
        }
        if (sb.length() != 0) {
            AddProgramLine.charCount = length + i;
            addProgramLine.AddLine(sb.toString());
        }
    }

    public static IOException closeStreams(InputStream inputStream, OutputStream outputStream) {
        IOException e = null;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (outputStream == null) {
            return e;
        }
        try {
            outputStream.flush();
            outputStream.close();
            return e;
        } catch (IOException e3) {
            if (e == null) {
                return e3;
            }
            return e;
        }
    }

    public static IOException closeStreams(Reader reader, Writer writer) {
        IOException e = null;
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (writer == null) {
            return e;
        }
        try {
            writer.flush();
            writer.close();
            return e;
        } catch (IOException e3) {
            if (e == null) {
                return e3;
            }
            return e;
        }
    }

    /* loaded from: classes.dex */
    public class Loader extends AsyncTask<String, String, String> {
        static /* synthetic */ Class class$0;
        static /* synthetic */ Class class$1;
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
            String[] stringArray = this.mRes.getStringArray(R.array.loading_msg);
            this.mProgressMarker = this.mRes.getString(R.string.progress_marker);
            this.mDisplayProgress = (stringArray == null || stringArray.length == 0) ? false : true;
            if (this.mDisplayProgress) {
                String str = stringArray[0];
                int length = stringArray.length - 1;
                for (int i = 1; i < length; i++) {
                    str = String.valueOf(str) + '\n' + stringArray[i];
                }
                if (length > 0 && stringArray[length].length() > 0) {
                    str = String.valueOf(str) + '\n' + stringArray[length];
                }
                Basic.this.mProgressDialog.setTitle(str);
                Basic.this.mProgressDialog.show();
                Basic.this.mProgressText.setText("");
                this.mUpdates = 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onProgressUpdate(String... strArr) {
            if (this.mDisplayProgress) {
                this.mUpdates++;
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    Basic.this.mProgressText.setText(((Object) Basic.this.mProgressText.getText()) + strArr[i]);
                }
                if (Basic.this.mProgressText.getHeight() > (Basic.this.mSplash.getHeight() * 2) / 3) {
                    StringBuilder sb = new StringBuilder("Continuing load (");
                    if (this.maxUpdateCount != 0) {
                        sb.append((this.mUpdates * 100) / this.maxUpdateCount);
                        sb.append(Format.COMMENT);
                    } else {
                        sb.append(this.mUpdates);
                    }
                    sb.append(")\n");
                    Basic.this.mProgressText.setText(sb);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
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
            int integer;
            long currentTimeMillis = System.currentTimeMillis();
            Basic.InitDirs();
            LoadGraphicsForAPK();
            Basic.lines = new ArrayList<>();
            LoadTheProgram();
            if (this.mRes.getBoolean(R.bool.splash_display) && (integer = this.mRes.getInteger(R.integer.splash_time) - ((int) (System.currentTimeMillis() - currentTimeMillis))) > 1) {
                try {
                    Thread.sleep((long) integer);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Basic.DoAutoRun = true;
            return new Intent(Basic.this, Run.class);
        }

        private void copyAssets(String str) {
            String[] strArr;
            String[] strArr2 = null;
            try {
                strArr = Basic.this.getAssets().list(str);
            } catch (IOException e) {
                Log.e(Basic.LOGTAG, "copyAssets: " + e);
                strArr = strArr2;
            }
            if (strArr != null) {
                if (strArr.length == 0) {
                    copyAssetToFile(str);
                    return;
                }
                new File(String.valueOf(Basic.getBasePath()) + File.separatorChar + str).mkdirs();
                int length = strArr.length;
                for (int i = 0; i < length; i++) {
                    copyAssets(String.valueOf(str) + File.separatorChar + strArr[i]);
                }
            }
        }

        private void copyAssetToFile(String str) {
            if (str.endsWith(".bas") || str.endsWith(".txt") || str.endsWith(".html")) {
                copyTextAssetToFile(str);
            } else {
                copyBinaryAssetToFile(str);
            }
        }

        private void copyTextAssetToFile(String str) {
            BufferedReader bufferedReader;
            BufferedWriter bufferedWriter;
            publishProgress(this.mProgressMarker);
            File file = new File(String.valueOf(Basic.getBasePath()) + File.separatorChar + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Basic.this.getAssets().open(str)));
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            bufferedWriter.write(String.valueOf(readLine) + "\n");
                        } catch (IOException e) {
                            e = e;
                            Log.w(Basic.LOGTAG, "copyTextAssetToFile: " + e);
                            Basic.closeStreams(bufferedReader, bufferedWriter);
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    bufferedWriter = null;
                }
            } catch (IOException e3) {
                e = e3;
                bufferedReader = null;
                bufferedWriter = null;
            }
            Basic.closeStreams(bufferedReader, bufferedWriter);
        }

        private void copyBinaryAssetToFile(String str) {
            IOException e;
            BufferedInputStream bufferedInputStream;
            BufferedOutputStream bufferedOutputStream;
            int read;
            File file = new File(String.valueOf(Basic.getBasePath()) + File.separatorChar + str);
            byte[] bArr = new byte[8192];
            try {
                bufferedInputStream = new BufferedInputStream(Basic.this.getAssets().open(str));
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    do {
                        try {
                            read = bufferedInputStream.read(bArr, 0, 8192);
                            if (read > 0) {
                                bufferedOutputStream.write(bArr, 0, read);
                                publishProgress(this.mProgressMarker);
                            }
                        } catch (IOException e2) {
                            e = e2;
                            Log.w(Basic.LOGTAG, "copyBinaryAssetToFile: " + e);
                            Basic.closeStreams(bufferedInputStream, bufferedOutputStream);
                        }
                    } while (read != -1);
                } catch (IOException e3) {
                    e = e3;
                    bufferedOutputStream = null;
                }
            } catch (IOException e4) {
                e = e4;
                bufferedInputStream = null;
                bufferedOutputStream = null;
            }
            Basic.closeStreams(bufferedInputStream, bufferedOutputStream);
        }

        private void LoadGraphicsForAPK() {
            String[] stringArray = this.mRes.getStringArray(R.array.load_file_names);
            for (String str : stringArray) {
                if (!str.equals("")) {
                    Load1Graphic(str.endsWith(".db") ? Basic.DATABASES_DIR : Basic.DATA_DIR, str);
                }
            }
        }

        private void Load1Graphic(String str, String str2) {
            Exception e;
            BufferedInputStream bufferedInputStream;
            BufferedOutputStream bufferedOutputStream;
            int read;
            File file = new File(Basic.getFilePath(str, str2));
            File file2 = new File(file.getParent());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file2.exists()) {
                Log.w(Basic.LOGTAG, "Load1Graphic: can not create directory " + file.getPath());
                return;
            }
            byte[] bArr = new byte[8192];
            try {
                bufferedInputStream = new BufferedInputStream(Basic.streamFromResource(str, str2), 8192);
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    do {
                        try {
                            read = bufferedInputStream.read(bArr, 0, 8192);
                            if (read > 0) {
                                bufferedOutputStream.write(bArr, 0, read);
                                publishProgress(this.mProgressMarker);
                            }
                        } catch (Exception e2) {
                            e = e2;
                            Log.w(Basic.LOGTAG, "Load1Graphic: " + e);
                            Basic.closeStreams(bufferedInputStream, bufferedOutputStream);
                        }
                    } while (read != -1);
                } catch (Exception e3) {
                    e = e3;
                    bufferedOutputStream = null;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedInputStream = null;
                bufferedOutputStream = null;
            }
            Basic.closeStreams(bufferedInputStream, bufferedOutputStream);
        }

        public void doFirstLoad() {
            String string = Basic.this.getString(R.string.display_text_initial_info);
            String string2 = Basic.this.getString(R.string.display_text_addendum_dont_keep);
            String string3 = Basic.this.getString(R.string.display_text_open_comment);
            String string4 = Basic.this.getString(R.string.display_text_close_comment);
            int i = Build.VERSION.SDK_INT;
            StringBuilder append = new StringBuilder(String.valueOf(string3)).append(string);
            if (i < 11) {
                string2 = "";
            }
            Editor.DisplayText = append.append(string2).append(string4).toString();
        }

        public void doCantLoad() {
            Editor.DisplayText = String.valueOf(Basic.this.getString(R.string.display_text_open_comment)) + Basic.this.getString(R.string.display_text_cant_load) + Basic.this.getString(R.string.display_text_close_comment);
        }

        private void LoadTheProgram() {
            AddProgramLine addProgramLine = new AddProgramLine();
            try {
                InputStream streamFromResource = Basic.streamFromResource(Basic.SOURCE_DIR, this.mRes.getString(R.string.my_program));
                if (streamFromResource != null && this.mRes.getBoolean(R.bool.apk_programs_encrypted)) {
                    streamFromResource = Basic.getDecryptedStream(streamFromResource);
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamFromResource));
                int i = 0;
                while (true) {
                    try {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            addProgramLine.AddLine(readLine);
                            i++;
                            if (i >= 200) {
                                publishProgress(this.mProgressMarker);
                                i = 0;
                            }
                        } catch (IOException e) {
                            Log.e(Basic.LOGTAG, "LoadTheProgram - error reading, about line " + i + ": " + e);
                        }
                    } catch (Throwable th) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (Exception e4) {
                Log.e(Basic.LOGTAG, "LoadTheProgram - error getting stream from resource: " + e4);
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

        public TextStyle(TextStyle textStyle, Typeface typeface) {
            this(textStyle);
            this.mTypeface = typeface;
        }

        public TextStyle(TextStyle textStyle) {
            this(textStyle.mTextColor, textStyle.mBackgroundColor, textStyle.mLineColor, textStyle.mHighlightColor, textStyle.mSize, textStyle.mTypeface);
        }

        public TextStyle(int i, int i2, int i3, int i4, float f, Typeface typeface) {
            this.mTextColor = i;
            this.mBackgroundColor = i2;
            this.mLineColor = i3;
            this.mHighlightColor = i4;
            this.mSize = f;
            this.mTypeface = typeface;
        }

        public void refresh() {
            Context context = Basic.mContextMgr.getContext(1);
            getScreenColors(context);
            this.mSize = Settings.getFont(context);
            this.mTypeface = Settings.getConsoleTypeface(context);
        }

        public boolean getCustomColors(Context context, int[] iArr) {
            boolean useCustomColors = Settings.useCustomColors(context);
            if (useCustomColors) {
                String[] customColors = Settings.getCustomColors(context);
                for (int i = 0; i < 4; i++) {
                    String replace = customColors[i].trim().replace("0x", "#");
                    if (!replace.contains("#")) {
                        replace = "#" + replace;
                    }
                    try {
                        iArr[i] = Color.parseColor(replace);
                    } catch (IllegalArgumentException e) {
                        Log.d(Basic.LOGTAG, "getPrefColors: failed to parse <" + replace + ">");
                    }
                }
            }
            return useCustomColors;
        }

        public void getScreenColors(Context context) {
            Resources resources = context.getResources();
            int[] iArr = {resources.getInteger(R.integer.color1), resources.getInteger(R.integer.color2), resources.getInteger(R.integer.color3), resources.getInteger(R.integer.color4)};
            if (getCustomColors(context, iArr)) {
                this.mTextColor = iArr[1];
                this.mBackgroundColor = iArr[2];
                this.mLineColor = iArr[0];
            } else {
                String colorScheme = Settings.getColorScheme(context);
                if (colorScheme.equals("BW")) {
                    this.mTextColor = iArr[0];
                    this.mBackgroundColor = iArr[1];
                    this.mLineColor = this.mTextColor;
                } else if (colorScheme.equals("WB")) {
                    this.mTextColor = iArr[1];
                    this.mBackgroundColor = iArr[0];
                    this.mLineColor = this.mTextColor;
                } else if (colorScheme.equals("WBL")) {
                    this.mTextColor = iArr[1];
                    this.mBackgroundColor = iArr[2];
                    this.mLineColor = iArr[0];
                }
                this.mLineColor &= -2130706433;
            }
            this.mHighlightColor = iArr[3];
        }
    }

    /* loaded from: classes.dex */
    public static class ColoredTextAdapter extends ArrayAdapter<String> {
        private final Activity mActivity;
        private final ArrayList<String> mList;
        private TextStyle mTextStyle;

        public ColoredTextAdapter(Activity activity, ArrayList<String> arrayList, TextStyle textStyle, Typeface typeface) {
            this(activity, arrayList, textStyle);
            this.mTextStyle.mTypeface = typeface;
        }

        public ColoredTextAdapter(Activity activity, ArrayList<String> arrayList, TextStyle textStyle) {
            super(activity, Settings.getLOadapter(activity), arrayList);
            this.mActivity = activity;
            this.mList = arrayList;
            this.mTextStyle = textStyle;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mActivity.getLayoutInflater().inflate(Settings.getLOadapter(this.mActivity), (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(R.id.the_text);
            textView.setTextColor(this.mTextStyle.mTextColor);
            textView.setText(this.mList.get(i));
            if (this.mTextStyle.mTypeface != null) {
                textView.setTypeface(this.mTextStyle.mTypeface);
            }
            textView.setBackgroundColor(this.mTextStyle.mBackgroundColor);
            textView.setHighlightColor(this.mTextStyle.mHighlightColor);
            return view;
        }
    }

    public static Toast toaster(Context context, CharSequence charSequence) {
        if (context == null || charSequence == null || charSequence.equals("")) {
            return null;
        }
        Toast makeText = Toast.makeText(context, charSequence, 0);
        makeText.setGravity(49, 0, 50);
        makeText.show();
        return makeText;
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

        public Encryption(int i, String str) throws Exception {
            this.mCipher = null;
            try {
                SecretKey generateSecret = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec(str.toCharArray(), SALT, ITERATION_COUNT));
                this.mCipher = Cipher.getInstance(generateSecret.getAlgorithm());
                this.mCipher.init(i, generateSecret, new PBEParameterSpec(SALT, ITERATION_COUNT));
            } catch (Exception e) {
                throw e;
            }
        }
    }
}
