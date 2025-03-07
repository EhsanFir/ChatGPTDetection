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
public final class C0034 {
    private C0034() {
    }

    @SimpleFunction
    /* renamed from: 取MD5值  reason: contains not printable characters */
    public static String m548MD5(byte[] bytes) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(bytes);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                int k2 = k + 1;
                str[k] = hexDigits[(byte0 >>> 4) & 15];
                k = k2 + 1;
                str[k2] = hexDigits[byte0 & 15];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: RC4加密  reason: contains not printable characters */
    public static String m544RC4(String data, String key) {
        if (data == null || key == null) {
            return "";
        }
        try {
            byte[] a = RC4Base(data.getBytes("GBK"), key);
            char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            int j = a.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : a) {
                int k2 = k + 1;
                str[k] = hexDigits[(byte0 >>> 4) & 15];
                k = k2 + 1;
                str[k2] = hexDigits[byte0 & 15];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: RC4解密  reason: contains not printable characters */
    public static String m546RC4(String data, String key) {
        if (data == null || key == null) {
            return "";
        }
        try {
            return new String(RC4Base(HexString2Bytes(data), key), "GBK");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: RC4加密2  reason: contains not printable characters */
    public static byte[] m545RC42(byte[] data, String key) {
        if (data == null || key == null) {
            return new byte[0];
        }
        try {
            byte[] a = RC4Base(data, key);
            byte[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
            int j = a.length;
            byte[] str = new byte[j * 2];
            int k = 0;
            for (byte byte0 : a) {
                int k2 = k + 1;
                str[k] = hexDigits[(byte0 >>> 4) & 15];
                k = k2 + 1;
                str[k2] = hexDigits[byte0 & 15];
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @SimpleFunction
    /* renamed from: RC4解密2  reason: contains not printable characters */
    public static byte[] m547RC42(byte[] data, String key) {
        if (data == null || key == null) {
            return new byte[0];
        }
        try {
            return RC4Base(data, key);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] HexString2Bytes(String src) {
        try {
            int size = src.length();
            byte[] ret = new byte[size / 2];
            byte[] tmp = src.getBytes("GBK");
            for (int i = 0; i < size / 2; i++) {
                ret[i] = uniteBytes(tmp[i * 2], tmp[(i * 2) + 1]);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte uniteBytes(byte src0, byte src1) {
        return (byte) (((char) (((char) Byte.decode("0x" + new String(new byte[]{src0})).byteValue()) << 4)) ^ ((char) Byte.decode("0x" + new String(new byte[]{src1})).byteValue()));
    }

    private static byte[] RC4Base(byte[] input, String mKkey) {
        int x = 0;
        int y = 0;
        byte[] key = initKey(mKkey);
        byte[] result = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            x = (x + 1) & 255;
            y = ((key[x] & 255) + y) & 255;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            result[i] = (byte) (input[i] ^ key[((key[x] & 255) + (key[y] & 255)) & 255]);
        }
        return result;
    }

    private static byte[] initKey(String aKey) {
        try {
            byte[] b_key = aKey.getBytes("GBK");
            byte[] state = new byte[256];
            for (int i = 0; i < 256; i++) {
                state[i] = (byte) i;
            }
            int index1 = 0;
            int index2 = 0;
            if (b_key == null || b_key.length == 0) {
                return null;
            }
            for (int i2 = 0; i2 < 256; i2++) {
                index2 = ((b_key[index1] & 255) + (state[i2] & 255) + index2) & 255;
                byte tmp = state[i2];
                state[i2] = state[index2];
                state[index2] = tmp;
                index1 = (index1 + 1) % b_key.length;
            }
            return state;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SimpleFunction
    /* renamed from: DES加密  reason: contains not printable characters */
    public static String m542DES(String encryptString, String encryptKey) throws Exception {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
            SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes("GBK"), "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(1, key, zeroIv);
            return m540BASE64(cipher.doFinal(encryptString.getBytes("GBK")));
        } catch (Exception e) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: DES解密  reason: contains not printable characters */
    public static String m543DES(String decryptString, String decryptKey) throws Exception {
        try {
            byte[] byteMi = m541BASE64(decryptString);
            IvParameterSpec zeroIv = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
            SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes("GBK"), "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(2, key, zeroIv);
            return new String(cipher.doFinal(byteMi), "GBK");
        } catch (Exception e) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: BASE64编码  reason: contains not printable characters */
    public static String m540BASE64(byte[] data) {
        try {
            return Base64.encode(data);
        } catch (Exception e) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: BASE64解码  reason: contains not printable characters */
    public static byte[] m541BASE64(String data) {
        try {
            return Base64.decode(data);
        } catch (UnsupportedEncodingException e) {
            return new byte[0];
        }
    }

    @SimpleFunction
    /* renamed from: Authcode加密  reason: contains not printable characters */
    public static String m538Authcode(String source, String key) {
        try {
            return Authcode.Encode(source, key);
        } catch (Exception e) {
            return "";
        }
    }

    @SimpleFunction
    /* renamed from: Authcode解密  reason: contains not printable characters */
    public static String m539Authcode(String source, String key) {
        try {
            return Authcode.Decode(source, key);
        } catch (Exception e) {
            return "";
        }
    }
}
