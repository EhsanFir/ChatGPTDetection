package com.umeng.analytics;

import android.content.Context;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import u.aly.bm;
import u.aly.cw;
import u.aly.m;

/* compiled from: DataHelper.java */
/* loaded from: classes.dex */
public class b {
    private static final byte[] a = {10, 1, 11, 5, 4, cw.m, 7, 9, 23, 3, 1, 6, 8, 12, cw.k, 91};

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static int a(int $i0, String str) {
        int $i1 = 0;
        if (((double) new Random().nextFloat()) < 0.001d) {
            if (str == null) {
                bm.c("--->", "null signature..");
            }
            try {
                $i1 = Integer.parseInt(str.substring(9, 11), 16);
            } catch (Exception e) {
            }
            return ($i1 | 128) * 1000;
        }
        int $i02 = new Random().nextInt($i0);
        if ($i02 > 255000 || $i02 < 128000) {
            return $i02;
        }
        return 127000;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static String a(Throwable th) {
        String $r3 = null;
        if (th == null) {
            return null;
        }
        try {
            StringWriter $r1 = new StringWriter();
            PrintWriter $r2 = new PrintWriter($r1);
            th.printStackTrace($r2);
            for (Throwable $r0 = th.getCause(); $r0 != null; $r0 = $r0.getCause()) {
                $r0.printStackTrace($r2);
            }
            String $r4 = $r1.toString();
            $r3 = $r4;
            $r2.close();
            $r1.close();
            return $r4;
        } catch (Exception $r5) {
            $r5.printStackTrace();
            return $r3;
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer $r1 = new StringBuffer();
        for (int $i0 = 0; $i0 < bArr.length; $i0++) {
            $r1.append(String.format("%02X", Byte.valueOf(bArr[$i0])));
        }
        return $r1.toString().toLowerCase(Locale.US);
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static boolean a(Context context, byte[] bArr) {
        long $l0 = (long) bArr.length;
        if ($l0 <= a.y) {
            return false;
        }
        h.a(context).h();
        m.a(context).a($l0, System.currentTimeMillis(), a.v);
        return true;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        int $i1 = str.length();
        if ($i1 % 2 != 0) {
            return null;
        }
        byte[] $r1 = new byte[$i1 / 2];
        for (int $i2 = 0; $i2 < $i1; $i2 += 2) {
            $r1[$i2 / 2] = (byte) Integer.valueOf(str.substring($i2, $i2 + 2), 16).intValue();
        }
        return $r1;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher $r3 = Cipher.getInstance("AES/CBC/PKCS7Padding");
        $r3.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(a));
        return $r3.doFinal(bArr);
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static String b(String str) {
        return "http://" + str + ".umeng.com/app_logs";
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest $r1 = MessageDigest.getInstance("MD5");
            $r1.reset();
            $r1.update(bArr);
            return $r1.digest();
        } catch (Exception $r2) {
            $r2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher $r3 = Cipher.getInstance("AES/CBC/PKCS7Padding");
        $r3.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(a));
        return $r3.doFinal(bArr);
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static String c(String str) {
        byte[] $r1 = str.getBytes();
        try {
            MessageDigest $r2 = MessageDigest.getInstance("SHA1");
            $r2.update($r1);
            return c($r2.digest());
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    static String c(byte[] bArr) {
        String $r1 = "";
        int $i0 = 0;
        while ($i0 < bArr.length) {
            String $r2 = Integer.toHexString((short) (((short) bArr[$i0]) & 255));
            if ($r2.length() == 1) {
                $r1 = $r1 + "0";
            }
            $i0++;
            $r1 = $r1 + $r2;
        }
        return $r1;
    }
}
