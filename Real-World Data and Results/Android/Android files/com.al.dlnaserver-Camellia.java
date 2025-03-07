package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.a.q.a;
import org.bouncycastle.b.e;
import org.bouncycastle.b.e.ak;
import org.bouncycastle.b.e.j;
import org.bouncycastle.b.e.k;
import org.bouncycastle.b.f.y;
import org.bouncycastle.b.h;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;

/* loaded from: classes.dex */
public final class Camellia {

    /* loaded from: classes.dex */
    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[16];
            if (this.c == null) {
                this.c = l.a();
            }
            this.c.nextBytes(bArr);
            try {
                AlgorithmParameters a2 = a("Camellia");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for Camellia parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "Camellia IV";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new j()), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.Camellia.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public final e a() {
                    return new j();
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new org.bouncycastle.b.h.e(new org.bouncycastle.b.i.l(new j())));
        }
    }

    /* loaded from: classes.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("Camellia", null);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("Camellia", i, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen128 extends KeyGen {
        public KeyGen128() {
            super(128);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen192 extends KeyGen {
        public KeyGen192() {
            super(192);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen256 extends KeyGen {
        public KeyGen256() {
            super(256);
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1117a = Camellia.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.CAMELLIA", f1117a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters", a.f398a, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameters", a.b, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameters", a.c, "CAMELLIA");
            configurableProvider.a("AlgorithmParameterGenerator.CAMELLIA", f1117a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.f398a, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.b, "CAMELLIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.c, "CAMELLIA");
            configurableProvider.a("Cipher.CAMELLIA", f1117a + "$ECB");
            configurableProvider.a("Cipher", a.f398a, f1117a + "$CBC");
            configurableProvider.a("Cipher", a.b, f1117a + "$CBC");
            configurableProvider.a("Cipher", a.c, f1117a + "$CBC");
            configurableProvider.a("Cipher.CAMELLIARFC3211WRAP", f1117a + "$RFC3211Wrap");
            configurableProvider.a("Cipher.CAMELLIAWRAP", f1117a + "$Wrap");
            configurableProvider.a("Alg.Alias.Cipher", a.d, "CAMELLIAWRAP");
            configurableProvider.a("Alg.Alias.Cipher", a.e, "CAMELLIAWRAP");
            configurableProvider.a("Alg.Alias.Cipher", a.f, "CAMELLIAWRAP");
            configurableProvider.a("SecretKeyFactory.CAMELLIA", f1117a + "$KeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory", a.f398a, "CAMELLIA");
            configurableProvider.a("Alg.Alias.SecretKeyFactory", a.b, "CAMELLIA");
            configurableProvider.a("Alg.Alias.SecretKeyFactory", a.c, "CAMELLIA");
            configurableProvider.a("KeyGenerator.CAMELLIA", f1117a + "$KeyGen");
            configurableProvider.a("KeyGenerator", a.d, f1117a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.e, f1117a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.f, f1117a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.f398a, f1117a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.b, f1117a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.c, f1117a + "$KeyGen256");
            b(configurableProvider, "CAMELLIA", f1117a + "$GMAC", f1117a + "$KeyGen");
            c(configurableProvider, "CAMELLIA", f1117a + "$Poly1305", f1117a + "$Poly1305KeyGen");
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.b.h.l(new j()));
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-Camellia", 256, new y());
        }
    }

    /* loaded from: classes.dex */
    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new ak(new j()), 16);
        }
    }

    /* loaded from: classes.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new k());
        }
    }

    private Camellia() {
    }
}
