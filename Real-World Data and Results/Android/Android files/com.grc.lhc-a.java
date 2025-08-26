package com.qq.e.comm.net.rr;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: dalvik_source_3D2B2CA765D8B36EFB287D5823493E781208771F38DEC8BE378A0A43A7F65439.apk */
/* loaded from: classes.dex */
public final class a {
    private static final byte[] a = {91, -62};
    private static Cipher b = null;
    private static Cipher c = null;
    private static byte[] d = Base64.decode("4M3PpUC4Vu1uMp+Y0Mxd+vfc6v4ggJAINfgTlH74pis=", 0);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: dalvik_source_3D2B2CA765D8B36EFB287D5823493E781208771F38DEC8BE378A0A43A7F65439.apk */
    /* renamed from: com.qq.e.comm.net.rr.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0010a extends Exception {
        public C0010a(String str, Throwable th) {
            super(str, th);
        }
    }

    /* compiled from: dalvik_source_3D2B2CA765D8B36EFB287D5823493E781208771F38DEC8BE378A0A43A7F65439.apk */
    /* loaded from: classes.dex */
    public static class b extends Exception {
        public b(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    @SuppressLint({"TrulyRandom"})
    private static synchronized Cipher a() throws C0010a {
        Cipher $r0;
        synchronized (a.class) {
            if (b != null) {
                $r0 = b;
            } else {
                try {
                    Cipher $r02 = Cipher.getInstance("AES/ECB/PKCS7Padding");
                    $r02.init(1, new SecretKeySpec(d, "AES"));
                    b = $r02;
                    $r0 = b;
                } catch (Exception $r3) {
                    throw new C0010a("Fail To Init Cipher", $r3);
                }
            }
        }
        return $r0;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static byte[] a(byte[] bArr) throws b {
        ByteArrayOutputStream $r1 = new ByteArrayOutputStream();
        DataOutputStream $r2 = new DataOutputStream($r1);
        try {
            $r2.write(a);
            $r2.writeByte(1);
            $r2.writeByte(2);
            $r2.write(c(com.qq.e.comm.a.a(bArr)));
            $r2.close();
            return $r1.toByteArray();
        } catch (Exception $r4) {
            throw new b("Exception while packaging byte array", $r4);
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    private static synchronized Cipher b() throws C0010a {
        Cipher $r0;
        synchronized (a.class) {
            if (c != null) {
                $r0 = c;
            } else {
                try {
                    Cipher $r02 = Cipher.getInstance("AES/ECB/PKCS7Padding");
                    $r02.init(2, new SecretKeySpec(d, "AES"));
                    c = $r02;
                    $r0 = c;
                } catch (Exception $r3) {
                    throw new C0010a("Fail To Init Cipher", $r3);
                }
            }
        }
        return $r0;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    @TargetApi(9)
    public static byte[] b(byte[] bArr) throws b {
        if (bArr == null || bArr.length < 4) {
            throw new b("S2SS Package FormatError", null);
        }
        try {
            byte[] $r4 = new byte[4];
            new DataInputStream(new ByteArrayInputStream(bArr)).read($r4);
            if (a[0] == $r4[0] && a[1] == $r4[1] && 1 == $r4[2] && 2 == $r4[3]) {
                return com.qq.e.comm.a.b(d(Arrays.copyOfRange(bArr, 4, bArr.length)));
            }
            throw new b("S2SS Package Magic/Version FormatError", null);
        } catch (Exception $r6) {
            throw new b("Exception while packaging byte array", $r6);
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    private static byte[] c(byte[] bArr) throws C0010a {
        try {
            return a().doFinal(bArr);
        } catch (Exception $r3) {
            throw new C0010a("Exception While encrypt byte array", $r3);
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    private static byte[] d(byte[] bArr) throws C0010a {
        try {
            return b().doFinal(bArr);
        } catch (Exception $r3) {
            throw new C0010a("Exception While dencrypt byte array", $r3);
        }
    }
}
