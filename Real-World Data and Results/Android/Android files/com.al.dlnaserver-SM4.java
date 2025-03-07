package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.b.e;
import org.bouncycastle.b.e.at;
import org.bouncycastle.b.f.y;
import org.bouncycastle.b.h;
import org.bouncycastle.b.h.c;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;

/* loaded from: classes.dex */
public final class SM4 {

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
                AlgorithmParameters a2 = a("SM4");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for SM4 parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "SM4 IV";
        }
    }

    /* loaded from: classes.dex */
    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new c(new at()));
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.SM4.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public final e a() {
                    return new at();
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new org.bouncycastle.b.h.e(new org.bouncycastle.b.i.l(new at())));
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("SM4", 128, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1154a = SM4.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.SM4", f1154a + "$AlgParams");
            configurableProvider.a("AlgorithmParameterGenerator.SM4", f1154a + "$AlgParamGen");
            configurableProvider.a("Cipher.SM4", f1154a + "$ECB");
            configurableProvider.a("KeyGenerator.SM4", f1154a + "$KeyGen");
            a(configurableProvider, "SM4", f1154a + "$CMAC", f1154a + "$KeyGen");
            b(configurableProvider, "SM4", f1154a + "$GMAC", f1154a + "$KeyGen");
            c(configurableProvider, "SM4", f1154a + "$Poly1305", f1154a + "$Poly1305KeyGen");
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.b.h.l(new at()));
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-SM4", 256, new y());
        }
    }

    private SM4() {
    }
}
