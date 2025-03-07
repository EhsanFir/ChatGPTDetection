package com.google.android.gms.internal.ads;

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
public final class ahj {
    private static Cipher b;
    private static final Object c = new Object();
    private static final Object d = new Object();
    private final SecureRandom a = null;

    public ahj(SecureRandom secureRandom) {
    }

    private static Cipher a() {
        Cipher cipher;
        synchronized (d) {
            if (b == null) {
                b = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            cipher = b;
        }
        return cipher;
    }

    public final String a(byte[] bArr, byte[] bArr2) {
        byte[] doFinal;
        byte[] iv;
        if (bArr.length == 16) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                synchronized (c) {
                    a().init(1, secretKeySpec, (SecureRandom) null);
                    doFinal = a().doFinal(bArr2);
                    iv = a().getIV();
                }
                int length = doFinal.length + iv.length;
                ByteBuffer allocate = ByteBuffer.allocate(length);
                allocate.put(iv).put(doFinal);
                allocate.flip();
                byte[] bArr3 = new byte[length];
                allocate.get(bArr3);
                return agg.a(bArr3, false);
            } catch (InvalidKeyException e) {
                throw new ahk(this, e);
            } catch (NoSuchAlgorithmException e2) {
                throw new ahk(this, e2);
            } catch (BadPaddingException e3) {
                throw new ahk(this, e3);
            } catch (IllegalBlockSizeException e4) {
                throw new ahk(this, e4);
            } catch (NoSuchPaddingException e5) {
                throw new ahk(this, e5);
            }
        } else {
            throw new ahk(this);
        }
    }

    public final byte[] a(String str) {
        try {
            byte[] a = agg.a(str, false);
            if (a.length == 32) {
                byte[] bArr = new byte[16];
                ByteBuffer.wrap(a, 4, 16).get(bArr);
                for (int i = 0; i < 16; i++) {
                    bArr[i] = (byte) (bArr[i] ^ 68);
                }
                return bArr;
            }
            throw new ahk(this);
        } catch (IllegalArgumentException e) {
            throw new ahk(this, e);
        }
    }

    public final byte[] a(byte[] bArr, String str) {
        byte[] doFinal;
        if (bArr.length == 16) {
            try {
                byte[] a = agg.a(str, false);
                if (a.length > 16) {
                    ByteBuffer allocate = ByteBuffer.allocate(a.length);
                    allocate.put(a);
                    allocate.flip();
                    byte[] bArr2 = new byte[16];
                    byte[] bArr3 = new byte[a.length - 16];
                    allocate.get(bArr2);
                    allocate.get(bArr3);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                    synchronized (c) {
                        a().init(2, secretKeySpec, new IvParameterSpec(bArr2));
                        doFinal = a().doFinal(bArr3);
                    }
                    return doFinal;
                }
                throw new ahk(this);
            } catch (IllegalArgumentException e) {
                throw new ahk(this, e);
            } catch (InvalidAlgorithmParameterException e2) {
                throw new ahk(this, e2);
            } catch (InvalidKeyException e3) {
                throw new ahk(this, e3);
            } catch (NoSuchAlgorithmException e4) {
                throw new ahk(this, e4);
            } catch (BadPaddingException e5) {
                throw new ahk(this, e5);
            } catch (IllegalBlockSizeException e6) {
                throw new ahk(this, e6);
            } catch (NoSuchPaddingException e7) {
                throw new ahk(this, e7);
            }
        } else {
            throw new ahk(this);
        }
    }
}
