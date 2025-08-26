package com.google.ads;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class an {

    /* loaded from: classes.dex */
    public static class a extends Exception {
        public a() {
        }

        public a(Throwable th) {
            super(th);
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    static void a(byte[] bArr) {
        for (int $i0 = 0; $i0 < bArr.length; $i0++) {
            bArr[$i0] = (byte) (bArr[$i0] ^ 68);
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static byte[] a(String str) throws ap, a {
        byte[] $r1 = aq.a(str);
        if ($r1.length != 32) {
            throw new a();
        }
        ByteBuffer $r3 = ByteBuffer.wrap($r1, 4, 16);
        byte[] $r12 = new byte[16];
        $r3.get($r12);
        a($r12);
        return $r12;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public static byte[] a(byte[] bArr, String str) throws a, ap {
        if (bArr.length != 16) {
            throw new a();
        }
        try {
            byte[] $r5 = aq.a(str);
            if ($r5.length <= 16) {
                throw new a();
            }
            ByteBuffer $r6 = ByteBuffer.allocate($r5.length);
            $r6.put($r5);
            $r6.flip();
            byte[] $r0 = new byte[16];
            byte[] $r52 = new byte[$r5.length - 16];
            $r6.get($r0);
            $r6.get($r52);
            SecretKeySpec $r7 = new SecretKeySpec(bArr, "AES");
            Cipher $r8 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            $r8.init(2, $r7, new IvParameterSpec($r0));
            return $r8.doFinal($r52);
        } catch (InvalidAlgorithmParameterException $r9) {
            throw new a($r9);
        } catch (InvalidKeyException $r13) {
            throw new a($r13);
        } catch (NoSuchAlgorithmException $r14) {
            throw new a($r14);
        } catch (BadPaddingException $r10) {
            throw new a($r10);
        } catch (IllegalBlockSizeException $r12) {
            throw new a($r12);
        } catch (NoSuchPaddingException $r11) {
            throw new a($r11);
        }
    }
}
