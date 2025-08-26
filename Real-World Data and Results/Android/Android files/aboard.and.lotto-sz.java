package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class sz {
    private final sm a;
    private final SecureRandom b;

    public sz(sm smVar, SecureRandom secureRandom) {
        this.a = smVar;
        this.b = secureRandom;
    }

    static void a(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ 68);
        }
    }

    public byte[] a(String str) {
        try {
            byte[] a = this.a.a(str, false);
            if (a.length != 32) {
                throw new ta(this);
            }
            byte[] bArr = new byte[16];
            ByteBuffer.wrap(a, 4, 16).get(bArr);
            a(bArr);
            return bArr;
        } catch (IllegalArgumentException e) {
            throw new ta(this, e);
        }
    }

    public byte[] a(byte[] bArr, String str) {
        if (bArr.length != 16) {
            throw new ta(this);
        }
        try {
            byte[] a = this.a.a(str, false);
            if (a.length <= 16) {
                throw new ta(this);
            }
            ByteBuffer allocate = ByteBuffer.allocate(a.length);
            allocate.put(a);
            allocate.flip();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[a.length - 16];
            allocate.get(bArr2);
            allocate.get(bArr3);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(bArr2));
            return instance.doFinal(bArr3);
        } catch (IllegalArgumentException e) {
            throw new ta(this, e);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new ta(this, e2);
        } catch (InvalidKeyException e3) {
            throw new ta(this, e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new ta(this, e4);
        } catch (BadPaddingException e5) {
            throw new ta(this, e5);
        } catch (IllegalBlockSizeException e6) {
            throw new ta(this, e6);
        } catch (NoSuchPaddingException e7) {
            throw new ta(this, e7);
        }
    }
}
