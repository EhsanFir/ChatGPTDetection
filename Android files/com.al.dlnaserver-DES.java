package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.a.p;
import org.bouncycastle.a.s.n;
import org.bouncycastle.b.ae;
import org.bouncycastle.b.e.ak;
import org.bouncycastle.b.e.o;
import org.bouncycastle.b.h.a;
import org.bouncycastle.b.h.c;
import org.bouncycastle.b.h.h;
import org.bouncycastle.b.i;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.k.ba;
import org.bouncycastle.b.k.be;
import org.bouncycastle.b.l;
import org.bouncycastle.b.x;
import org.bouncycastle.jcajce.PBKDF1Key;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes.dex */
public final class DES {

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
            super(new b(new o()), 64);
        }
    }

    /* loaded from: classes.dex */
    public static class CBCMAC extends BaseMac {
        public CBCMAC() {
            super(new a(new o()));
        }
    }

    /* loaded from: classes.dex */
    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new c(new o()));
        }
    }

    /* loaded from: classes.dex */
    public static class DES64 extends BaseMac {
        public DES64() {
            super(new a(new o(), 64));
        }
    }

    /* loaded from: classes.dex */
    public static class DES64with7816d4 extends BaseMac {
        public DES64with7816d4() {
            super(new a(new o(), 64, new org.bouncycastle.b.j.c()));
        }
    }

    /* loaded from: classes.dex */
    public static class DES9797Alg3 extends BaseMac {
        public DES9797Alg3() {
            super(new h(new o()));
        }
    }

    /* loaded from: classes.dex */
    public static class DES9797Alg3with7816d4 extends BaseMac {
        public DES9797Alg3with7816d4() {
            super(new h(new o(), new org.bouncycastle.b.j.c()));
        }
    }

    /* loaded from: classes.dex */
    public static class DESCFB8 extends BaseMac {
        public DESCFB8() {
            super(new org.bouncycastle.b.h.b(new o()));
        }
    }

    /* loaded from: classes.dex */
    public static class DESPBEKeyFactory extends BaseSecretKeyFactory {
        private int d;
        private int e;
        private int f;
        private boolean c = true;
        private int g = 64;

        public DESPBEKeyFactory(String str, p pVar, int i, int i2, int i3) {
            super(str, pVar);
            this.d = i;
            this.e = i2;
            this.f = i3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        public SecretKey engineGenerateSecret(KeySpec keySpec) {
            if (keySpec instanceof PBEKeySpec) {
                PBEKeySpec pBEKeySpec = (PBEKeySpec) keySpec;
                if (pBEKeySpec.getSalt() != null) {
                    i a2 = this.c ? PBE.Util.a(pBEKeySpec, this.d, this.e, this.f, this.g) : PBE.Util.a(pBEKeySpec, this.d, this.e, this.f);
                    org.bouncycastle.b.k.c.a((a2 instanceof be ? (ba) ((be) a2).b() : (ba) a2).a());
                    return new BCPBEKey(this.f1174a, this.b, this.d, this.e, this.f, this.g, pBEKeySpec, a2);
                } else if (this.d != 0 && this.d != 4) {
                    return new BCPBEKey(this.f1174a, this.b, this.d, this.e, this.f, this.g, pBEKeySpec, null);
                } else {
                    return new PBKDF1Key(pBEKeySpec.getPassword(), this.d == 0 ? ae.f484a : ae.b);
                }
            } else {
                throw new InvalidKeySpecException("Invalid KeySpec");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new o());
        }
    }

    /* loaded from: classes.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("DES", null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        public SecretKey engineGenerateSecret(KeySpec keySpec) {
            return keySpec instanceof DESKeySpec ? new SecretKeySpec(((DESKeySpec) keySpec).getKey(), "DES") : super.engineGenerateSecret(keySpec);
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory, javax.crypto.SecretKeyFactorySpi
        protected KeySpec engineGetKeySpec(SecretKey secretKey, Class cls) {
            if (cls == null) {
                throw new InvalidKeySpecException("keySpec parameter is null");
            } else if (secretKey == null) {
                throw new InvalidKeySpecException("key parameter is null");
            } else if (SecretKeySpec.class.isAssignableFrom(cls)) {
                return new SecretKeySpec(secretKey.getEncoded(), this.f1174a);
            } else {
                if (DESKeySpec.class.isAssignableFrom(cls)) {
                    try {
                        return new DESKeySpec(secretKey.getEncoded());
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
        public KeyGenerator() {
            super("DES", 64, new org.bouncycastle.b.f.b());
        }

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator, javax.crypto.KeyGeneratorSpi
        protected SecretKey engineGenerateKey() {
            if (this.e) {
                this.d.a(new x(l.a(), this.c));
                this.e = false;
            }
            return new SecretKeySpec(this.d.a(), this.f1172a);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator, javax.crypto.KeyGeneratorSpi
        public void engineInit(int i, SecureRandom secureRandom) {
            super.engineInit(i, secureRandom);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithMD2 extends BaseBlockCipher {
        public PBEWithMD2() {
            super(new b(new o()), 0, 5, 64, 8);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithMD2KeyFactory extends DESPBEKeyFactory {
        public PBEWithMD2KeyFactory() {
            super("PBEwithMD2andDES", n.t_, 0, 5, 64);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithMD5 extends BaseBlockCipher {
        public PBEWithMD5() {
            super(new b(new o()), 0, 0, 64, 8);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithMD5KeyFactory extends DESPBEKeyFactory {
        public PBEWithMD5KeyFactory() {
            super("PBEwithMD5andDES", n.w, 0, 0, 64);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHA1 extends BaseBlockCipher {
        public PBEWithSHA1() {
            super(new b(new o()), 0, 1, 64, 8);
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHA1KeyFactory extends DESPBEKeyFactory {
        public PBEWithSHA1KeyFactory() {
            super("PBEwithSHA1andDES", n.y, 0, 1, 64);
        }
    }

    /* loaded from: classes.dex */
    public static class RFC3211 extends BaseWrapCipher {
        public RFC3211() {
            super(new ak(new o()), 8);
        }
    }

    private DES() {
    }

    /* loaded from: classes.dex */
    public static class Mappings extends AlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1119a = DES.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.DES", f1119a + "$ECB");
            configurableProvider.a("Cipher", org.bouncycastle.a.r.b.e, f1119a + "$CBC");
            p pVar = org.bouncycastle.a.r.b.e;
            configurableProvider.a("Alg.Alias.KeyGenerator." + pVar.b(), "DES");
            configurableProvider.a("Alg.Alias.KeyFactory." + pVar.b(), "DES");
            configurableProvider.a("Cipher.DESRFC3211WRAP", f1119a + "$RFC3211");
            configurableProvider.a("KeyGenerator.DES", f1119a + "$KeyGenerator");
            configurableProvider.a("SecretKeyFactory.DES", f1119a + "$KeyFactory");
            configurableProvider.a("Mac.DESCMAC", f1119a + "$CMAC");
            configurableProvider.a("Mac.DESMAC", f1119a + "$CBCMAC");
            configurableProvider.a("Alg.Alias.Mac.DES", "DESMAC");
            configurableProvider.a("Mac.DESMAC/CFB8", f1119a + "$DESCFB8");
            configurableProvider.a("Alg.Alias.Mac.DES/CFB8", "DESMAC/CFB8");
            configurableProvider.a("Mac.DESMAC64", f1119a + "$DES64");
            configurableProvider.a("Alg.Alias.Mac.DES64", "DESMAC64");
            configurableProvider.a("Mac.DESMAC64WITHISO7816-4PADDING", f1119a + "$DES64with7816d4");
            configurableProvider.a("Alg.Alias.Mac.DES64WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESISO9797ALG1MACWITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Alg.Alias.Mac.DESISO9797ALG1WITHISO7816-4PADDING", "DESMAC64WITHISO7816-4PADDING");
            configurableProvider.a("Mac.DESWITHISO9797", f1119a + "$DES9797Alg3");
            configurableProvider.a("Alg.Alias.Mac.DESISO9797MAC", "DESWITHISO9797");
            configurableProvider.a("Mac.ISO9797ALG3MAC", f1119a + "$DES9797Alg3");
            configurableProvider.a("Alg.Alias.Mac.ISO9797ALG3", "ISO9797ALG3MAC");
            configurableProvider.a("Mac.ISO9797ALG3WITHISO7816-4PADDING", f1119a + "$DES9797Alg3with7816d4");
            configurableProvider.a("Alg.Alias.Mac.ISO9797ALG3MACWITHISO7816-4PADDING", "ISO9797ALG3WITHISO7816-4PADDING");
            configurableProvider.a("AlgorithmParameters.DES", "org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters");
            configurableProvider.a("Alg.Alias.AlgorithmParameters", org.bouncycastle.a.r.b.e, "DES");
            configurableProvider.a("AlgorithmParameterGenerator.DES", f1119a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + org.bouncycastle.a.r.b.e, "DES");
            configurableProvider.a("Cipher.PBEWITHMD2ANDDES", f1119a + "$PBEWithMD2");
            configurableProvider.a("Cipher.PBEWITHMD5ANDDES", f1119a + "$PBEWithMD5");
            configurableProvider.a("Cipher.PBEWITHSHA1ANDDES", f1119a + "$PBEWithSHA1");
            configurableProvider.a("Alg.Alias.Cipher", n.t_, "PBEWITHMD2ANDDES");
            configurableProvider.a("Alg.Alias.Cipher", n.w, "PBEWITHMD5ANDDES");
            configurableProvider.a("Alg.Alias.Cipher", n.y, "PBEWITHSHA1ANDDES");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHMD2ANDDES-CBC", "PBEWITHMD2ANDDES");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHMD5ANDDES-CBC", "PBEWITHMD5ANDDES");
            configurableProvider.a("Alg.Alias.Cipher.PBEWITHSHA1ANDDES-CBC", "PBEWITHSHA1ANDDES");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD2ANDDES", f1119a + "$PBEWithMD2KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHMD5ANDDES", f1119a + "$PBEWithMD5KeyFactory");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHA1ANDDES", f1119a + "$PBEWithSHA1KeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDDES-CBC", "PBEWITHMD2ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDDES-CBC", "PBEWITHMD5ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDDES-CBC", "PBEWITHSHA1ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + n.t_, "PBEWITHMD2ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + n.w, "PBEWITHMD5ANDDES");
            configurableProvider.a("Alg.Alias.SecretKeyFactory." + n.y, "PBEWITHSHA1ANDDES");
        }
    }
}
