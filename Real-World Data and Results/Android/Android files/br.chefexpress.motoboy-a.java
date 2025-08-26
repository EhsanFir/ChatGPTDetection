package br.chefexpress.motoboy;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private IvParameterSpec f510a;
    private SecretKeySpec b;
    private Cipher c;

    public a(String str, String str2) {
        this.f510a = new IvParameterSpec(str2.getBytes());
        this.b = new SecretKeySpec(str.getBytes(), "AES");
        try {
            this.c = Cipher.getInstance("AES/CBC/NoPadding");
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
            this.c.init(2, this.b, this.f510a);
            byte[] doFinal = this.c.doFinal(b(str));
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
