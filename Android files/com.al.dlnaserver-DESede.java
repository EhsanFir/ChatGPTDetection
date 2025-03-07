package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.a.s.n;
import org.bouncycastle.b.e.ak;
import org.bouncycastle.b.e.p;
import org.bouncycastle.b.e.q;
import org.bouncycastle.b.h.a;
import org.bouncycastle.b.h.c;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.l;
import org.bouncycastle.b.x;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes.dex */
public final class DESede {

    /* loaded from: classes.dex */
    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[8];
            if (this.c == null) {
                this.c = l.a();
            }
            this.c.nextBytes(bArr);
            try {
                AlgorithmParameters a2 = a("DES");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for DES parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new p()), 64);
        }
    }

    /* loaded from: classes.dex */
    public static class CBCMAC extends BaseMac {
        public CBCMAC() {
            super(new a(new p()));
        }
    }

    /* loaded from: classes.dex */
    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new c(new p()));
        }
    }

    /* loaded from: classes.dex */
    public static class DESede64 extends BaseMac {
        public DESede64() {
            super(new a(new p(), 64));
        }
    }

    /* loaded from: classes.dex */
    public static class DESede64with7816d4 extends BaseMac {
        public DESede64with7816d4() {
            super(new a(new p(), 64, new org.bouncycastle.b.j.c()));
        }
    }

    /* loaded from: classes.dex */
    public static class DESedeCFB8 extends BaseMac {
        public DESedeCFB8() {
            super(new org.bouncycastle.b.h.b(new p()));
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new p());
        }
    }

    /* loaded from: classes.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("DESede", null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        public SecretKey engineGenerateSecret(KeySpec keySpec) {
            return keySpec instanceof DESedeKeySpec ? new SecretKeySpec(((DESedeKeySpec) keySpec).getKey(), "DESede") : super.engineGenerateSecret(keySpec);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        protected KeySpec engineGetKeySpec(SecretKey secretKey, Class cls) {
            DESedeKeySpec dESedeKeySpec;
            if (cls == null) {
                throw new InvalidKeySpecException("keySpec parameter is null");
            } else if (secretKey == null) {
                throw new InvalidKeySpecException("key parameter is null");
            } else if (SecretKeySpec.class.isAssignableFrom(cls)) {
                return new SecretKeySpec(secretKey.getEncoded(), this.f1174a);
            } else {
                if (DESedeKeySpec.class.isAssignableFrom(cls)) {
                    byte[] encoded = secretKey.getEncoded();
                    try {
                        if (encoded.length == 16) {
                            byte[] bArr = new byte[24];
                            System.arraycopy(encoded, 0, bArr, 0, 16);
                            System.arraycopy(encoded, 0, bArr, 16, 8);
                            dESedeKeySpec = new DESedeKeySpec(bArr);
                        } else {
                            dESedeKeySpec = new DESedeKeySpec(encoded);
                        }
                        return dESedeKeySpec;
                    } catch (Exception e) {
                        throw new InvalidKeySpecException(e.toString());
                    }
                } else {
                    throw new InvalidKeySpecException("Invalid KeySpec");
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGenerator extends BaseKeyGenerator {
        private boolean f = false;

        public KeyGenerator() {
            super("DESede", 192, new org.bouncycastle.b.f.c());
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator, javax.crypto.KeyGeneratorSpi
        protected SecretKey engineGenerateKey() {
            if (this.e) {
                this.d.a(new x(l.a(), this.c));
                this.e = false;
            }
            if (this.f) {
                return new SecretKeySpec(this.d.a(), this.f1172a);
            }
            byte[] a2 = this.d.a();
            System.arraycopy(a2, 0, a2, 16, 8);
            return new SecretKeySpec(a2, this.f1172a);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator, javax.crypto.KeyGeneratorSpi
        public void engineInit(int i, SecureRandom secureRandom) {
            super.engineInit(i, secureRandom);
            this.f = true;
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGenerator3 extends BaseKeyGenerator {
        public KeyGenerator3() {
            super("DESede3", 192, new org.bouncycastle.b.f.c());
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends AlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1120a = DESede.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.DESEDE", f1120a + "$ECB");
            configurableProvider.a("Cipher", n.D, f1120a + "$CBC");
            configurableProvider.a("Cipher.DESEDEWRAP", f1120a + "$Wrap");
            configurableProvider.a("Cipher", n.bK, f1120a + "$Wrap");
            configurableProvider.a("Cipher.DESEDERFC3211WRAP", f1120a + "$RFC3211");
            configurableProvider.a("Alg.Alias.Cipher.DESEDERFC3217WRAP", "DESEDEWRAP");
            configurableProvider.a("Alg.Alias.Cipher.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.Cipher.TDEAWRAP", "DESEDEWRAP");
            configurableProvider.a("Alg.Alias.KeyGenerator.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.TDEA", "DESEDE");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.TDEA", "DESEDE");
            if (configurableProvider.b("MessageDigest", "SHA-1")) {
                configurableProvider.a("Cipher.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", f1120a + "$PBEWithSHAAndDES3Key");
                configurableProvider.a("Cipher.BROKENPBEWITHSHAAND3-KEYTRIPLEDES-CBC", f1120a + "$BrokePBEWithSHAAndDES3Key");
                configurableProvider.a("Cipher.OLDPBEWITHSHAAND3-KEYTRIPLEDES-CBC", f1120a + "$OldPBEWithSHAAndDES3Key");
                configurableProvider.a("Cipher.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", f1120a + "$PBEWithSHAAndDES2Key");
                configurableProvider.a("Cipher.BROKENPBEWITHSHAAND2-KEYTRIPLEDES-CBC", f1120a + "$BrokePBEWithSHAAndDES2Key");
                configurableProvider.a("Alg.Alias.Cipher", n.bF, "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher", n.bG, "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1ANDDESEDE", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND3-KEYTRIPLEDES-CBC", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND2-KEYTRIPLEDES-CBC", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHAAND3-KEYDESEDE-CBC", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHAAND2-KEYDESEDE-CBC", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND3-KEYDESEDE-CBC", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1AND2-KEYDESEDE-CBC", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
                configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1ANDDESEDE-CBC", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            }
            configurableProvider.a("KeyGenerator.DESEDE", f1120a + "$KeyGenerator");
            configurableProvider.a("KeyGenerator." + n.D, f1120a + "$KeyGenerator3");
            configurableProvider.a("KeyGenerator.DESEDEWRAP", f1120a + "$KeyGenerator");
            configurableProvider.a("SecretKeyFactory.DESEDE", f1120a + "$KeyFactory");
            configurableProvider.a("SecretKeyFactory", org.bouncycastle.a.r.b.h, f1120a + "$KeyFactory");
            configurableProvider.a("Mac.DESEDECMAC", f1120a + "$CMAC");
            configurableProvider.a("Mac.DESEDEMAC", f1120a + "$CBCMAC");
            configurableProvider.a("Alg.Alias.Mac.DESEDE", "DESEDEMAC");
            configurableProvider.a("Mac.DESEDEMAC/CFB8", f1120a + "$DESedeCFB8");
            configurableProvider.a("Alg.Alias.Mac.DESEDE/CFB8", "DESEDEMAC/CFB8");
            configurableProvider.a("Mac.DESEDEMAC64", f1120a + "$DESede64");
            configurableProvider.a("Alg.Alias.Mac.DESEDE64", "DESEDEMAC64");
            configurableProvider.a("Mac.DESEDEMAC64WITHISO7816-4PADDING", f1120a + "$DESede64with7816d4");
            configurableProvider.a("Alg.Alias.Mac.DESEDE64WITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESEDEISO9797ALG1MACWITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESEDEISO9797ALG1WITHISO7816-4PADDING", "DESEDEMAC64WITHISO7816-4PADDING");
            configurableProvider.a("AlgorithmParameters.DESEDE", "org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + n.D, "DESEDE");
            configurableProvider.a("AlgorithmParameterGenerator.DESEDE", f1120a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + n.D, "DESEDE");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", f1120a + "$PBEWithSHAAndDES3KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", f1120a + "$PBEWithSHAAndDES2KeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDDESEDE", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND3-KEYTRIPLEDES-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND2-KEYTRIPLEDES-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES3KEY-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDDES2KEY-CBC", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBE", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.3", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.4", "PBEWITHSHAAND2-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWithSHAAnd3KeyTripleDES", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.3", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.4", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.Cipher.PBEWithSHAAnd3KeyTripleDES", "PBEWITHSHAAND3-KEYTRIPLEDES-CBC");
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHAAndDES2Key extends BaseBlockCipher {
        public PBEWithSHAAndDES2Key() {
            super(new b(new p()), 2, 1, 128, 8);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHAAndDES2KeyFactory extends DES.DESPBEKeyFactory {
        public PBEWithSHAAndDES2KeyFactory() {
            super("PBEwithSHAandDES2Key-CBC", n.bG, 2, 1, 128);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHAAndDES3Key extends BaseBlockCipher {
        public PBEWithSHAAndDES3Key() {
            super(new b(new p()), 2, 1, 192, 8);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHAAndDES3KeyFactory extends DES.DESPBEKeyFactory {
        public PBEWithSHAAndDES3KeyFactory() {
            super("PBEwithSHAandDES3Key-CBC", n.bF, 2, 1, 192);
        }
    }

    /* loaded from: classes.dex */
    public static class RFC3211 extends BaseWrapCipher {
        public RFC3211() {
            super(new ak(new p()), 8);
        }
    }

    /* loaded from: classes.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new q());
        }
    }

    private DESede() {
    }
}
