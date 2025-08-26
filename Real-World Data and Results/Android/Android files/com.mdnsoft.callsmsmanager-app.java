package com.mdnsoft.callsmsmanager;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcel;
import android.os.PowerManager;
import android.os.Process;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.vending.licensing.AESObfuscator;
import com.mdnsoft.smsapp.Journal;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class app extends Application {
    public static String C;
    public static String D;
    public static AlarmManager aC;
    public static PowerManager aD;
    public static PowerManager.WakeLock aE;
    public static PrintWriter aG;
    public static PrintWriter aH;
    public static String aX;
    public static boolean aY;
    public static String[] bQ;
    public static String[] bR;
    private static String cD;
    private static String cE;
    private static app cH;
    public static boolean cu;
    public static boolean cv;
    public static String cw;
    public static SQLiteDatabase v;
    public static boolean a = false;
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;
    public static boolean e = false;
    public static boolean f = false;
    public static boolean g = false;
    public static boolean h = true;
    public static boolean i = false;
    public static int j = 1;
    public static boolean k = false;
    public static boolean l = false;
    public static boolean m = false;
    static boolean n = false;
    static boolean o = false;
    static boolean p = false;
    public static boolean q = false;
    private static boolean cC = false;
    public static boolean r = true;
    public static boolean s = false;
    public static long t = 0;
    public static boolean u = false;
    public static SQLiteDatabase w = null;
    static String x = "";
    public static boolean y = true;
    public static Process z = null;
    public static OutputStreamWriter A = null;
    public static BufferedReader B = null;
    public static int E = 0;
    public static int F = 0;
    public static int G = 0;
    public static int H = 0;
    public static boolean I = false;
    public static int J = 12;
    public static boolean K = false;
    public static int L = 0;
    public static int M = 0;
    public static int N = 0;
    public static boolean O = false;
    public static boolean P = false;
    public static boolean Q = false;
    public static boolean R = true;
    public static boolean S = false;
    public static boolean T = false;
    private static int cF = -1;
    public static double U = -1.0d;
    public static int V = -1;
    public static int W = 0;
    public static boolean X = true;
    public static int Y = 0;
    public static int Z = 0;
    public static float aa = 1.0f;
    public static boolean ab = false;
    public static boolean ac = false;
    public static boolean ad = false;
    public static boolean ae = false;
    public static boolean af = false;
    public static boolean ag = false;
    public static int ah = 8;
    public static boolean ai = false;
    public static boolean aj = false;
    public static boolean ak = false;
    public static boolean al = false;
    public static boolean am = false;
    public static int an = 0;
    public static boolean ao = false;
    public static boolean ap = false;
    public static boolean aq = false;
    public static boolean ar = false;
    private static boolean cG = false;
    public static boolean as = false;
    public static boolean at = false;
    public static boolean au = false;
    public static int av = 5;
    public static boolean aw = true;
    public static int ax = 20;
    public static int ay = 0;
    public static String az = "";
    public static String aA = "dd.MM.yyyy HH:mm:ss";
    public static String aB = "";
    public static Handler aF = new Handler();
    public static int aI = 0;
    public static boolean aJ = false;
    public static boolean aK = false;
    public static boolean aL = false;
    public static boolean aM = false;
    public static boolean aN = false;
    public static boolean aO = false;
    public static boolean aP = false;
    public static int aQ = 0;
    public static String aR = "";
    public static String aS = "";
    public static boolean aT = true;
    public static int aU = -1;
    public static int aV = -1;
    public static boolean aW = false;
    public static int aZ = 0;
    public static long ba = 0;
    public static long bb = 0;
    public static long bc = 0;
    public static Object bd = null;
    public static String be = "";
    public static int bf = -1;
    public static boolean bg = false;
    public static int bh = 0;
    public static boolean bi = false;
    public static long bj = 0;
    public static int bk = -1;
    public static int bl = 11;
    public static boolean bm = true;
    public static boolean bn = false;
    public static int bo = 1;
    public static boolean bp = false;
    public static int bq = 5;
    public static boolean br = false;
    public static int bs = 0;
    public static String bt = "";
    public static boolean bu = false;
    public static boolean bv = false;
    public static boolean bw = false;
    public static boolean bx = false;
    public static boolean by = false;
    public static boolean bz = false;
    public static String bA = "/sdcard";
    public static String bB = "";
    public static boolean bC = false;
    public static boolean bD = false;
    public static boolean bE = false;
    public static boolean bF = false;
    public static boolean bG = false;
    public static boolean bH = false;
    public static boolean bI = false;
    public static boolean bJ = false;
    public static boolean bK = false;
    public static int bL = 0;
    private static boolean cI = true;
    public static boolean bM = false;
    public static boolean bN = false;
    public static boolean bO = false;
    public static boolean bP = false;
    static HashSet bS = null;
    static HashSet bT = null;
    static HashSet bU = null;
    static HashSet bV = null;
    static HashSet bW = null;
    public static boolean bX = false;
    public static String bY = "";
    public static boolean bZ = false;
    public static boolean ca = false;
    public static int cb = 0;
    public static int cc = 0;
    public static int cd = 1;
    public static int ce = 1;
    public static boolean cf = false;
    public static boolean cg = false;
    public static boolean ch = false;
    public static boolean ci = false;
    public static boolean cj = false;
    public static int ck = 15;
    public static int cl = 0;
    public static boolean cm = false;
    public static boolean cn = false;
    public static boolean co = false;
    public static boolean cp = false;
    public static boolean cq = true;
    public static boolean cr = false;
    public static boolean cs = false;
    public static boolean ct = false;
    private static Thread cJ = null;
    public static ArrayList cx = new ArrayList();
    static Socket cy = null;
    static int cz = 5999;
    static OutputStream cA = null;
    static InputStream cB = null;
    private static Thread cK = null;

    /* renamed from: com.mdnsoft.callsmsmanager.app$16  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass16 implements Runnable {
        private final /* synthetic */ long a;
        private final /* synthetic */ long b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                app.a(this.a, this.b);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.mdnsoft.callsmsmanager.app$18  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass18 implements Runnable {
        private final /* synthetic */ long a;
        private final /* synthetic */ long b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                app.b(this.a, this.b);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.mdnsoft.callsmsmanager.app$2  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass2 implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            app.p();
        }
    }

    /* renamed from: com.mdnsoft.callsmsmanager.app$20  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass20 implements Runnable {
        private final /* synthetic */ String a;
        private final /* synthetic */ long b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                app.a(this.a, this.b);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.mdnsoft.callsmsmanager.app$22  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass22 implements Runnable {
        private final /* synthetic */ String a;
        private final /* synthetic */ long b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                app.b(this.a, this.b);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.mdnsoft.callsmsmanager.app$24  reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass24 implements Runnable {
        private final /* synthetic */ long a;
        private final /* synthetic */ long b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                app.c(this.a, this.b);
            } catch (Exception e) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class Notifications {
        int a;
        int b;
        int c;
        String d;
        String e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long a(String str, String str2, long j2, int i2, int i3, long j3) {
        int i4;
        try {
            Cursor query = cH.getApplicationContext().getContentResolver().query(Uri.parse("content://sms/"), null, "type=1", null, "_id desc LIMIT 0");
            i4 = query.getColumnIndex("sub_id") == -1 ? -1 : i3;
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            i4 = -1;
        }
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.writeInt(3);
        obtain.writeString(str);
        obtain.writeString(str2);
        obtain.writeLong(j2);
        obtain.writeInt(i2);
        obtain.writeInt(i4);
        obtain.writeLong(j3);
        byte[] marshall = obtain.marshall();
        Util.a(marshall, 0, Util.h(marshall.length - 4));
        obtain.recycle();
        try {
            cA.write(marshall);
            cA.flush();
            return 0;
        } catch (Exception e3) {
            try {
                q();
                Thread.sleep(2000);
                cA.write(marshall);
                cA.flush();
                return 0;
            } catch (Exception e4) {
                return -1;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long a(String str, String str2, long j2, int i2, int i3, long j3, int i4) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.writeInt(4);
        obtain.writeString(str);
        obtain.writeString(str2);
        obtain.writeLong(j2);
        obtain.writeInt(i2);
        obtain.writeInt(i3);
        obtain.writeLong(j3);
        obtain.writeInt(i4);
        byte[] marshall = obtain.marshall();
        Util.a(marshall, 0, Util.h(marshall.length - 4));
        obtain.recycle();
        try {
            cA.write(marshall);
            cA.flush();
            return 0;
        } catch (Exception e2) {
            try {
                q();
                Thread.sleep(2000);
                cA.write(marshall);
                cA.flush();
                return 0;
            } catch (Exception e3) {
                return -1;
            }
        }
    }

    public static app a() {
        return cH;
    }

    public static String a(long j2) {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).format(Long.valueOf(j2));
    }

    public static String a(String str, int i2) {
        return new AESObfuscator(DataService.x, cH.getPackageName(), Settings.Secure.getString(cH.getContentResolver(), "android_id")).a(String.valueOf(i2), str);
    }

    public static void a(int i2) {
        int i3;
        int i4;
        long j2 = 300;
        int i5 = -1;
        b(aG, "killCall");
        if (Build.VERSION.SDK_INT >= 26) {
            final TelecomManager telecomManager = (TelecomManager) cH.getApplicationContext().getSystemService("telecom");
            telecomManager.acceptRingingCall();
            long j3 = (long) L;
            if (j3 >= 300) {
                j2 = j3;
            }
            try {
                Thread.sleep(j2);
            } catch (InterruptedException e2) {
            }
            if (Build.VERSION.SDK_INT >= 28) {
                telecomManager.endCall();
            } else {
                j();
            }
            new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e3) {
                    }
                    TelephonyManager telephonyManager = (TelephonyManager) app.a().getApplicationContext().getSystemService("phone");
                    app.b(app.aG, "State:" + telephonyManager.getCallState());
                    if (telephonyManager.getCallState() != 0) {
                        app.b(app.aG, "killCall1");
                        if (Build.VERSION.SDK_INT >= 28) {
                            telecomManager.endCall();
                        } else {
                            app.j();
                        }
                    }
                }
            }).start();
        } else if (!q) {
            try {
                Class<?> cls = Class.forName("com.android.internal.telephony.ITelephony$Stub");
                try {
                    Field declaredField = cls.getDeclaredField("TRANSACTION_answerRingingCall");
                    declaredField.setAccessible(true);
                    i3 = declaredField.getInt(null);
                } catch (Exception e3) {
                    i3 = -1;
                }
                try {
                    Field declaredField2 = cls.getDeclaredField("TRANSACTION_answerRingingCallForSubscriber");
                    declaredField2.setAccessible(true);
                    i4 = declaredField2.getInt(null);
                } catch (Exception e4) {
                    i4 = -1;
                }
                try {
                    Field declaredField3 = cls.getDeclaredField("TRANSACTION_endCall");
                    declaredField3.setAccessible(true);
                    i5 = declaredField3.getInt(null);
                } catch (Exception e5) {
                    a(aG, "e tendcall:" + e5.getMessage());
                }
                if (z == null) {
                    e();
                }
                int t2 = Util.t(i2);
                if (i4 > 0 && t2 > 0) {
                    A.write("service call phone " + i4 + " i32 " + t2 + "\n");
                    A.flush();
                    a(aG, "acall");
                } else if (i3 > 0) {
                    A.write("service call phone " + i3 + "\n");
                    A.flush();
                    a(aG, "acall2");
                } else {
                    try {
                        Field declaredField4 = Class.forName("com.android.internal.telecom.ITelecomService$Stub").getDeclaredField("TRANSACTION_acceptRingingCall");
                        declaredField4.setAccessible(true);
                        A.write("service call telecom " + declaredField4.getInt(null) + "\n");
                        A.flush();
                        a(aG, "acall3");
                    } catch (Exception e6) {
                    }
                }
                Thread.sleep(1000);
                Thread.sleep((long) L);
                if (Build.VERSION.SDK_INT >= 26) {
                    j();
                } else if (i5 > 0) {
                    A.write("service call phone " + i5 + "\n");
                    A.flush();
                } else {
                    u();
                }
            } catch (Exception e7) {
                a(aG, "Error killCall:" + e7.getMessage() + "-" + e7.getCause());
            }
        } else {
            k();
            new Timer().schedule(new TimerTask() { // from class: com.mdnsoft.callsmsmanager.app.8
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    app.j();
                }
            }, 2000);
        }
    }

    public static void a(int i2, String str, String str2, int i3, int i4, int i5, int i6) {
        boolean z2;
        int i7 = 2131230929;
        if (i6 > 1000) {
            i6 /= 1000;
            z2 = true;
        } else {
            z2 = false;
        }
        String str3 = "";
        try {
            switch (i2) {
                case -2:
                    str3 = "RCM_sms_answer";
                    break;
                case 1:
                    str3 = "RCM_call";
                    break;
                case 2:
                    str3 = "RCM_sms";
                    break;
                case 4:
                    str3 = "RCM_mms";
                    break;
                case 11:
                    str3 = "RCM_call_out";
                    break;
                case 12:
                    str3 = "RCM_sms_out";
                    break;
                case 14:
                    str3 = "RCM_ussd";
                    break;
            }
            Intent intent = new Intent(str3);
            intent.putExtra("number", str);
            intent.putExtra("name", Util.e(str));
            intent.putExtra("list", i5 != 0 ? Lists.a(aU) : Lists.a(i5));
            if ((i2 == 2 || i2 == -2 || i2 == 12 || i2 == 14) && str2 != null) {
                intent.putExtra("msg", str2);
            }
            if (i3 == -1) {
                i3 = 0;
            }
            intent.putExtra("slot", i3 + 1);
            if (i6 != -1) {
                if (i2 != 2) {
                    switch (i6) {
                        case 1:
                            i7 = 2131230767;
                            break;
                        case 2:
                            i7 = 2131230766;
                            break;
                        case 3:
                            i7 = 2131230768;
                            break;
                        case 4:
                            break;
                        case 5:
                            i7 = 2131230956;
                            break;
                        case 6:
                            i7 = 2131230956;
                            break;
                        case 7:
                        default:
                            i7 = 1;
                            break;
                        case 8:
                            i7 = 2131231008;
                            break;
                    }
                } else if (i6 != 1) {
                    i7 = 2131230934;
                }
                intent.putExtra("handler", cH.getApplicationContext().getString(i7));
                if (z2) {
                    intent.putExtra("keeper", 1);
                }
            }
            if (!(Lists.b(aU) == 0 || i5 == 0)) {
                i4 = -1;
            }
            if (i4 != -1) {
                Cursor rawQuery = v.rawQuery("select * from tbNumberList where n_id=".concat(String.valueOf(i4)), null);
                if (rawQuery.moveToFirst()) {
                    intent.putExtra("rcmname", rawQuery.getString(rawQuery.getColumnIndex("Name")));
                    int i8 = rawQuery.getInt(rawQuery.getColumnIndex("NumberType"));
                    if (i8 == 1) {
                        intent.putExtra("group", Util.e(rawQuery.getInt(rawQuery.getColumnIndex("Number"))));
                    }
                    if (i8 == 6) {
                        intent.putExtra("rcmgroup", Groups.a(rawQuery.getInt(rawQuery.getColumnIndex("Number"))));
                        int a2 = NumberTest.a(rawQuery.getInt(rawQuery.getColumnIndex("Number")), str);
                        if (a2 != -1) {
                            intent.putExtra("group", Util.e(a2));
                        }
                    }
                }
                rawQuery.close();
            }
            cH.getApplicationContext().sendBroadcast(intent);
        } catch (Exception e2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(long j2, long j3) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.writeInt(1);
        obtain.writeLong(j2);
        obtain.writeLong(j3);
        final byte[] marshall = obtain.marshall();
        Util.a(marshall, 0, Util.h(marshall.length - 4));
        obtain.recycle();
        try {
            cA.write(marshall);
            cA.flush();
        } catch (Exception e2) {
            if (cG) {
                Util.a(new String[]{"1", String.valueOf(j2), String.valueOf(j3)});
                return;
            }
            try {
                new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.17
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            app.r();
                            app.cA.write(marshall);
                            app.cA.flush();
                        } catch (Exception e3) {
                        }
                    }
                }).start();
            } catch (Exception e3) {
            }
        }
    }

    public static void a(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(2131230733);
        builder.setPositiveButton(2131230728, new DialogInterface.OnClickListener() { // from class: com.mdnsoft.callsmsmanager.app.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                Intent intent = new Intent(app.a().getApplicationContext(), LicenseDialog.class);
                intent.addFlags(268435456);
                app.a().getApplicationContext().startActivity(intent);
            }
        });
        builder.setNeutralButton(2131230763, (DialogInterface.OnClickListener) null);
        builder.create().show();
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
            printWriter.println(String.valueOf(s()) + " :" + str);
            printWriter.flush();
        }
    }

    public static void a(final String str) {
        aF.post(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.1
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(app.a().getApplicationContext(), str, 1).show();
            }
        });
    }

    static void a(String str, long j2) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.writeInt(11);
        obtain.writeString(str);
        obtain.writeLong(j2);
        final byte[] marshall = obtain.marshall();
        Util.a(marshall, 0, Util.h(marshall.length - 4));
        obtain.recycle();
        try {
            cA.write(marshall);
            cA.flush();
        } catch (Exception e2) {
            try {
                new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.21
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            app.r();
                            app.cA.write(marshall);
                            app.cA.flush();
                        } catch (Exception e3) {
                        }
                    }
                }).start();
            } catch (Exception e3) {
            }
        }
    }

    private static String b(int i2) {
        switch (i2) {
            case 0:
                return "android.telephony.gemini.GeminiSmsManager";
            case 1:
                return "android.plugin.PlugInServiceManager";
            case 2:
                return "android.telephony.MSimSmsManager";
            case 3:
                return "android.telephony.HtcIfSmsManager";
            case 4:
            case 5:
            case 8:
            case 9:
            default:
                return "android.telephony.SmsManager";
            case 6:
                return "android.telephony.MultiSimSmsManager";
            case 7:
                return "com.htc.wrap.android.telephony.HtcWrapIfSmsManager";
            case 10:
                return "com.mediatek.telephony.SmsManagerEx";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b() {
        /*
            r8 = 0
            r2 = 0
            r1 = 1
            android.database.sqlite.SQLiteDatabase r0 = com.mdnsoft.callsmsmanager.app.v
            java.lang.String r3 = "select date, r, answer from tblog where r in(0,1) order by date desc Limit 1"
            r4 = 0
            android.database.Cursor r3 = r0.rawQuery(r3, r4)
            if (r3 == 0) goto L_0x0073
            boolean r0 = r3.moveToFirst()
            if (r0 == 0) goto L_0x0073
            long r4 = r3.getLong(r2)
            int r6 = r3.getInt(r1)
            r0 = 2
            java.lang.String r0 = r3.getString(r0)
            java.lang.String r7 = java.lang.String.valueOf(r4)
            java.lang.String r7 = a(r7, r6)
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x006a
            if (r6 != 0) goto L_0x0065
            r0 = r1
        L_0x0033:
            com.mdnsoft.callsmsmanager.app.s = r0
            if (r6 != r1) goto L_0x0067
            long r0 = java.lang.System.currentTimeMillis()
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 >= 0) goto L_0x0067
            com.mdnsoft.callsmsmanager.app.t = r4
        L_0x0041:
            if (r3 == 0) goto L_0x0046
            r3.close()
        L_0x0046:
            boolean r0 = com.mdnsoft.callsmsmanager.Util.g()
            if (r0 == 0) goto L_0x004e
            com.mdnsoft.callsmsmanager.app.s = r2
        L_0x004e:
            java.io.PrintWriter r0 = com.mdnsoft.callsmsmanager.app.aG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "RL="
            r1.<init>(r2)
            boolean r2 = com.mdnsoft.callsmsmanager.app.s
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            a(r0, r1)
            return
        L_0x0065:
            r0 = r2
            goto L_0x0033
        L_0x0067:
            com.mdnsoft.callsmsmanager.app.t = r8
            goto L_0x0041
        L_0x006a:
            java.io.PrintWriter r0 = com.mdnsoft.callsmsmanager.app.aG
            java.lang.String r4 = "AES=false"
            a(r0, r4)
            com.mdnsoft.callsmsmanager.app.t = r8
        L_0x0073:
            com.mdnsoft.callsmsmanager.app.s = r1
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mdnsoft.callsmsmanager.app.b():void");
    }

    static void b(long j2, long j3) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.writeInt(5);
        obtain.writeLong(j2);
        obtain.writeLong(j3);
        final byte[] marshall = obtain.marshall();
        Util.a(marshall, 0, Util.h(marshall.length - 4));
        obtain.recycle();
        try {
            cA.write(marshall);
            cA.flush();
        } catch (Exception e2) {
            try {
                new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.19
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            app.r();
                            app.cA.write(marshall);
                            app.cA.flush();
                        } catch (Exception e3) {
                        }
                    }
                }).start();
            } catch (Exception e3) {
            }
        }
    }

    public static void b(Context context) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(cH.getApplicationContext());
        final Dialog dialog = new Dialog(context);
        dialog.setTitle(2131230721);
        dialog.setContentView(2130903104);
        ((Button) dialog.findViewById(2131427619)).setOnClickListener(new View.OnClickListener() { // from class: com.mdnsoft.callsmsmanager.app.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                edit.putBoolean("pnrate", true);
                edit.putLong("pdrate", System.currentTimeMillis());
                edit.commit();
                app.bi = defaultSharedPreferences.getBoolean("pnrate", false);
                app.bj = defaultSharedPreferences.getLong("pdrate", 0);
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mdnsoft.callsmsmanager"));
                    intent.addFlags(268435456);
                    app.a().getApplicationContext().startActivity(intent);
                } catch (Exception e2) {
                    try {
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.mdnsoft.callsmsmanager"));
                        intent2.addFlags(268435456);
                        app.a().getApplicationContext().startActivity(intent2);
                    } catch (Exception e3) {
                    }
                }
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(2131427620)).setOnClickListener(new View.OnClickListener() { // from class: com.mdnsoft.callsmsmanager.app.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                edit.putLong("pdrate", System.currentTimeMillis());
                edit.commit();
                app.bi = defaultSharedPreferences.getBoolean("pnrate", false);
                app.bj = defaultSharedPreferences.getLong("pdrate", 0);
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(2131427621)).setOnClickListener(new View.OnClickListener() { // from class: com.mdnsoft.callsmsmanager.app.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                edit.putBoolean("pnrate", true);
                edit.putLong("pdrate", System.currentTimeMillis());
                edit.commit();
                app.bi = defaultSharedPreferences.getBoolean("pnrate", false);
                app.bj = defaultSharedPreferences.getLong("pdrate", 0);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void b(PrintWriter printWriter, String str) {
        if (printWriter != null) {
            printWriter.println(String.valueOf(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis()))) + " :" + str);
            printWriter.flush();
        }
    }

    static void b(String str, long j2) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.writeInt(12);
        obtain.writeString(str);
        obtain.writeLong(j2);
        final byte[] marshall = obtain.marshall();
        Util.a(marshall, 0, Util.h(marshall.length - 4));
        obtain.recycle();
        try {
            cA.write(marshall);
            cA.flush();
        } catch (Exception e2) {
            try {
                new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.23
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            app.r();
                            app.cA.write(marshall);
                            app.cA.flush();
                        } catch (Exception e3) {
                        }
                    }
                }).start();
            } catch (Exception e3) {
            }
        }
    }

    public static String c() {
        String str = String.valueOf(String.valueOf(String.valueOf("") + "select 1 as _id, 0 as k, '" + cH.getApplicationContext().getString(2131230767) + "' as name \n") + "union all select 4 as _id, 5 as k, '" + cH.getApplicationContext().getString(2131230929) + "' as name \n") + "union all select 11 as _id, 6 as k, '" + cH.getApplicationContext().getString(2131230756) + "' as name \n";
        if ((!q && !cs) || ct) {
            String str2 = String.valueOf(str) + "union all select 2 as _id, 2 as k, '" + cH.getApplicationContext().getString(2131230766) + "' as name \n";
            if (S) {
                str2 = String.valueOf(str2) + "union all select 3 as _id, 1 as k, '" + cH.getApplicationContext().getString(2131230768) + "' as name \n";
            }
            str = String.valueOf(String.valueOf(str2) + "union all select 5 as _id, 3 as k, '" + cH.getApplicationContext().getString(2131230956) + "' as name \n") + "union all select 8 as _id, 8 as k, '" + cH.getApplicationContext().getString(2131231008) + "' as name \n";
        }
        if (cs && !ct) {
            str = String.valueOf(str) + "union all select 5 as _id, 3 as k, '" + cH.getApplicationContext().getString(2131230956) + "' as name \n";
        }
        if (Util.A()) {
            str = String.valueOf(str) + "union select 2 as _id, 2 as k, '" + cH.getApplicationContext().getString(2131230766) + "' as name \n";
        }
        return String.valueOf(str) + "order by k";
    }

    static void c(long j2, long j3) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInt(0);
        obtain.writeInt(2);
        obtain.writeLong(j2);
        obtain.writeLong(j3);
        final byte[] marshall = obtain.marshall();
        Util.a(marshall, 0, Util.h(marshall.length - 4));
        obtain.recycle();
        try {
            cA.write(marshall);
            cA.flush();
        } catch (Exception e2) {
            try {
                new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.25
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            app.r();
                            app.cA.write(marshall);
                            app.cA.flush();
                        } catch (Exception e3) {
                        }
                    }
                }).start();
            } catch (Exception e3) {
            }
        }
    }

    public static void d() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(cH);
        if (Build.CPU_ABI.contains("x86") && new File(cH.getApplicationContext().getFilesDir() + "/rcm_x86_support.zip").exists()) {
            n = true;
        }
        if (!defaultSharedPreferences.contains("pTheme") && Build.VERSION.SDK_INT > 23) {
            SharedPreferences.Editor edit = defaultSharedPreferences.edit();
            edit.putString("pTheme", "4");
            edit.commit();
        }
        ay = Integer.parseInt(defaultSharedPreferences.getString("pTheme", Build.VERSION.SDK_INT > 23 ? "4" : "0"));
        az = defaultSharedPreferences.getString("pLanguage", "");
        aU = defaultSharedPreferences.getInt("pCurList", -1);
        a(aG, "InitPref,l_id=" + aU);
        if (!defaultSharedPreferences.contains("pHiding") && defaultSharedPreferences.getBoolean("pHide", false)) {
            SharedPreferences.Editor edit2 = defaultSharedPreferences.edit();
            edit2.putString("pHiding", "1");
            edit2.commit();
        }
        int parseInt = Integer.parseInt(defaultSharedPreferences.getString("pHiding", "0"));
        aI = parseInt;
        if (parseInt == 1 && !g && !Util.E()) {
            SharedPreferences.Editor edit3 = defaultSharedPreferences.edit();
            edit3.putString("pHiding", "0");
            edit3.commit();
            aI = 0;
            PackageManager packageManager = cH.getPackageManager();
            ComponentName componentName = new ComponentName(cH.getApplicationContext(), MainActivity.class);
            ComponentName componentName2 = new ComponentName(cH.getApplicationContext(), Notepad.class);
            ComponentName componentName3 = new ComponentName(cH.getApplicationContext(), Calculator.class);
            ComponentName componentName4 = new ComponentName(cH.getApplicationContext(), Widget.class);
            ComponentName componentName5 = new ComponentName(cH.getApplicationContext(), Widget2.class);
            packageManager.setComponentEnabledSetting(componentName, 0, 1);
            packageManager.setComponentEnabledSetting(componentName2, 2, 1);
            packageManager.setComponentEnabledSetting(componentName3, 2, 1);
            packageManager.setComponentEnabledSetting(componentName4, 0, 1);
            packageManager.setComponentEnabledSetting(componentName5, 0, 1);
        }
        aO = defaultSharedPreferences.getBoolean("pHidePrivate", false);
        aJ = defaultSharedPreferences.getBoolean("pProtection", false);
        aN = defaultSharedPreferences.getBoolean("pFingerPrint", false);
        aK = defaultSharedPreferences.getBoolean("pWProtection", false);
        aM = defaultSharedPreferences.getBoolean("pWHideIcon", false);
        aL = defaultSharedPreferences.getBoolean("pAutoInput", false);
        aR = defaultSharedPreferences.getString("pCode", "123");
        aS = defaultSharedPreferences.getString("pCodePrivate", "321");
        aT = defaultSharedPreferences.getBoolean("pForeground", true);
        if (Build.VERSION.SDK_INT >= 26 && !aT && !Util.b(cH)) {
            SharedPreferences.Editor edit4 = defaultSharedPreferences.edit();
            edit4.putBoolean("pForeground", true);
            edit4.commit();
            aT = true;
        }
        if (!defaultSharedPreferences.contains("pMode5Subtype")) {
            if (S) {
                SharedPreferences.Editor edit5 = defaultSharedPreferences.edit();
                edit5.putString("pMode5Subtype", "1");
                edit5.putInt("pwiteboot", 15);
                edit5.putBoolean("pRestart", true);
                edit5.putBoolean("p3gswitch", true);
                edit5.commit();
            }
            if (Build.MODEL.contains("HTC One dual sim")) {
                SharedPreferences.Editor edit6 = defaultSharedPreferences.edit();
                edit6.putString("pMode5Subtype", "1");
                edit6.putInt("pwiteboot", 45);
                edit6.commit();
            }
            if (Build.MODEL.startsWith("LG")) {
                SharedPreferences.Editor edit7 = defaultSharedPreferences.edit();
                edit7.putString("pMode5Subtype", "1");
                edit7.putInt("pwiteboot", 30);
                edit7.commit();
            }
            if (Build.MODEL.contains("SM-G935F")) {
                SharedPreferences.Editor edit8 = defaultSharedPreferences.edit();
                edit8.putString("pMode5Subtype", "3");
                edit8.commit();
            }
        }
        if (!defaultSharedPreferences.contains("pRestartSimMode")) {
            SharedPreferences.Editor edit9 = defaultSharedPreferences.edit();
            if (Build.VERSION.SDK_INT < 19) {
                edit9.putString("pRestartSimMode", "0");
            } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 21) {
                edit9.putString("pRestartSimMode", "2");
            } else {
                edit9.putString("pRestartSimMode", "1");
            }
            edit9.commit();
        }
        if (Build.BRAND.toUpperCase().contains("SAMSUNG") && Build.VERSION.SDK_INT >= 22 && !defaultSharedPreferences.contains("pRestart") && DataService.H > 0) {
            SharedPreferences.Editor edit10 = defaultSharedPreferences.edit();
            edit10.putBoolean("pRestart", true);
            edit10.commit();
        }
        defaultSharedPreferences.getBoolean("pSetenforce", false);
        E = defaultSharedPreferences.getInt("pwiteboot", 0);
        F = defaultSharedPreferences.getInt("pRestart1", 0);
        if (!Build.BRAND.toUpperCase().contains("SAMSUNG") || Build.VERSION.SDK_INT < 21) {
            F = 0;
        }
        G = Integer.parseInt(defaultSharedPreferences.getString("pMode5Subtype", "0"));
        H = Integer.parseInt(defaultSharedPreferences.getString("pProcessCallMode", "0"));
        O = defaultSharedPreferences.getBoolean("pRestart", false);
        aW = defaultSharedPreferences.getBoolean("pdoSchedule", false);
        cF = Integer.parseInt(defaultSharedPreferences.getString("pJcleaer", "0"));
        U = Double.parseDouble(defaultSharedPreferences.getString("pSmsPeriod", "1"));
        V = Integer.parseInt(defaultSharedPreferences.getString("pSmsSIM", "-1"));
        W = Integer.parseInt(defaultSharedPreferences.getString("pSmsSendingMode", "0"));
        Y = Integer.parseInt(defaultSharedPreferences.getString("pStartActivity", "0"));
        Z = Integer.parseInt(defaultSharedPreferences.getString("pJTab", "0"));
        aa = ((float) defaultSharedPreferences.getInt("pJScale", 100)) / 100.0f;
        ab = defaultSharedPreferences.getBoolean("pSelect_unread", false);
        X = defaultSharedPreferences.getBoolean("pPrivate", true);
        String string = defaultSharedPreferences.getString("version", "");
        bg = defaultSharedPreferences.getBoolean("pdelivery_report", false);
        bh = Integer.parseInt(defaultSharedPreferences.getString("pdelivery_report_restart", "0"));
        try {
            cD = cH.getApplicationContext().getPackageManager().getPackageInfo(cH.getApplicationContext().getPackageName(), 0).versionName;
            a(aG, "version:" + cD);
        } catch (Exception e2) {
        }
        if (!string.equals(cD)) {
            SharedPreferences.Editor edit11 = defaultSharedPreferences.edit();
            edit11.putString("version", cD);
            edit11.commit();
            u = true;
            if (Build.VERSION.SDK_INT >= 29) {
                Util.H();
            }
        }
        bA = defaultSharedPreferences.getString("pPath", Build.VERSION.SDK_INT < 29 ? Environment.getExternalStorageDirectory() + "/" + cH.getApplicationContext().getPackageName() : String.valueOf(Environment.getExternalStorageDirectory().getPath()) + "/Android/data/" + cH.getApplicationContext().getPackageName());
        if (Build.VERSION.SDK_INT >= 29 && new File(bA).exists() && !new File(bA).canWrite()) {
            bA = String.valueOf(Environment.getExternalStorageDirectory().getPath()) + "/Android/data/" + cH.getApplicationContext().getPackageName();
            cH.getApplicationContext().getExternalFilesDir(null);
            defaultSharedPreferences.edit().putString("pPath", bA).commit();
        }
        bB = "";
        ad = defaultSharedPreferences.getBoolean("pShake", false);
        ac = defaultSharedPreferences.getBoolean("pScreen_off", false);
        ae = defaultSharedPreferences.getBoolean("pCloseAct", false);
        ag = defaultSharedPreferences.getBoolean("pChangeSlot", false);
        af = defaultSharedPreferences.getBoolean("p3gswitch", false);
        if ((Build.BRAND.toUpperCase().equals("HUAWEI") || Build.MANUFACTURER.toUpperCase().equals("HUAWEI")) && !S && Build.VERSION.SDK_INT >= 21) {
            a(aG, "getDefaultDataSubId=" + Util.s());
            a(aG, "getPreferredNetworkType=" + Util.z(0) + "," + Util.z(1));
        }
        J = Integer.parseInt(defaultSharedPreferences.getString("pRejectType", "12"));
        K = defaultSharedPreferences.getBoolean("pApplyRejectTypeToKill", false);
        L = defaultSharedPreferences.getInt("pKillDelay", 0);
        M = defaultSharedPreferences.getInt("pRejectDelay", 0);
        N = Integer.parseInt(defaultSharedPreferences.getString("pPhoneType", "0"));
        ah = defaultSharedPreferences.getInt("pComparison", 8);
        am = defaultSharedPreferences.getBoolean("pBlockMMS", true);
        ai = defaultSharedPreferences.getBoolean("pGroupNotif", false);
        aj = defaultSharedPreferences.getBoolean("pHeadsUpNotif", false);
        ak = defaultSharedPreferences.getBoolean("pMIUINotifFix", false);
        al = defaultSharedPreferences.getBoolean("pNotifShowDateTime", false);
        an = Integer.parseInt(defaultSharedPreferences.getString("pClear_Notif", "0"));
        bi = defaultSharedPreferences.getBoolean("pnrate", false);
        if (!defaultSharedPreferences.contains("pdrate")) {
            SharedPreferences.Editor edit12 = defaultSharedPreferences.edit();
            edit12.putLong("pdrate", System.currentTimeMillis());
            edit12.commit();
        }
        bj = defaultSharedPreferences.getLong("pdrate", 0);
        if (!defaultSharedPreferences.contains("pWidgetLabelColor")) {
            SharedPreferences.Editor edit13 = defaultSharedPreferences.edit();
            edit13.putInt("pWidgetLabelColor", -1);
            edit13.commit();
        }
        bk = defaultSharedPreferences.getInt("pWidgetLabelColor", -1);
        bl = defaultSharedPreferences.getInt("pWdgFontSize", 11);
        bm = defaultSharedPreferences.getBoolean("pWdg_new", true);
        bn = defaultSharedPreferences.getBoolean("pWdg_scheduler", false);
        bo = Integer.parseInt(defaultSharedPreferences.getString("pWdg_new_clear", "1"));
        bp = defaultSharedPreferences.getBoolean("pWdgNotificationsOnly", false);
        bu = defaultSharedPreferences.getBoolean("bRoot", false);
        bv = defaultSharedPreferences.getBoolean("bCall", false);
        bw = defaultSharedPreferences.getBoolean("bSMS", false);
        a(aG, "bCall=" + bv + ",bSMS=" + bw);
        P = defaultSharedPreferences.getBoolean("pRestartInet", false);
        bq = defaultSharedPreferences.getInt("pMuteSMSTime", 5);
        bt = defaultSharedPreferences.getString("pSMSC", "");
        br = defaultSharedPreferences.getBoolean("pRestartSim", false);
        bs = Integer.parseInt(defaultSharedPreferences.getString("pRestartSimMode", "0"));
        bx = defaultSharedPreferences.getBoolean("pCheckNewNumberCall", false);
        by = defaultSharedPreferences.getBoolean("pCheckNewNumberCallOUT", false);
        bz = defaultSharedPreferences.getBoolean("pCheckNewNumberSMS", false);
        bC = defaultSharedPreferences.getBoolean("pHideSmsBody", false);
        bD = defaultSharedPreferences.getBoolean("pShowForwarding", false);
        bE = defaultSharedPreferences.getBoolean("pShowForwarding1", false);
        bF = defaultSharedPreferences.getBoolean("pDeleteMissedNot", false);
        bG = defaultSharedPreferences.getBoolean("pDeleteMissedNotPrivate", false);
        bH = defaultSharedPreferences.getBoolean("pDeleteSMSNotPrivate", false);
        aQ = defaultSharedPreferences.getInt("pWaitSMSOut", 0);
        bI = defaultSharedPreferences.getBoolean("pMuteVibraOff", false);
        bJ = defaultSharedPreferences.getBoolean("pMuteSetDoNotDisturb", false);
        bK = defaultSharedPreferences.getBoolean("pLED", false);
        ao = defaultSharedPreferences.getBoolean("pLowResolution", false);
        bM = defaultSharedPreferences.getBoolean("pmoveCall", false);
        bN = defaultSharedPreferences.getBoolean("pmoveSMS", false);
        bO = defaultSharedPreferences.getBoolean("pPrivBlockedCall", false);
        bP = defaultSharedPreferences.getBoolean("pPrivBlockedSMS", false);
        aP = defaultSharedPreferences.getBoolean("pClearFrequents", false);
        bX = defaultSharedPreferences.getBoolean("pSMSInsertReceive", false);
        ap = defaultSharedPreferences.getBoolean("pShowScheduleTime", false);
        aq = defaultSharedPreferences.getBoolean("pShowUnreadCount", false);
        ar = defaultSharedPreferences.getBoolean("pIntegration", false);
        bY = defaultSharedPreferences.getString("pMainNotifTitle", cH.getString(2131230721));
        cb = Integer.parseInt(defaultSharedPreferences.getString("pMainNotifPriority", "0"));
        cc = Integer.parseInt(defaultSharedPreferences.getString("pNotifPriority", "0"));
        bZ = defaultSharedPreferences.getBoolean("pShowWarning", false);
        cd = Integer.parseInt(defaultSharedPreferences.getString("pMarkCopyCall", "1"));
        ce = Integer.parseInt(defaultSharedPreferences.getString("pMarkMoveCall", "1"));
        cf = defaultSharedPreferences.getBoolean("pConfirmOUTCall", false);
        cg = defaultSharedPreferences.getBoolean("pConfirmOUTSMS", false);
        ch = defaultSharedPreferences.getBoolean("pConfirmOUTUSSD", false);
        ci = defaultSharedPreferences.getBoolean("pConfirmOUTRoaming", false);
        ck = defaultSharedPreferences.getInt("pConfirmOUTTime", 15);
        cl = Integer.parseInt(defaultSharedPreferences.getString("pConfirmOUTDefaultAction", "0"));
        cj = defaultSharedPreferences.getBoolean("pShowProcessOUTNot", false);
        cp = defaultSharedPreferences.getBoolean("pcheckMute", false);
        aB = defaultSharedPreferences.getString("pNoBlockingName", cH.getString(2131230745));
        cr = defaultSharedPreferences.getBoolean("pClockScheduler", false);
        if (Build.BRAND.equals("ZTE") && Build.VERSION.SDK_INT >= 21) {
            cG = true;
        }
        ca = defaultSharedPreferences.getBoolean("pHide_not_noblocking", false);
        R = defaultSharedPreferences.getBoolean("pProcessSMSF", true);
        cs = Build.VERSION.SDK_INT >= 26;
        Q = defaultSharedPreferences.getBoolean("pDisableService", false);
        I = defaultSharedPreferences.getBoolean("pProcessVoIPCalls", cs);
        cC = (Build.MODEL.equals("X9009") && Build.MANUFACTURER.equals("OPPO")) || Build.MODEL.equals("LG-H918");
        I = I || cC;
        boolean z2 = defaultSharedPreferences.getBoolean("pnoRoot", false);
        q = z2;
        if (z2) {
            a(aG, "noRoot!!!");
        }
        cq = defaultSharedPreferences.getBoolean("pMuteCalls", true);
        aA = defaultSharedPreferences.getString("pDateTimeFormat", "dd.MM.yyyy HH:mm:ss");
        if (cs) {
            Q = true;
            I = true;
            if (!defaultSharedPreferences.contains("pProcessVoIPCalls")) {
                defaultSharedPreferences.edit().putBoolean("pProcessVoIPCalls", true).commit();
            }
        }
        ct = defaultSharedPreferences.getBoolean("pXposed", false);
        a(aG, "Xposed=" + ct);
        if (ct) {
            Q = true;
        }
        r = defaultSharedPreferences.getBoolean("pquery_default_dialer", true);
        h = defaultSharedPreferences.getBoolean("pCheckUpdate", true);
        i = defaultSharedPreferences.getBoolean("pRadioFix", false);
        as = defaultSharedPreferences.getBoolean("pShotCalls", false);
        av = defaultSharedPreferences.getInt("pShotCallsDuration", 5);
        at = defaultSharedPreferences.getBoolean("pShotCallsNotification", false);
        au = defaultSharedPreferences.getBoolean("pShotCallsDeleteMissedNot", false);
        aw = defaultSharedPreferences.getBoolean("pUSSDWithoutQueryOnly", true);
        ax = defaultSharedPreferences.getInt("pUSSDTimeout", 20);
        j = defaultSharedPreferences.getInt("pCheckFrequencyGroups", 0);
    }

    public static void e() {
        a(aG, "GetRoot");
        if (q) {
            a(aG, "noRoot");
            return;
        }
        if (z != null) {
            try {
                A.write("exit\n");
                A.flush();
                A.close();
                A = null;
                B = null;
                z = null;
            } catch (Exception e2) {
            }
        }
        try {
            try {
                cH.sendBroadcast(new Intent("com.mdnsoft.getroot"));
                z = Runtime.getRuntime().exec("su");
                A = new OutputStreamWriter(z.getOutputStream());
                B = new BufferedReader(new InputStreamReader(z.getInputStream()));
                if (l || Build.VERSION.SDK_INT >= 21) {
                    Thread thread = new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.6
                        @Override // java.lang.Runnable
                        public void run() {
                            String readLine;
                            app.a(app.aG, "##start");
                            while (app.B != null && (readLine = app.B.readLine()) != null) {
                                try {
                                    if (readLine.contains("Enforcing")) {
                                        app.cu = true;
                                    } else if (readLine.contains("Permissive")) {
                                        app.cu = false;
                                    }
                                    if (readLine.contains("context=u:r:supersu:s0")) {
                                        app.cw = "supersu";
                                    }
                                    if (readLine.contains("context=u:r:su:s0")) {
                                        app.cw = "su";
                                    }
                                    if (readLine.contains("radio.sh") || readLine.contains("radio_magisksu.sh")) {
                                        app.cv = true;
                                    }
                                    app.a(app.aG, "##".concat(String.valueOf(readLine)));
                                } catch (Exception e3) {
                                }
                            }
                            app.a(app.aG, "##end");
                        }
                    });
                    cJ = thread;
                    thread.start();
                }
                A.write("id\n");
                A.flush();
                A.write("ls -l /su/su.d\n");
                A.flush();
                if (Build.VERSION.SDK_INT >= 24) {
                    A.write("chmod 755 /su/su.d/radio.sh\n");
                    A.flush();
                }
                if (l) {
                    A.write("cat /su/su.d/radio.sh\n");
                    A.flush();
                }
                if (!bu) {
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(cH).edit();
                    edit.putBoolean("bRoot", true);
                    edit.commit();
                    bu = true;
                }
            } catch (Exception e3) {
                a(aG, "Error GetRoot@:" + e3.getMessage());
                Thread.sleep(500);
                Intent intent = new Intent(cH.getApplicationContext(), ActDlgRoot.class);
                intent.addFlags(268435456);
                if (!ct) {
                    cH.getApplicationContext().startActivity(intent);
                }
            }
            if (Build.VERSION.SDK_INT > 17) {
                A.write("getenforce\n");
                A.flush();
            }
            try {
                t();
            } catch (Exception e4) {
            }
        } catch (Exception e5) {
            a(aG, "Error GetRoot1:" + e5.getMessage());
        }
    }

    public static void f() {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 26 && b) {
            String str = cH.getApplicationContext().getFilesDir() + "/sepolicy-inject";
            if (new File(str).exists()) {
                a(aG, "do sepolicy-inject");
                try {
                    A.write("chmod 777 " + str + "\n");
                    A.flush();
                    A.write(String.valueOf(str) + " -s radio -t sudaemon -c unix_stream_socket -p connectto -l\n");
                    A.flush();
                } catch (Exception e2) {
                }
            }
        }
    }

    public static void g() {
        a(aG, "RadioFix");
        try {
            A.write("magiskpolicy --live \"allow radio {magisk su} unix_stream_socket connectto\"\n");
            A.write("supolicy --live \"allow radio {magisk su} unix_stream_socket connectto\"\n");
            A.write("supolicy --live \"allow radio supersu unix_stream_socket connectto\"\n");
            A.flush();
        } catch (Exception e2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void h() {
        Util.y();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object i() {
        Class<?> cls;
        Object obj = null;
        int i2 = 0;
        try {
            Object invoke = Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "phone");
            Class<?>[] declaredClasses = Class.forName("com.android.internal.telephony.ITelephony").getDeclaredClasses();
            int length = declaredClasses.length;
            while (true) {
                if (i2 < length) {
                    cls = declaredClasses[i2];
                    if (cls.getSimpleName().equals("Stub")) {
                        break;
                    }
                    i2++;
                } else {
                    cls = null;
                    break;
                }
            }
            if (cls == null) {
                return null;
            }
            obj = cls.getDeclaredMethod("asInterface", Class.forName("android.os.IBinder")).invoke(null, invoke);
            return obj;
        } catch (Exception e2) {
            return obj;
        }
    }

    public static void j() {
        b(aG, "endCall");
        try {
            if (Build.VERSION.SDK_INT < 28) {
                Object i2 = i();
                bd = i2;
                i2.getClass().getMethod("endCall", null).invoke(bd, null);
            } else {
                ((TelecomManager) cH.getApplicationContext().getSystemService("telecom")).endCall();
            }
        } catch (Exception e2) {
            a(aG, "Error endCall:" + e2.getMessage() + "-" + e2.getCause());
            u();
        }
        if (Build.VERSION.SDK_INT >= 26 && !ct && !Util.A() && r) {
            Context applicationContext = cH.getApplicationContext();
            Intent intent = new Intent(applicationContext, ActDlgDefaultDialer.class);
            intent.addFlags(268435456);
            applicationContext.startActivity(intent);
        }
    }

    public static void k() {
        a(aG, "AnswerCall");
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                ((TelecomManager) cH.getApplicationContext().getSystemService("telecom")).acceptRingingCall();
                return;
            } catch (Exception e2) {
                a(aG, "E acceptRingingCall:" + e2.getMessage() + "," + e2.getCause());
            }
        }
        if (!v()) {
            try {
                Runtime.getRuntime().exec("input keyevent 79");
            } catch (Exception e3) {
                Intent putExtra = new Intent("android.intent.action.MEDIA_BUTTON").putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, 79));
                Intent putExtra2 = new Intent("android.intent.action.MEDIA_BUTTON").putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(1, 79));
                cH.getApplicationContext().sendOrderedBroadcast(putExtra, "android.permission.CALL_PRIVILEGED");
                cH.getApplicationContext().sendOrderedBroadcast(putExtra2, "android.permission.CALL_PRIVILEGED");
            }
        }
    }

    public static void l() {
        if (cF != 0) {
            Calendar instance = Calendar.getInstance();
            if (cF > 0) {
                instance.add(2, -cF);
            } else {
                instance.add(5, cF);
            }
            long timeInMillis = instance.getTimeInMillis();
            Cursor rawQuery = v.rawQuery("select date from vJournal where date<" + timeInMillis + " order by Date  Limit 1", null);
            if (rawQuery != null && rawQuery.moveToFirst()) {
                v.execSQL("delete from call_log where date<".concat(String.valueOf(timeInMillis)));
                v.execSQL("delete from sms_log where date<".concat(String.valueOf(timeInMillis)));
                v.execSQL("delete from mms_log where date<".concat(String.valueOf(timeInMillis)));
                v.execSQL("delete from mms_part where exists(select * from tbPrivateLog where mms_part.[mid]=tbPrivateLog.[_id] and date<" + timeInMillis + ")");
                v.execSQL("delete from tbPrivateLog where date<".concat(String.valueOf(timeInMillis)));
                v.execSQL("VACUUM");
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            v.execSQL("delete from tbAnswers where date<" + (System.currentTimeMillis() - 86400000));
            Calendar instance2 = Calendar.getInstance();
            instance2.add(5, -3);
            Util.e(instance2.getTimeInMillis());
        }
    }

    static void m() {
        a(aG, "Restart App");
        if (z == null) {
            e();
        }
        try {
            A.write("am start -S --ez restart true com.mdnsoft.callsmsmanager/.MainActivity\n");
            A.flush();
        } catch (Exception e2) {
        }
    }

    public static void n() {
        Widget.a(cH.getApplicationContext());
        Widget2.a(cH.getApplicationContext());
    }

    public static void o() {
        Intent intent = new Intent("RCM_list");
        intent.putExtra("list", Lists.a(aU));
        cH.getApplicationContext().sendBroadcast(intent);
    }

    public static void p() {
        int a2;
        a(aG, "Start read Rthread");
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                a2 = DataService.a(cB, bArr);
                a(aG, "read " + a2 + " byte from Rsocket:" + Util.a(bArr, a2));
            } catch (Exception e2) {
            }
            if (a2 <= 0) {
                try {
                    cK = null;
                    cy.shutdownInput();
                    Thread.sleep(100);
                    cy.shutdownOutput();
                    cA.close();
                    cB.close();
                    cA = null;
                    cB = null;
                    cy.close();
                    cy = null;
                    return;
                } catch (Exception e3) {
                    cy = null;
                    cA = null;
                    cB = null;
                    return;
                }
            } else {
                continue;
            }
        }
    }

    static synchronized void q() {
        synchronized (app.class) {
            new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.13
                @Override // java.lang.Runnable
                public void run() {
                    app.r();
                }
            }).start();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x01a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static synchronized void r() {
        /*
            Method dump skipped, instructions count: 669
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mdnsoft.callsmsmanager.app.r():void");
    }

    private static String s() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis()));
    }

    private static int t() {
        int myPid = Process.myPid();
        A.write("chmod 777 /proc/" + myPid + "/oom_adj\n");
        A.write("echo -17 > /proc/" + myPid + "/oom_adj\n");
        A.write("chmod 444 /proc/" + myPid + "/oom_adj\n");
        A.flush();
        return myPid;
    }

    private static void u() {
        a(aG, "endCall_");
        TelephonyManager telephonyManager = (TelephonyManager) cH.getApplicationContext().getSystemService("phone");
        try {
            telephonyManager.getClass().getMethod("endCall", null).invoke(telephonyManager, null);
        } catch (Exception e2) {
            a(aG, "Error endCall_:" + e2.getMessage() + "-" + e2.getCause());
            try {
                Field declaredField = Class.forName("com.android.internal.telephony.ITelephony$Stub").getDeclaredField("TRANSACTION_endCall");
                declaredField.setAccessible(true);
                int i2 = declaredField.getInt(null);
                if (!q) {
                    if (z == null) {
                        e();
                    }
                    A.write("service call phone " + i2 + "\n");
                    A.flush();
                    return;
                }
                Runtime.getRuntime().exec("service call phone " + i2 + "\n");
            } catch (Exception e3) {
                a(aG, "e tendcall:" + e3.getMessage());
            }
        }
    }

    private static boolean v() {
        a(aG, "answerRingingCall");
        bd = i();
        try {
            bd.getClass().getMethod("answerRingingCall", null).invoke(bd, null);
            return true;
        } catch (Exception e2) {
            a(aG, "Error answerRingingCall:" + e2.getMessage() + "-" + e2.getCause());
            return false;
        }
    }

    private static int w() {
        int i2 = 0;
        i2 = -1;
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                Class.forName("com.mediatek.internal.telephony.MtkRadioIndication");
                S = true;
            } catch (ClassNotFoundException e2) {
            }
        }
        try {
            Class.forName(b(0));
            try {
                S = true;
                return 0;
            } catch (ClassNotFoundException e3) {
                try {
                    Class.forName(b(10));
                    try {
                        S = true;
                        return 10;
                    } catch (ClassNotFoundException e4) {
                        i2 = 10;
                        if (DataService.H == 0) {
                            return -1;
                        }
                        try {
                            Class.forName("android.telephony.SmsManager").getMethod("getDefault", Integer.TYPE);
                            return 4;
                        } catch (Exception e5) {
                            if (Build.MODEL.contains("S7562")) {
                                try {
                                    Class.forName(b(1));
                                    return 1;
                                } catch (ClassNotFoundException e6) {
                                    Class.forName(b(2));
                                    return 2;
                                }
                            }
                            try {
                                Class.forName(b(2));
                                return 2;
                            } catch (ClassNotFoundException e7) {
                                try {
                                    Class.forName(b(7));
                                    return 7;
                                } catch (ClassNotFoundException e8) {
                                    try {
                                        Class.forName(b(3));
                                        return 3;
                                    } catch (ClassNotFoundException e9) {
                                        try {
                                            Class.forName("android.telephony.MultiSimSmsManager");
                                            return 6;
                                        } catch (Exception e10) {
                                            try {
                                                Class.forName("android.telephony.SmsManager").getMethod("sendMultipartTextMessage", String.class, String.class, ArrayList.class, ArrayList.class, ArrayList.class, Integer.TYPE);
                                                return 5;
                                            } catch (Exception e11) {
                                                return i2;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException e12) {
                }
            }
        } catch (ClassNotFoundException e13) {
        }
    }

    @Override // android.app.Application
    public final void onCreate() {
        Thread.setDefaultUncaughtExceptionHandler(new TryMe());
        super.onCreate();
        cH = this;
        aC = (AlarmManager) getSystemService("alarm");
        aD = (PowerManager) getSystemService("power");
        DataService.N = (NotificationManager) getSystemService("notification");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String string = defaultSharedPreferences.getString("pLanguage", "");
        az = string;
        if (!string.equals("")) {
            a(this, az);
        }
        m = defaultSharedPreferences.getBoolean("pDebug", false);
        if (l) {
            m = true;
        }
        try {
            aH = new PrintWriter(openFileOutput("CallSMSManagerlogerr.txt", 32768));
            if (l) {
                aG = new PrintWriter(openFileOutput("CallSMSManagerlog.txt", 32768));
            } else if (m) {
                l = true;
                SecretKeySpec secretKeySpec = new SecretKeySpec(cH.getApplicationContext().getPackageName().substring(0, 16).getBytes(), "AES");
                Cipher instance = Cipher.getInstance("AES");
                instance.init(1, secretKeySpec);
                aG = new PrintWriter(new CipherOutputStream(openFileOutput("CallSMSManagerlog.txt", 32768), instance));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a(aG, "on App Create RCM");
        q = PreferenceManager.getDefaultSharedPreferences(cH).getBoolean("pnoRoot", false);
        a(aG, "pid=" + Process.myPid());
        a(aG, Util.c());
        bQ = new String[]{getString(2131230789), "1", "2"};
        bR = new String[]{"1", "2"};
        SQLiteDatabase openOrCreateDatabase = openOrCreateDatabase("Data.db", 0, null);
        v = openOrCreateDatabase;
        openOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS tDBVer (dbVer INT)");
        int i2 = -1;
        try {
            Cursor rawQuery = v.rawQuery("select max(dbVer) from tDBVer", null);
            rawQuery.moveToFirst();
            i2 = rawQuery.getInt(0);
            rawQuery.close();
            i2 = i2;
        } catch (Exception e3) {
        }
        if (i2 != 23) {
            if (i2 == 1) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN slot INTEGER  default (-1)");
                } catch (Exception e4) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN slot INTEGER  default (-1)");
                } catch (Exception e5) {
                }
            }
            if (i2 > 0 && i2 <= 2) {
                try {
                    v.execSQL("ALTER TABLE sms_log ADD COLUMN read INTEGER  default (0)");
                } catch (Exception e6) {
                }
                try {
                    v.execSQL("ALTER TABLE sms_log ADD COLUMN date_sent BIGINT default (0)");
                } catch (Exception e7) {
                }
            }
            if (i2 > 0 && i2 < 4) {
                try {
                    v.execSQL("drop view vJournal");
                } catch (Exception e8) {
                }
            }
            if (i2 > 0 && i2 < 5) {
                try {
                    v.execSQL("drop view vSchedules");
                } catch (Exception e9) {
                }
            }
            if (i2 > 0 && i2 < 6) {
                try {
                    v.execSQL("ALTER TABLE call_log ADD COLUMN read INTEGER  default (0)");
                } catch (Exception e10) {
                }
                try {
                    v.execSQL("ALTER TABLE mms_log ADD COLUMN read INTEGER  default (0)");
                } catch (Exception e11) {
                }
                try {
                    v.execSQL("drop view vJournal");
                } catch (Exception e12) {
                }
                try {
                    v.execSQL("update call_log set read=1");
                    v.execSQL("update sms_log set read=1");
                    v.execSQL("update mms_log set read=1");
                    v.execSQL("update tbPrivateLog set read=1");
                } catch (Exception e13) {
                }
            }
            if (i2 > 0 && i2 < 7) {
                try {
                    v.execSQL("create table tbNumberList2(n_id INTEGER PRIMARY KEY AUTOINCREMENT, l_id INTEGER,blocktype INTEGER,blockcontent INTEGER, Number TEXT, Name TEXT,NumberType INTEGER, NotifType INTEGER,Journal INTEGER,  Settings INTEGER,smsfilter INTEGER, msg TEXT,smsanswer TEXT, bsmsanswer INTEGER, slot INTEGER default (-1),  CONSTRAINT constr1 UNIQUE(l_id,Number,slot))");
                    v.execSQL("insert into tbNumberList2(n_id, l_id ,blocktype,blockcontent, Number, Name,NumberType, NotifType,Journal,  Settings ,smsfilter, msg,smsanswer, bsmsanswer, slot) select n_id, l_id ,blocktype,blockcontent, Number, Name,NumberType, NotifType,Journal,  Settings ,smsfilter, msg,smsanswer, bsmsanswer, slot from tbNumberList");
                    v.execSQL("drop table tbNumberList");
                    v.execSQL("ALTER TABLE tbNumberList2 RENAME TO tbNumberList");
                } catch (Exception e14) {
                }
            }
            if (i2 > 0 && i2 < 8) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN Volume INTEGER  default (0)");
                } catch (Exception e15) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN Volume INTEGER  default (0)");
                } catch (Exception e16) {
                }
                try {
                    v.execSQL("ALTER TABLE mms_log ADD COLUMN load INTEGER  default (0)");
                } catch (Exception e17) {
                }
                try {
                    v.execSQL("ALTER TABLE mms_log ADD COLUMN ContentLocation TEXT");
                } catch (Exception e18) {
                }
                try {
                    v.execSQL("ALTER TABLE mms_log ADD COLUMN data1 BLOB");
                } catch (Exception e19) {
                }
                try {
                    v.execSQL("ALTER TABLE mms_log ADD COLUMN date_sent BIGINT");
                } catch (Exception e20) {
                }
                try {
                    v.execSQL("ALTER TABLE sms_log ADD COLUMN data BLOB");
                } catch (Exception e21) {
                }
            }
            if (i2 > 0 && i2 < 9) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN MuteTime INTEGER  default (0)");
                } catch (Exception e22) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN MuteTime INTEGER  default (0)");
                } catch (Exception e23) {
                }
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN bKeeper INTEGER  default (0)");
                } catch (Exception e24) {
                }
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN KeeperNum INTEGER  default (3)");
                } catch (Exception e25) {
                }
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN KeeperTime INTEGER  default (5)");
                } catch (Exception e26) {
                }
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN Keeperblocktype INTEGER  default (0)");
                } catch (Exception e27) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN bKeeper INTEGER  default (0)");
                } catch (Exception e28) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN KeeperNum INTEGER  default (3)");
                } catch (Exception e29) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN KeeperTime INTEGER  default (5)");
                } catch (Exception e30) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN Keeperblocktype INTEGER  default (0)");
                } catch (Exception e31) {
                }
                try {
                    v.execSQL("ALTER TABLE call_log ADD COLUMN n_id INTEGER  default (-1)");
                } catch (Exception e32) {
                }
                try {
                    v.execSQL("ALTER TABLE call_log ADD COLUMN l_id INTEGER  default (-1)");
                } catch (Exception e33) {
                }
            }
            if (i2 > 0 && i2 < 10) {
                try {
                    v.execSQL("ALTER TABLE call_log ADD COLUMN bKeeper INTEGER default(0)");
                } catch (Exception e34) {
                }
            }
            if (i2 > 0 && i2 < 11) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN Move INTEGER  default (0)");
                } catch (Exception e35) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN Move INTEGER  default (0)");
                } catch (Exception e36) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN N INTEGER default(0)");
                    v.execSQL("update tbLists set N=l_id");
                } catch (Exception e37) {
                }
                try {
                    v.execSQL("ALTER TABLE tbGroupList ADD COLUMN N INTEGER default(0)");
                    v.execSQL("update tbGroupList set N=_id");
                } catch (Exception e38) {
                }
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN N INTEGER default(0)");
                    v.execSQL("update tbNumberList set N=n_id");
                    v.execSQL("create table tbNumberList_tmp(n_id INTEGER, l_id INTEGER);");
                    v.execSQL(String.valueOf(String.valueOf("insert into tbNumberList_tmp") + "\nselect n_id,l_id from tbNumberList") + "\norder by l_id, (case when Number<>'*' then 0 else 1 end),(case NumberType when 5 then 2 when 6 then 0 else NumberType end),(case when NumberType not in(2,5)then 0 else length(Number) end)desc, n_id;");
                    v.execSQL("update tbNumberList set N=(select rowid from tbNumberList_tmp where tbNumberList_tmp.n_id=tbNumberList.n_id);");
                    v.execSQL("drop table tbNumberList_tmp;");
                } catch (Exception e39) {
                    a(aG, e39.getMessage());
                }
                try {
                    v.execSQL("ALTER TABLE call_log ADD COLUMN invisible INTEGER  default (0)");
                } catch (Exception e40) {
                }
                try {
                    v.execSQL("drop view vJournal");
                } catch (Exception e41) {
                }
            }
            if (i2 > 0 && i2 < 12) {
                try {
                    v.execSQL("ALTER TABLE sms_log ADD COLUMN n_id INTEGER  default (-1)");
                } catch (Exception e42) {
                }
                try {
                    v.execSQL("ALTER TABLE sms_log ADD COLUMN l_id INTEGER  default (-1)");
                } catch (Exception e43) {
                }
                try {
                    v.execSQL("ALTER TABLE mms_log ADD COLUMN n_id INTEGER  default (-1)");
                } catch (Exception e44) {
                }
                try {
                    v.execSQL("ALTER TABLE mms_log ADD COLUMN l_id INTEGER  default (-1)");
                } catch (Exception e45) {
                }
                try {
                    v.execSQL("drop view vContacts");
                } catch (Exception e46) {
                }
            }
            if (i2 > 0 && i2 < 13) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN status INTEGER  default (0)");
                    v.execSQL("update tbNumberList set status=0");
                } catch (Exception e47) {
                }
                try {
                    v.execSQL("ALTER TABLE tbSchedules ADD COLUMN type INTEGER  default (0)");
                    v.execSQL("update tbSchedules set type=0");
                } catch (Exception e48) {
                }
                try {
                    v.execSQL("drop view vSchedules");
                } catch (Exception e49) {
                }
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN MoveFilter TEXT  default ('')");
                } catch (Exception e50) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN MoveFilter TEXT  default ('')");
                } catch (Exception e51) {
                }
            }
            if (i2 > 0 && i2 < 14) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN DeleteTime BIGINT  default (257698037820)");
                } catch (Exception e52) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN DeleteTime BIGINT  default (257698037820)");
                } catch (Exception e53) {
                }
            }
            if (i2 > 0 && i2 < 15) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN MarkTime BIGINT  default (0)");
                } catch (Exception e54) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN MarkTime BIGINT  default (0)");
                } catch (Exception e55) {
                }
                try {
                    v.execSQL("ALTER TABLE sms_log ADD COLUMN invisible INTEGER  default (0)");
                } catch (Exception e56) {
                }
                try {
                    v.execSQL("drop view vJournal");
                } catch (Exception e57) {
                }
            }
            if (i2 > 0 && i2 < 16) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN msg_out TEXT  default ('')");
                } catch (Exception e58) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN msg_out TEXT  default ('')");
                } catch (Exception e59) {
                }
            }
            if (i2 > 0 && i2 < 17) {
                try {
                    v.execSQL("ALTER TABLE call_log ADD COLUMN forwarded INTEGER  default (0)");
                } catch (Exception e60) {
                }
                try {
                    v.execSQL("ALTER TABLE tbPrivateLog ADD COLUMN forwarded INTEGER  default (0)");
                } catch (Exception e61) {
                }
                try {
                    v.execSQL("drop view vJournal");
                } catch (Exception e62) {
                }
            }
            if (i2 > 0 && i2 < 18) {
                try {
                    v.execSQL("CREATE TABLE tbNumberList2(n_id INTEGER PRIMARY KEY AUTOINCREMENT, l_id INTEGER,blocktype INTEGER,blockcontent INTEGER, Number TEXT, Name TEXT,NumberType INTEGER, NotifType INTEGER,Journal INTEGER,  Settings INTEGER,smsfilter INTEGER, msg TEXT,msg_out TEXT,smsanswer TEXT, bsmsanswer INTEGER, slot INTEGER default (-1), Volume INTEGER  default (0), MuteTime INTEGER  default (0), DeleteTime BIGINT  default (257698037820),MarkTime BIGINT  default (0), bKeeper INTEGER  default (0),KeeperNum INTEGER  default (3),KeeperTime INTEGER  default (5), Keeperblocktype INTEGER default (0),  Move INTEGER default(0),N INTEGER default(0), status INTEGER default(0), MoveFilter TEXT  default (''))");
                    v.execSQL("insert into tbNumberList2 (n_id, l_id,blocktype,blockcontent, Number, Name,NumberType, NotifType ,Journal ,  Settings ,smsfilter , msg ,msg_out ,smsanswer , bsmsanswer , slot, Volume , MuteTime , DeleteTime,MarkTime , bKeeper ,KeeperNum ,KeeperTime, Keeperblocktype,  Move ,N , status , MoveFilter)     select n_id, l_id,blocktype,blockcontent, Number, Name,NumberType, NotifType ,Journal ,  Settings ,smsfilter , msg ,msg_out ,smsanswer , bsmsanswer , slot, Volume , MuteTime , DeleteTime,MarkTime , bKeeper ,KeeperNum ,KeeperTime, Keeperblocktype,  Move ,N , status , MoveFilter from tbNumberList");
                    v.execSQL("drop table tbNumberList");
                    v.execSQL("ALTER TABLE tbNumberList2 RENAME TO tbNumberList");
                } catch (Exception e63) {
                }
            }
            if (i2 > 0 && i2 < 19) {
                try {
                    v.execSQL("drop view vSchedules");
                } catch (Exception e64) {
                }
            }
            if (i2 > 0 && i2 < 20) {
                try {
                    v.execSQL("ALTER TABLE tbPrivateLog ADD COLUMN date_sent BIGINT  default (0)");
                } catch (Exception e65) {
                }
            }
            if (i2 > 0 && i2 < 21) {
                try {
                    v.execSQL("CREATE TABLE tbGroupList2(_id INTEGER PRIMARY KEY AUTOINCREMENT, g_id INTEGER, Number TEXT, Name TEXT,NumberType INTEGER,  Settings INTEGER, N INTEGER default(0))");
                    v.execSQL("insert into tbGroupList2 (_id, g_id, Number, Name, NumberType, Settings, N)     select _id, g_id, Number, Name, NumberType, Settings, N from tbGroupList");
                    v.execSQL("drop table tbGroupList");
                    v.execSQL("ALTER TABLE tbGroupList2 RENAME TO tbGroupList");
                } catch (Exception e66) {
                }
            }
            if (i2 > 0 && i2 < 22) {
                try {
                    v.execSQL("ALTER TABLE tbNumberList ADD COLUMN forward_out INTEGER default(0)");
                } catch (Exception e67) {
                }
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN forward_out INTEGER default(0)");
                } catch (Exception e68) {
                }
            }
            if (i2 > 0 && i2 < 23) {
                try {
                    v.execSQL("ALTER TABLE tbLists ADD COLUMN msg_ussd TEXT  default ('')");
                } catch (Exception e69) {
                }
            }
            v.execSQL("insert into tDBVer (dbVer) values (23)");
            v.setVersion(23);
        }
        v.execSQL("CREATE TABLE IF NOT EXISTS tbLists(l_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Mode INTEGER, blocktype INTEGER,blockcontent INTEGER,  NotifType INTEGER,  Journal INTEGER,  Settings INTEGER,smsfilter INTEGER, msg TEXT,msg_out TEXT,  smsanswer TEXT,  bsmsanswer INTEGER, slot INTEGER default (-1), Volume INTEGER  default (0), MuteTime INTEGER  default (0) ,DeleteTime BIGINT  default (257698037820),MarkTime BIGINT  default (0), bKeeper INTEGER  default (0),KeeperNum INTEGER  default (3),KeeperTime INTEGER  default (5), Keeperblocktype INTEGER default (0), Move INTEGER default(0),N INTEGER default(1),MoveFilter TEXT  default (''), forward_out INTEGER default(0),msg_ussd TEXT  default (''))");
        try {
            v.execSQL("insert into tbLists (l_id,Name,N) values (-1,'" + cH.getApplicationContext().getString(2131230745) + "',-1)");
        } catch (Exception e70) {
        }
        try {
            v.execSQL("insert into tbLists (l_id,Name,N) values (0,'" + cH.getApplicationContext().getString(2131230746) + "',0)");
            v.execSQL("insert into tbLists (l_id,Name,N, blocktype,blockcontent,NotifType,Journal) values (1,'" + cH.getApplicationContext().getString(2131231078) + "',1," + (Build.VERSION.SDK_INT < 26 ? 1 : 17) + "," + (Build.VERSION.SDK_INT < 26 ? 3 : 1) + ",3,3)");
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(cH).edit();
            edit.putInt("pCurList", 1);
            edit.commit();
            aU = 1;
        } catch (Exception e71) {
        }
        try {
            v.execSQL("insert into tbLists (l_id,blockcontent,Name,N) values (-10,3,'" + cH.getApplicationContext().getString(2131230884) + "',-10)");
        } catch (Exception e72) {
        }
        v.execSQL("CREATE TABLE IF NOT EXISTS tbNumberList(n_id INTEGER PRIMARY KEY AUTOINCREMENT, l_id INTEGER,blocktype INTEGER,blockcontent INTEGER, Number TEXT, Name TEXT,NumberType INTEGER, NotifType INTEGER,Journal INTEGER,  Settings INTEGER,smsfilter INTEGER, msg TEXT,msg_out TEXT,smsanswer TEXT, bsmsanswer INTEGER, slot INTEGER default (-1), Volume INTEGER  default (0), MuteTime INTEGER  default (0), DeleteTime BIGINT  default (257698037820),MarkTime BIGINT  default (0), bKeeper INTEGER  default (0),KeeperNum INTEGER  default (3),KeeperTime INTEGER  default (5), Keeperblocktype INTEGER default (0),  Move INTEGER default(0),N INTEGER default(0), status INTEGER default(0), MoveFilter TEXT  default (''), forward_out INTEGER default(0))");
        v.execSQL("CREATE TRIGGER IF NOT EXISTS tbNumberList_t AFTER INSERT ON tbNumberList BEGIN UPDATE tbNumberList SET N = (select ifnull(max(N),0)+1 from tbNumberList)  WHERE rowid = new.rowid and new.N=0; END");
        v.execSQL("CREATE TABLE IF NOT EXISTS sms_log(_id INTEGER PRIMARY KEY AUTOINCREMENT,simid BIGINT, address TEXT, body TEXT, date BIGINT, type INTEGER, read INTEGER default 0, date_sent BIGINT, data BLOB, n_id INTEGER default(-1), l_id INTEGER default(-1),invisible INTEGER  default (0))");
        v.execSQL("CREATE INDEX IF NOT EXISTS sms_log_idx1 ON sms_log ( simid,Date)");
        v.execSQL("CREATE INDEX IF NOT EXISTS sms_log_idx2 ON sms_log ( address,Date)");
        v.execSQL("CREATE TABLE IF NOT EXISTS mms_log(_id INTEGER PRIMARY KEY AUTOINCREMENT,simid BIGINT, address TEXT, body TEXT, date BIGINT, type INTEGER, data BLOB,read INTEGER default 0, date_sent BIGINT, ContentLocation TEXT, load INTEGER default 0, data1 BLOB,  n_id INTEGER default(-1), l_id INTEGER default(-1)) ");
        v.execSQL("CREATE INDEX IF NOT EXISTS mms_log_idx1 ON mms_log ( simid,Date)");
        v.execSQL("CREATE INDEX IF NOT EXISTS mms_log_idx2 ON mms_log ( address,Date)");
        v.execSQL("CREATE TABLE IF NOT EXISTS call_log(_id INTEGER PRIMARY KEY AUTOINCREMENT,simid BIGINT, number TEXT, date BIGINT, type INTEGER, read INTEGER default 0, n_id INTEGER default(-1), l_id INTEGER default(-1), bKeeper INTEGER default(0),invisible INTEGER  default (0), forwarded INTEGER  default (0))");
        v.execSQL("CREATE INDEX IF NOT EXISTS call_log_idx1 ON call_log ( simid,Date)");
        v.execSQL("CREATE INDEX IF NOT EXISTS call_log_idx2 ON call_log ( number,Date)");
        v.execSQL("CREATE TABLE IF NOT EXISTS del_log(_id INTEGER, ctype INTEGER, date BIGINT, deltime BIGINT default(257698037820), simid BIGINT, number TEXT, body TEXT, thread_id INTEGER, duration INTEGER default 0, type INTEGER,  n_id INTEGER default(-1), l_id INTEGER default(-1) , PRIMARY KEY(ctype,_id,date))");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbPrivateLog(_id INTEGER, ctype INTEGER, type INTEGER, date BIGINT, simid BIGINT, number TEXT, body TEXT,duration INTEGER default 0,read INTEGER default 0, forwarded INTEGER default 0, date_sent BIGINT  default (0), PRIMARY KEY(ctype,_id,date) )");
        v.execSQL("CREATE INDEX IF NOT EXISTS tbPrivateLog_idx1 ON tbPrivateLog ( simid,Date)");
        v.execSQL("CREATE INDEX IF NOT EXISTS tbPrivateLog_idx2 ON tbPrivateLog( number,Date)");
        v.execSQL("CREATE TABLE IF NOT EXISTS mms_part(_id INTEGER PRIMARY KEY AUTOINCREMENT,mid INTEGER,seq INTEGER DEFAULT 0,ct TEXT,name TEXT,chset INTEGER,cd TEXT,fn TEXT,cid TEXT,cl TEXT,ctt_s INTEGER,ctt_t TEXT,text TEXT,data BLOB)");
        v.execSQL("create view IF NOT EXISTS vJournal   as select \t rowid as id,date,simid as Sim_slot,1 as param,type, number,  '' as body,null as data, read, 0 as duration, forwarded from call_log where invisible=0 union all select \trowid as id,date,simid as Sim_slot,2 as param,type, address as number,body,null as data, read,  0 as duration,0 as forwarded from sms_log where invisible=0 union all select \trowid as id,date,simid as Sim_slot,4 as param,type,address as number,body,data, read,  0 as duration,0 as forwarded   from mms_log union all select \trowid as id,date,simid as Sim_slot,ctype as param,type, number,body,null as data, read, duration, forwarded  from tbPrivateLog ");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbSchedules(_id INTEGER PRIMARY KEY AUTOINCREMENT, status INTEGER, type INTEGER  default (0), l_id INTEGER, h1 INTEGER,m1 INTEGER, h2 INTEGER,m2 INTEGER, d1 INTEGER, d2 INTEGER, d3 INTEGER, d4 INTEGER, d5 INTEGER, d6 INTEGER, d7 INTEGER)");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbSchedules_tmp(_id INTEGER PRIMARY KEY AUTOINCREMENT, status INTEGER,l_id INTEGER, l_id_end INTEGER, dbegin BIGINT, dend BIGINT,day_begin INTEGER,day_end INTEGER,h1 INTEGER,m1 INTEGER, h2 INTEGER,m2 INTEGER)");
        v.execSQL("create view IF NOT EXISTS vSchedules   as select _id, status ,type,l_id ,1 as d, h1 ,m1 , h2 ,m2 from tbSchedules where d1=1 and 60*h1+m1<60*h2+m2 union all\nselect _id, status ,type,l_id ,2 as d, h1 ,m1 , h2 ,m2 from tbSchedules where d2=1 and 60*h1+m1<60*h2+m2 union all\nselect _id, status ,type,l_id ,3 as d, h1 ,m1 , h2 ,m2 from tbSchedules where d3=1 and 60*h1+m1<60*h2+m2 union all\nselect _id, status ,type,l_id ,4 as d, h1 ,m1 , h2 ,m2 from tbSchedules where d4=1 and 60*h1+m1<60*h2+m2 union all\nselect _id, status ,type,l_id ,5 as d, h1 ,m1 , h2 ,m2 from tbSchedules where d5=1 and 60*h1+m1<60*h2+m2 union all\nselect _id, status ,type,l_id ,6 as d, h1 ,m1 , h2 ,m2 from tbSchedules where d6=1 and 60*h1+m1<60*h2+m2 union all\nselect _id, status ,type,l_id ,7 as d, h1 ,m1 , h2 ,m2 from tbSchedules where d7=1 and 60*h1+m1<60*h2+m2 union all\nselect _id, status ,type,l_id ,1 as d, h1 ,m1 , 23 ,59 from tbSchedules where d1=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,2 as d, h1 ,m1 , 23 ,59 from tbSchedules where d2=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,3 as d, h1 ,m1 , 23 ,59 from tbSchedules where d3=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,4 as d, h1 ,m1 , 23 ,59 from tbSchedules where d4=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,5 as d, h1 ,m1 , 23 ,59 from tbSchedules where d5=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,6 as d, h1 ,m1 , 23 ,59 from tbSchedules where d6=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,7 as d, h1 ,m1 , 23 ,59 from tbSchedules where d7=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,2 as d, 0 ,0 , h2 ,m2  from tbSchedules where d1=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,3 as d, 0 ,0 , h2 ,m2  from tbSchedules where d2=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,4 as d, 0 ,0 , h2 ,m2  from tbSchedules where d3=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,5 as d, 0 ,0 , h2 ,m2  from tbSchedules where d4=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,6 as d, 0 ,0 , h2 ,m2  from tbSchedules where d5=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,7 as d, 0 ,0 , h2 ,m2  from tbSchedules where d6=1 and 60*h1+m1>60*h2+m2 union all\nselect _id, status ,type,l_id ,1 as d, 0 ,0 , h2 ,m2  from tbSchedules where d7=1 and 60*h1+m1>60*h2+m2\n union all select -_id as _id, status ,1 as type,l_id ,day_begin as d, h1 ,m1 , h2 ,m2 from tbSchedules_tmp where day_begin=day_end\n union all select -_id as _id, status ,1 as type,l_id ,day_begin as d, h1 ,m1 , 23 ,59 from tbSchedules_tmp where day_begin<day_end\n union all select -_id as _id, status ,1 as type,l_id ,day_end as d, 0,0 , h2 ,m2 from tbSchedules_tmp where day_begin<day_end");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbAnswers(date BIGINT, Number TEXT )");
        v.execSQL("CREATE TABLE IF NOT EXISTS tblog(date BIGINT, type INTEGER, r INTEGER, answer TEXT )");
        v.execSQL("create table IF NOT EXISTS tbNotif(n_id INT, l_id INT, type INT, action INT,Settings INT,bBAR INT, tickerText TEXT, cTitle TEXT, cText TEXT, ico INT,bSound INT, Sound TEXT,bVibra INT, Pulse INT, Pause INT, Repeat INT,bLed INT, LedColor INT,LedOn INT, Ledoff INT, LedDuration INT, LedSettings INT)");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbGroups(g_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Mode INTEGER, blocktype INTEGER,blockcontent INTEGER,  NotifType INTEGER,  Journal INTEGER,  Settings INTEGER,smsfilter INTEGER, msg TEXT,  smsanswer TEXT,  bsmsanswer INTEGER, slot INTEGER default (-1))");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbGroupList(_id INTEGER PRIMARY KEY AUTOINCREMENT, g_id INTEGER, Number TEXT, Name TEXT,NumberType INTEGER,  Settings INTEGER, N INTEGER default(0))");
        v.execSQL("CREATE TRIGGER IF NOT EXISTS tbGroupList_t AFTER INSERT ON tbGroupList BEGIN UPDATE tbGroupList SET N = (select ifnull(max(N),0)+1 from tbGroupList)  WHERE rowid = new.rowid and new.N=0; END");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbContacts(_id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT)");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbContactsPhones(_id INTEGER PRIMARY KEY AUTOINCREMENT,c_id INTEGER, Number TEXT, type INTEGER)");
        v.execSQL("create view IF NOT EXISTS vContacts as\nselect Name,Number,0 as type,b._id as m_id from tbContactsPhones a, tbContacts b where a.c_id=b._id\nunion all\nselect Name,Number,1 as type,n_id as m_id from tbNumberList where NumberType=0\nunion all\nselect Name,Number,2 as type,g_id as m_id from tbGroupList where NumberType=0\n");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbNewNumbers(_id INTEGER PRIMARY KEY AUTOINCREMENT, Number TEXT, type INTEGER default(0))");
        v.execSQL("CREATE TABLE IF NOT EXISTS tbKeeper(simid  INTEGER, n_id INTEGER, l_id INTEGER, phonenumber TEXT, type INTEGER, blockcontent INTEGER, NumberType INTEGER, t BIGINT)");
        v.execSQL("CREATE TABLE IF NOT EXISTS siminfo(_id INTEGER PRIMARY KEY AUTOINCREMENT,subid INTEGER default(0), icc_id TEXT, sim_id INTEGER, date BIGINT)");
        if (ActPerm.a(getApplicationContext()).size() > 0) {
            a(aG, "invalid permissions:" + ActPerm.a(getApplicationContext()).toString());
            Intent intent = new Intent(this, ActPerm.class);
            intent.addFlags(268435456);
            startActivity(intent);
            return;
        }
        Util.F();
        DataService.c();
        a(aG, "rildualsim=" + DataService.H);
        bf = w();
        a(aG, "SMStype=" + bf);
        a(aG, "isVolteAvailable=" + Util.v() + ",isVolteProvisionedOnDevice=" + Util.w() + ",isVolteEnabledByPlatform=" + Util.x());
        d();
        k = true;
        p = false;
        Util.y();
        if (Build.VERSION.SDK_INT >= 19) {
            boolean y2 = Util.y();
            a(aG, "WRITE_SMS=".concat(String.valueOf(y2)));
            if (!y2 && Build.VERSION.SDK_INT < 26 && (b || Util.E())) {
                if (z == null) {
                    e();
                }
                Util.z();
                Util.y();
            }
        }
        o = false;
        try {
            Class.forName("android.app.MiuiNotification");
            T = true;
        } catch (Exception e73) {
        }
        if (Lists.f(aU)) {
            new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.3
                @Override // java.lang.Runnable
                public void run() {
                    PhoneJournalList.d();
                }
            }).start();
        }
        try {
            b();
        } catch (Exception e74) {
            a(aG, "error RE" + e74.getMessage());
            t = 0;
            s = true;
        }
        Intent intent2 = new Intent(this, DataService.class);
        if (!aT || Build.VERSION.SDK_INT < 26) {
            try {
                startService(intent2);
            } catch (IllegalStateException e75) {
                if (Build.VERSION.SDK_INT >= 26) {
                    aT = true;
                    startForegroundService(intent2);
                }
            }
        } else {
            startForegroundService(intent2);
        }
        C = getFilesDir() + "/cmphonereader";
        D = getFilesDir() + "/runner";
        cE = getFilesDir() + "/rp.sh";
        be = getFilesDir().toString().replace("files", "shared_prefs");
        if (new File("/dbdata/databases/" + getPackageName() + "/shared_prefs").exists()) {
            be = "/dbdata/databases/" + getPackageName() + "/shared_prefs";
        }
        bd = i();
        new Thread(new Runnable() { // from class: com.mdnsoft.callsmsmanager.app.4
            @Override // java.lang.Runnable
            public void run() {
                app.n();
            }
        }).start();
        if (cI && Build.MODEL.contains("IQ431")) {
            new Timer().schedule(new TimerTask() { // from class: com.mdnsoft.callsmsmanager.app.5
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (!DataService.a) {
                        app.m();
                    }
                }
            }, 3000);
        }
        if (ci) {
            cm = Util.o(0);
            cn = Util.o(1);
            co = Util.o(2);
        }
        a(aG, "isNotificationListenerServiceEnabled=" + PrefAct.a("com.mdnsoft.callsmsmanager/com.mdnsoft.callsmsmanager.NLS"));
        if (Build.VERSION.SDK_INT >= 29 && Build.BRAND.toLowerCase().contains("xiaomi") && PrefAct.a("com.mdnsoft.callsmsmanager/com.mdnsoft.callsmsmanager.NLS")) {
            try {
                startService(new Intent(this, NLS.class));
            } catch (Exception e76) {
            }
            a(aG, "RelaunchApp,Hide=" + aI);
            PackageManager packageManager = getPackageManager();
            ComponentName componentName = new ComponentName(getApplicationContext(), ActAbout.class);
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        }
        Util.j();
        a(aG, "End on App Create RCM");
        a(aG, "FindModule=" + Util.E());
        if (b) {
            try {
                getPackageManager().setComponentEnabledSetting(new ComponentName(getApplicationContext(), Journal.class), Util.D() ? 1 : 2, 1);
            } catch (Exception e77) {
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
            } catch (Exception e78) {
            }
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
    }
}
