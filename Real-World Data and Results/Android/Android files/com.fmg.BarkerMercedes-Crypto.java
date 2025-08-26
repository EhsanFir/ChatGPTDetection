package com.urbanairship.push.embedded;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: dalvik_source_C686B544E8671B4542CEFC2ED2BDC05BA78D4605B99FB9D44B7FE1953BABF6D6.apk */
/* loaded from: classes.dex */
public class Crypto {
    private static final String algo = "AES/CBC/PKCS5Padding";
    private static final byte[] ivBytes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final IvParameterSpec ivs = new IvParameterSpec(ivBytes);
    private Cipher cipher;
    private SecretKeySpec keySpec;

    /* compiled from: dalvik_source_C686B544E8671B4542CEFC2ED2BDC05BA78D4605B99FB9D44B7FE1953BABF6D6.apk */
    /* loaded from: classes.dex */
    public class NullUUID extends Exception {
        private static final long serialVersionUID = -2805631869260469310L;

        public NullUUID() {
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public Crypto(UUID uuid) throws NullUUID {
        if (uuid == null) {
            throw new NullUUID();
        }
        try {
            this.cipher = Cipher.getInstance(algo);
            this.keySpec = new SecretKeySpec(uuid.toString().replace("-", "").getBytes(), "AES");
        } catch (NoSuchAlgorithmException $r7) {
            $r7.printStackTrace();
        } catch (NoSuchPaddingException $r8) {
            $r8.printStackTrace();
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public byte[] decrypt(byte[] bArr) {
        try {
            this.cipher.init(2, this.keySpec, ivs);
            return this.cipher.doFinal(bArr);
        } catch (Exception $r5) {
            $r5.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Can't parse signature for local variable: 
    java.lang.NullPointerException
     */
    public byte[] encrypt(byte[] bArr) {
        try {
            this.cipher.init(1, this.keySpec, ivs);
            return this.cipher.doFinal(bArr);
        } catch (Exception $r5) {
            $r5.printStackTrace();
            return null;
        }
    }
}
