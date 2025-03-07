package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.b.e;
import org.bouncycastle.b.e.aj;
import org.bouncycastle.b.f;
import org.bouncycastle.b.f.y;
import org.bouncycastle.b.h;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.i.d;
import org.bouncycastle.b.i.s;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;

/* loaded from: classes.dex */
public final class RC6 {

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
                AlgorithmParameters a2 = a("RC6");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for RC6 parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "RC6 IV";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new aj()), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class CFB extends BaseBlockCipher {
        public CFB() {
            super(new f(new d(new aj(), 128)), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.RC6.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public final e a() {
                    return new aj();
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new org.bouncycastle.b.h.e(new org.bouncycastle.b.i.l(new aj())));
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("RC6", 256, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1150a = RC6.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.RC6", f1150a + "$ECB");
            configurableProvider.a("KeyGenerator.RC6", f1150a + "$KeyGen");
            configurableProvider.a("AlgorithmParameters.RC6", f1150a + "$AlgParams");
            b(configurableProvider, "RC6", f1150a + "$GMAC", f1150a + "$KeyGen");
            c(configurableProvider, "RC6", f1150a + "$Poly1305", f1150a + "$Poly1305KeyGen");
        }
    }

    /* loaded from: classes.dex */
    public static class OFB extends BaseBlockCipher {
        public OFB() {
            super(new f(new s(new aj(), 128)), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.b.h.l(new aj()));
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-RC6", 256, new y());
        }
    }

    private RC6() {
    }
}
