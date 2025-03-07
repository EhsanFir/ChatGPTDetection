package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.a.m.a;
import org.bouncycastle.b.e;
import org.bouncycastle.b.e.aq;
import org.bouncycastle.b.e.ar;
import org.bouncycastle.b.f.y;
import org.bouncycastle.b.h;
import org.bouncycastle.b.h.c;
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
public final class SEED {

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
                AlgorithmParameters a2 = a("SEED");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for SEED parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "SEED IV";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new aq()), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new c(new aq()));
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.SEED.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public final e a() {
                    return new aq();
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new org.bouncycastle.b.h.e(new org.bouncycastle.b.i.l(new aq())));
        }
    }

    /* loaded from: classes.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("SEED", null);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("SEED", 128, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1153a = SEED.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.SEED", f1153a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters." + a.f387a, "SEED");
            configurableProvider.a("AlgorithmParameterGenerator.SEED", f1153a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + a.f387a, "SEED");
            configurableProvider.a("Cipher.SEED", f1153a + "$ECB");
            configurableProvider.a("Cipher", a.f387a, f1153a + "$CBC");
            configurableProvider.a("Cipher.SEEDWRAP", f1153a + "$Wrap");
            configurableProvider.a("Alg.Alias.Cipher", a.d, "SEEDWRAP");
            configurableProvider.a("Alg.Alias.Cipher.SEEDKW", "SEEDWRAP");
            configurableProvider.a("KeyGenerator.SEED", f1153a + "$KeyGen");
            configurableProvider.a("KeyGenerator", a.f387a, f1153a + "$KeyGen");
            configurableProvider.a("KeyGenerator", a.d, f1153a + "$KeyGen");
            configurableProvider.a("SecretKeyFactory.SEED", f1153a + "$KeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory", a.f387a, "SEED");
            a(configurableProvider, "SEED", f1153a + "$CMAC", f1153a + "$KeyGen");
            b(configurableProvider, "SEED", f1153a + "$GMAC", f1153a + "$KeyGen");
            c(configurableProvider, "SEED", f1153a + "$Poly1305", f1153a + "$Poly1305KeyGen");
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.b.h.l(new aq()));
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-SEED", 256, new y());
        }
    }

    /* loaded from: classes.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new ar());
        }
    }

    private SEED() {
    }
}
