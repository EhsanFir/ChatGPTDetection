package com.e4a.runtime;

import com.e4a.runtime.annotations.SimpleFunction;
import com.e4a.runtime.annotations.SimpleObject;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@SimpleObject
/* renamed from: com.e4a.runtime.加密操作  reason: contains not printable characters */
/* loaded from: classes.dex */
public final class C0020 {
    private C0020() {
    }

    @SimpleFunction
    /* renamed from: 取MD5值  reason: contains not printable characters */
    public static String m299MD5(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[length * 2];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: RC4加密  reason: contains not printable characters */
    public static String m295RC4(String str, String str2) {
        if (!(str == null || str2 == null)) {
            try {
                byte[] RC4Base = RC4Base(str.getBytes("GBK"), str2);
                char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                int length = RC4Base.length;
                char[] cArr2 = new char[length * 2];
                int i = 0;
                for (byte b : RC4Base) {
                    int i2 = i + 1;
                    cArr2[i] = cArr[(b >>> 4) & 15];
                    i = i2 + 1;
                    cArr2[i2] = cArr[b & 15];
                }
                return new String(cArr2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @SimpleFunction
    /* renamed from: RC4解密  reason: contains not printable characters */
    public static String m297RC4(String str, String str2) {
        if (!(str == null || str2 == null)) {
            try {
                return new String(RC4Base(HexString2Bytes(str), str2), "GBK");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @SimpleFunction
    /* renamed from: RC4加密2  reason: contains not printable characters */
    public static byte[] m296RC42(byte[] bArr, String str) {
        if (bArr == null || str == null) {
            return new byte[0];
        }
        try {
            byte[] RC4Base = RC4Base(bArr, str);
            byte[] bArr2 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
            int length = RC4Base.length;
            byte[] bArr3 = new byte[length * 2];
            int i = 0;
            for (byte b : RC4Base) {
                int i2 = i + 1;
                bArr3[i] = bArr2[(b >>> 4) & 15];
                i = i2 + 1;
                bArr3[i2] = bArr2[b & 15];
            }
            return bArr3;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @SimpleFunction
    /* renamed from: RC4解密2  reason: contains not printable characters */
    public static byte[] m298RC42(byte[] bArr, String str) {
        if (bArr == null || str == null) {
            return new byte[0];
        }
        try {
            return RC4Base(bArr, str);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] HexString2Bytes(String str) {
        try {
            int length = str.length();
            byte[] bArr = new byte[length / 2];
            byte[] bytes = str.getBytes("GBK");
            for (int i = 0; i < length / 2; i++) {
                int i2 = i * 2;
                bArr[i] = uniteBytes(bytes[i2], bytes[i2 + 1]);
            }
            return bArr;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte uniteBytes(byte b, byte b2) {
        return (byte) (((char) (((char) Byte.decode("0x" + new String(new byte[]{b})).byteValue()) << 4)) ^ ((char) Byte.decode("0x" + new String(new byte[]{b2})).byteValue()));
    }

    private static byte[] RC4Base(byte[] bArr, String str) {
        byte[] initKey = initKey(str);
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = ((initKey[i] & 255) + i2) & 255;
            byte b = initKey[i];
            initKey[i] = initKey[i2];
            initKey[i2] = b;
            bArr2[i3] = (byte) (initKey[((initKey[i] & 255) + (initKey[i2] & 255)) & 255] ^ bArr[i3]);
        }
        return bArr2;
    }

    private static byte[] initKey(String str) {
        try {
            byte[] bytes = str.getBytes("GBK");
            byte[] bArr = new byte[256];
            for (int i = 0; i < 256; i++) {
                bArr[i] = (byte) i;
            }
            if (!(bytes == null || bytes.length == 0)) {
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < 256; i4++) {
                    i3 = ((bytes[i2] & 255) + (bArr[i4] & 255) + i3) & 255;
                    byte b = bArr[i4];
                    bArr[i4] = bArr[i3];
                    bArr[i3] = b;
                    i2 = (i2 + 1) % bytes.length;
                }
                return bArr;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SimpleFunction
    /* renamed from: DES加密  reason: contains not printable characters */
    public static String m293DES(String str, String str2) throws Exception {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("GBK"), "DES");
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return m291BASE64(instance.doFinal(str.getBytes("GBK")));
        } catch (Exception unused) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: DES解密  reason: contains not printable characters */
    public static String m294DES(String str, String str2) throws Exception {
        try {
            byte[] r6 = m292BASE64(str);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("GBK"), "DES");
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(r6), "GBK");
        } catch (Exception unused) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: BASE64编码  reason: contains not printable characters */
    public static String m291BASE64(byte[] bArr) {
        try {
            return Base64.encode(bArr);
        } catch (Exception unused) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: BASE64解码  reason: contains not printable characters */
    public static byte[] m292BASE64(String str) {
        try {
            return Base64.decode(str);
        } catch (UnsupportedEncodingException unused) {
            return new byte[0];
        }
    }

    @SimpleFunction
    /* renamed from: Authcode加密  reason: contains not printable characters */
    public static String m289Authcode(String str, String str2) {
        try {
            return Authcode.Encode(str, str2);
        } catch (Exception unused) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: Authcode解密  reason: contains not printable characters */
    public static String m290Authcode(String str, String str2) {
        try {
            return Authcode.Decode(str, str2);
        } catch (Exception unused) {
            return "";
        }
    }
}
