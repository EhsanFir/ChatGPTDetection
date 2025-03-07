package app.s.jagoanssh_com;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static char[] f520a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private IvParameterSpec b;
    private SecretKeySpec c;
    private Cipher d;

    public a(String str, String str2) {
        this.b = new IvParameterSpec(str2.getBytes());
        this.c = new SecretKeySpec(str.getBytes(), "AES");
        try {
            this.d = Cipher.getInstance("AES/CBC/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
    }

    public static byte[] b(String str) {
        if (str == null || str.length() < 2) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public byte[] a(String str) {
        if (str == null || str.length() == 0) {
            throw new Exception("Empty string");
        }
        try {
            this.d.init(2, this.c, this.b);
            byte[] doFinal = this.d.doFinal(b(str));
            if (doFinal.length <= 0) {
                return doFinal;
            }
            int i = 0;
            for (int length = doFinal.length - 1; length >= 0; length--) {
                if (doFinal[length] == 0) {
                    i++;
                }
            }
            if (i <= 0) {
                return doFinal;
            }
            byte[] bArr = new byte[doFinal.length - i];
            System.arraycopy(doFinal, 0, bArr, 0, doFinal.length - i);
            return bArr;
        } catch (Exception e) {
            throw new Exception("[decrypt] " + e.getMessage());
        }
    }
}
