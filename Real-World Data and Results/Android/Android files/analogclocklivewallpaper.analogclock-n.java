package com.google.android.gms.e;

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
public final class n {
    private final m a;
    private final SecureRandom b = null;

    /* loaded from: classes.dex */
    public class a extends Exception {
        public a() {
        }

        public a(Throwable th) {
            super(th);
        }
    }

    public n(m mVar) {
        this.a = mVar;
    }

    public final byte[] a(String str) throws a {
        try {
            byte[] a2 = this.a.a(str);
            if (a2.length != 32) {
                throw new a();
            }
            byte[] bArr = new byte[16];
            ByteBuffer.wrap(a2, 4, 16).get(bArr);
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (bArr[i] ^ 68);
            }
            return bArr;
        } catch (IllegalArgumentException e) {
            throw new a(e);
        }
    }

    public final byte[] a(byte[] bArr, String str) throws a {
        if (bArr.length != 16) {
            throw new a();
        }
        try {
            byte[] a2 = this.a.a(str);
            if (a2.length <= 16) {
                throw new a();
            }
            ByteBuffer allocate = ByteBuffer.allocate(a2.length);
            allocate.put(a2);
            allocate.flip();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[a2.length - 16];
            allocate.get(bArr2);
            allocate.get(bArr3);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(bArr2));
            return instance.doFinal(bArr3);
        } catch (IllegalArgumentException e) {
            throw new a(e);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new a(e2);
        } catch (InvalidKeyException e3) {
            throw new a(e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new a(e4);
        } catch (BadPaddingException e5) {
            throw new a(e5);
        } catch (IllegalBlockSizeException e6) {
            throw new a(e6);
        } catch (NoSuchPaddingException e7) {
            throw new a(e7);
        }
    }
}
