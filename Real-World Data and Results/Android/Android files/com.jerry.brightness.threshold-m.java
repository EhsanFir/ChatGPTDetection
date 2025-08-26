package com.baidu.mobstat;

import android.annotation.SuppressLint;
import com.androlua.BuildConfig;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class m {

    /* loaded from: classes.dex */
    public static class a {
        public static byte[] a() {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128, new SecureRandom());
            return instance.generateKey().getEncoded();
        }

        @SuppressLint({"TrulyRandom"})
        public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return instance.doFinal(bArr3);
        }

        public static byte[] b() {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            return bArr;
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public static byte[] a(int i, byte[] bArr) {
            int i2 = i - 1;
            if (i2 >= 0) {
                String[] strArr = r.f1134a;
                if (strArr.length > i2) {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(strArr[i2].getBytes(), "AES");
                    Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    instance.init(1, secretKeySpec);
                    return instance.doFinal(bArr);
                }
            }
            return new byte[0];
        }

        public static byte[] b(int i, byte[] bArr) {
            int i2 = i - 1;
            if (i2 >= 0) {
                String[] strArr = r.f1134a;
                if (strArr.length > i2) {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(strArr[i2].getBytes(), "AES");
                    Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    instance.init(2, secretKeySpec);
                    return instance.doFinal(bArr);
                }
            }
            return new byte[0];
        }

        public static String c(int i, byte[] bArr) {
            try {
                return p.b(a(i, bArr));
            } catch (Exception unused) {
                return BuildConfig.FLAVOR;
            }
        }
    }
}
