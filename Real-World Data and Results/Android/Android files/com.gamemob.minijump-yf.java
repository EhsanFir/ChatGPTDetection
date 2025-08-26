package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: dalvik_source_F21B0AC7CD1A40555348BF381C55C4093BB9729637F11C252DBEED88D52FB653.apk */
/* loaded from: classes.dex */
public final class yf implements tr {
    private final byte[] a;
    private final byte[] b;
    private final SecretKeySpec c;
    private final int d;

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public yf(byte[] bArr, int i) {
        if (i == 12 || i == 16) {
            this.d = i;
            this.c = new SecretKeySpec(bArr, "AES");
            Cipher $r4 = Cipher.getInstance("AES/ECB/NOPADDING");
            $r4.init(1, this.c);
            this.a = a($r4.doFinal(new byte[16]));
            this.b = a(this.a);
            return;
        }
        throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    private final byte[] a(Cipher cipher, int i, byte[] $r2, int $i1, int i2) {
        byte[] $r22;
        byte[] $r23;
        byte[] $r3 = new byte[16];
        $r3[15] = (byte) i;
        if (i2 == 0) {
            $r23 = b($r3, this.a);
        } else {
            byte[] $r32 = cipher.doFinal($r3);
            int $i4 = 0;
            while (i2 - $i4 > 16) {
                for (int $i5 = 0; $i5 < 16; $i5++) {
                    $r32[$i5] = (byte) ($r32[$i5] ^ $r2[($i1 + $i4) + $i5]);
                }
                $r32 = cipher.doFinal($r32);
                $i4 += 16;
            }
            byte[] $r24 = Arrays.copyOfRange($r2, $i4 + $i1, $i1 + i2);
            if ($r24.length == 16) {
                $r22 = b($r24, this.a);
            } else {
                byte[] $r4 = Arrays.copyOf(this.b, 16);
                for (int i3 = 0; i3 < $r24.length; i3++) {
                    $r4[i3] = (byte) ($r4[i3] ^ $r24[i3]);
                }
                $r4[$r24.length] = (byte) ((short) (((short) $r4[$r24.length]) ^ 128));
                $r22 = $r4;
            }
            $r23 = b($r32, $r22);
        }
        return cipher.doFinal($r23);
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    private static byte[] a(byte[] bArr) {
        byte[] $r1 = new byte[16];
        short $s1 = 0;
        int $i2 = 0;
        while ($i2 < 15) {
            int $i0 = $i2 + 1;
            $r1[$i2] = (byte) ((bArr[$i2] << 1) ^ (((short) (((short) bArr[$i0]) & 255)) >>> 7));
            $i2 = $i0;
        }
        int $i22 = bArr[15] << 1;
        if (((short) (((short) bArr[0]) & 128)) != 0) {
            $s1 = 135;
        }
        $r1[15] = (byte) ($i22 ^ $s1);
        return $r1;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    private static byte[] b(byte[] bArr, byte[] bArr2) {
        int $i0 = bArr.length;
        byte[] $r2 = new byte[$i0];
        for (int $i2 = 0; $i2 < $i0; $i2++) {
            $r2[$i2] = (byte) (bArr[$i2] ^ bArr2[$i2]);
        }
        return $r2;
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    @Override // com.google.android.gms.internal.ads.tr
    public final byte[] a(byte[] bArr, byte[] $r2) {
        int $i0 = bArr.length;
        int $i1 = this.d;
        if ($i0 <= (Integer.MAX_VALUE - $i1) - 16) {
            byte[] $r3 = new byte[bArr.length + $i1 + 16];
            byte[] $r4 = zj.a($i1);
            System.arraycopy($r4, 0, $r3, 0, this.d);
            Cipher $r5 = Cipher.getInstance("AES/ECB/NOPADDING");
            $r5.init(1, this.c);
            byte[] $r42 = a($r5, 0, $r4, 0, $r4.length);
            if ($r2 == null) {
                $r2 = new byte[0];
            }
            byte[] $r22 = a($r5, 1, $r2, 0, $r2.length);
            Cipher $r7 = Cipher.getInstance("AES/CTR/NOPADDING");
            $r7.init(1, this.c, new IvParameterSpec($r42));
            $r7.doFinal(bArr, 0, bArr.length, $r3, this.d);
            byte[] $r9 = a($r5, 2, $r3, this.d, bArr.length);
            int $i02 = bArr.length + this.d;
            for (int $i12 = 0; $i12 < 16; $i12++) {
                $r3[$i02 + $i12] = (byte) (((byte) ($r22[$i12] ^ $r42[$i12])) ^ $r9[$i12]);
            }
            return $r3;
        }
        throw new GeneralSecurityException("plaintext too long");
    }
}
