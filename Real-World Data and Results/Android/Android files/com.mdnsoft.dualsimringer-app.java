package com.mdnsoft.dualsimringer;

import android.app.Application;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.Button;
import com.a.a.a.a.a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class app extends Application {
    public static SQLiteDatabase D;
    public static PrintWriter G;
    public static PrintWriter H;
    private static app K;
    public static NotificationManager b;
    public static String n;
    public static String o;
    public static String p;
    public static String q;
    public static boolean r;
    public static boolean s;
    public static boolean t;
    public static boolean u;
    public static boolean v;
    public static boolean w;
    public static int x;
    public static int y;
    public static boolean a = false;
    public static boolean c = false;
    public static boolean d = false;
    public static int e = 0;
    public static int f = 0;
    public static int g = 0;
    public static int h = 5;
    public static boolean i = true;
    public static boolean j = false;
    public static boolean k = false;
    public static boolean l = false;
    public static String m = "";
    public static boolean z = true;
    public static boolean A = false;
    public static boolean B = false;
    public static long C = 0;
    public static boolean E = false;
    public static boolean F = false;
    public static boolean I = false;
    public static long J = 0;

    static {
        new Handler();
    }

    public static app a() {
        return K;
    }

    public static String a(long j2) {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).format(Long.valueOf(j2));
    }

    public static String a(String str, int i2) {
        return new a(DataService.j, K.getPackageName(), Settings.Secure.getString(K.getContentResolver(), "android_id")).a(String.valueOf(i2), str);
    }

    public static void a(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(K.getApplicationContext());
        Dialog dialog = new Dialog(context);
        dialog.setTitle(R.string.app_name);
        dialog.setContentView(R.layout.ratedlg);
        ((Button) dialog.findViewById(R.id.button1)).setOnClickListener(new V(defaultSharedPreferences, dialog));
        ((Button) dialog.findViewById(R.id.button2)).setOnClickListener(new W(defaultSharedPreferences, dialog));
        ((Button) dialog.findViewById(R.id.button3)).setOnClickListener(new X(defaultSharedPreferences, dialog));
        dialog.show();
    }

    public static void a(Context context, String str) {
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
    }

    public static void a(PrintWriter printWriter, String str) {
        if (printWriter != null) {
            printWriter.println(String.valueOf(d()) + " :" + str);
            printWriter.flush();
        }
    }

    public static void b() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(K);
        if (!defaultSharedPreferences.contains("d")) {
            defaultSharedPreferences.edit().putLong("d", System.currentTimeMillis()).commit();
        }
        l = new File("/dev/socket/rild-mtk-modem").exists();
        e = Integer.parseInt(defaultSharedPreferences.getString("pMode", "0"));
        if (l && !defaultSharedPreferences.contains("pSMSMode")) {
            defaultSharedPreferences.edit().putString("pSMSMode", "1").commit();
        }
        f = Integer.parseInt(defaultSharedPreferences.getString("pSMSMode", "0"));
        a(G, "Mode=" + e + ", SMSMode=" + f);
        h = defaultSharedPreferences.getInt("pMuteSMSTime", Build.VERSION.SDK_INT >= 24 ? 15 : 5);
        m = defaultSharedPreferences.getString("pLanguage", "");
        z = defaultSharedPreferences.getBoolean("pForeground", true);
        if (Build.VERSION.SDK_INT >= 26 && !z && !S.a(K)) {
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            edit.putBoolean("pForeground", true);
            edit.commit();
            z = true;
        }
        i = defaultSharedPreferences.getBoolean("pEnabled", true);
        j = defaultSharedPreferences.getBoolean("pSaveSilent", false);
        k = defaultSharedPreferences.getBoolean("pSkipContacts", false);
        AudioManager audioManager = (AudioManager) K.getApplicationContext().getSystemService("audio");
        n = defaultSharedPreferences.getString("pSMS1Sound", null);
        o = defaultSharedPreferences.getString("pSMS2Sound", null);
        p = defaultSharedPreferences.getString("pCall1Sound", null);
        q = defaultSharedPreferences.getString("pCall2Sound", null);
        r = defaultSharedPreferences.getBoolean("pSMS1", false);
        s = defaultSharedPreferences.getBoolean("pSMS2", false);
        t = defaultSharedPreferences.getBoolean("pCall1", true);
        u = defaultSharedPreferences.getBoolean("pCall2", true);
        a(G, "Call1=" + t + ",Call2=" + u);
        v = defaultSharedPreferences.getBoolean("pbVolume1", false);
        w = defaultSharedPreferences.getBoolean("pbVolume2", false);
        x = defaultSharedPreferences.getInt("pVolume1", audioManager.getStreamMaxVolume(2));
        y = defaultSharedPreferences.getInt("pVolume2", audioManager.getStreamMaxVolume(2));
        I = defaultSharedPreferences.getBoolean("pnrate", false);
        if (!defaultSharedPreferences.contains("pdrate")) {
            SharedPreferences.Editor edit2 = defaultSharedPreferences.edit();
            edit2.putLong("pdrate", System.currentTimeMillis());
            edit2.commit();
        }
        J = defaultSharedPreferences.getLong("pdrate", 0);
        F = defaultSharedPreferences.getBoolean("pXposed", false);
        if (Build.MODEL.equals("E6533")) {
            g = 1;
        }
    }

    public static void b(PrintWriter printWriter, String str) {
        if (printWriter != null) {
            printWriter.println(String.valueOf(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis()))) + " :" + str);
            printWriter.flush();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c() {
        /*
            r10 = 0
            r1 = 1
            r2 = 0
            java.io.PrintWriter r0 = com.mdnsoft.dualsimringer.app.G
            java.lang.String r3 = "start ReadLicense"
            a(r0, r3)
            android.database.sqlite.SQLiteDatabase r0 = com.mdnsoft.dualsimringer.app.D
            java.lang.String r3 = "select date, r, answer from tblog where r in(0,1) order by date desc Limit 1"
            r4 = 0
            android.database.Cursor r3 = r0.rawQuery(r3, r4)
            if (r3 == 0) goto L_0x00b9
            boolean r0 = r3.moveToFirst()
            if (r0 == 0) goto L_0x00b9
            java.io.PrintWriter r0 = com.mdnsoft.dualsimringer.app.G
            java.lang.String r4 = "q moveToFirst"
            a(r0, r4)
            long r4 = r3.getLong(r2)
            int r6 = r3.getInt(r1)
            r0 = 2
            java.lang.String r0 = r3.getString(r0)
            java.lang.String r7 = java.lang.String.valueOf(r4)
            java.lang.String r7 = a(r7, r6)
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x00b0
            java.io.PrintWriter r0 = com.mdnsoft.dualsimringer.app.G
            java.lang.String r7 = "AES=true,r="
            java.lang.String r8 = java.lang.String.valueOf(r6)
            java.lang.String r7 = r7.concat(r8)
            a(r0, r7)
            if (r6 != 0) goto L_0x00ab
            r0 = r1
        L_0x0050:
            com.mdnsoft.dualsimringer.app.A = r0
            if (r6 != r1) goto L_0x00ad
            long r0 = java.lang.System.currentTimeMillis()
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ad
            com.mdnsoft.dualsimringer.app.C = r4
        L_0x005e:
            java.io.PrintWriter r0 = com.mdnsoft.dualsimringer.app.G
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r4 = "RL="
            r1.<init>(r4)
            boolean r4 = com.mdnsoft.dualsimringer.app.A
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            a(r0, r1)
            if (r3 == 0) goto L_0x0079
            r3.close()
        L_0x0079:
            boolean r0 = com.mdnsoft.dualsimringer.S.e()
            if (r0 == 0) goto L_0x0081
            com.mdnsoft.dualsimringer.app.A = r2
        L_0x0081:
            boolean r0 = com.mdnsoft.dualsimringer.app.A
            com.mdnsoft.dualsimringer.app.B = r0
            boolean r0 = com.mdnsoft.dualsimringer.app.A
            if (r0 == 0) goto L_0x00aa
            com.mdnsoft.dualsimringer.app r0 = com.mdnsoft.dualsimringer.app.K
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.String r1 = "d"
            long r0 = r0.getLong(r1, r10)
            long r0 = r4 - r0
            r4 = 604800000(0x240c8400, double:2.988109026E-315)
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x00aa
            com.mdnsoft.dualsimringer.app.A = r2
            long r0 = java.lang.System.currentTimeMillis()
            com.mdnsoft.dualsimringer.app.C = r0
        L_0x00aa:
            return
        L_0x00ab:
            r0 = r2
            goto L_0x0050
        L_0x00ad:
            com.mdnsoft.dualsimringer.app.C = r10
            goto L_0x005e
        L_0x00b0:
            java.io.PrintWriter r0 = com.mdnsoft.dualsimringer.app.G
            java.lang.String r4 = "AES=false"
            a(r0, r4)
            com.mdnsoft.dualsimringer.app.C = r10
        L_0x00b9:
            com.mdnsoft.dualsimringer.app.A = r1
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mdnsoft.dualsimringer.app.c():void");
    }

    private static String d() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis()));
    }

    @Override // android.app.Application
    public final void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new R());
        super.onCreate();
        K = this;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String string = defaultSharedPreferences.getString("pLanguage", "");
        m = string;
        if (!string.equals("")) {
            a(this, m);
        }
        b = (NotificationManager) getSystemService("notification");
        d = defaultSharedPreferences.getBoolean("pDebug", false);
        if (c) {
            d = true;
        }
        try {
            H = new PrintWriter(openFileOutput("DualSIMRingerlogerr.txt", 32768));
            if (c) {
                G = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/sdcard/DualSIMRinger.txt", true)));
            } else if (d) {
                c = true;
                SecretKeySpec secretKeySpec = new SecretKeySpec("com.mdnsoft.call".getBytes(), "AES");
                Cipher instance = Cipher.getInstance("AES");
                instance.init(1, secretKeySpec);
                G = new PrintWriter(new CipherOutputStream(openFileOutput("DualSIMRingerlog.txt", 32768), instance));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a(G, "on App Create DSR");
        try {
            a(G, "version:".concat(String.valueOf(K.getApplicationContext().getPackageManager().getPackageInfo(K.getApplicationContext().getPackageName(), 0).versionName)));
        } catch (PackageManager.NameNotFoundException e3) {
        }
        l = new File("/dev/socket/rild-mtk-modem").exists();
        e = Integer.parseInt(defaultSharedPreferences.getString("pMode", "0"));
        if (l && !defaultSharedPreferences.contains("pSMSMode")) {
            defaultSharedPreferences.edit().putString("pSMSMode", "1").commit();
        }
        f = Integer.parseInt(defaultSharedPreferences.getString("pSMSMode", "0"));
        if (ActPerm.a(getApplicationContext()).size() > 0) {
            a(G, "invalid permissions:" + ActPerm.a(getApplicationContext()).toString());
            Intent intent = new Intent(this, ActPerm.class);
            intent.addFlags(268435456);
            startActivity(intent);
            return;
        }
        S.c();
        a(G, S.d());
        b();
        a = S.c("android.permission.RECEIVE_SMS");
        a(G, "End on App Create DSR, perm=" + a);
        D = openOrCreateDatabase("Data.db", 0, null);
        D.execSQL("CREATE TABLE IF NOT EXISTS tblog(date BIGINT, type INTEGER, r INTEGER, answer TEXT )");
        try {
            c();
        } catch (Exception e4) {
            a(G, "error RE" + e4.getMessage());
            C = 0;
            A = true;
        }
        Intent intent2 = new Intent(this, DataService.class);
        if (!z || Build.VERSION.SDK_INT < 26) {
            try {
                startService(intent2);
            } catch (IllegalStateException e5) {
                if (Build.VERSION.SDK_INT >= 26) {
                    z = true;
                    startForegroundService(intent2);
                }
            }
        } else {
            startForegroundService(intent2);
        }
        try {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        } catch (Exception e6) {
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
    }
}
