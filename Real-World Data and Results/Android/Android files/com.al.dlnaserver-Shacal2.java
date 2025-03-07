package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.b.e;
import org.bouncycastle.b.e.ax;
import org.bouncycastle.b.h;
import org.bouncycastle.b.h.c;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;

/* loaded from: classes.dex */
public final class Shacal2 {

    /* loaded from: classes.dex */
    public static class AlgParamGen extends BaseAlgorithmParameterGenerator {
        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected AlgorithmParameters engineGenerateParameters() {
            byte[] bArr = new byte[32];
            if (this.c == null) {
                this.c = l.a();
            }
            this.c.nextBytes(bArr);
            try {
                AlgorithmParameters a2 = a("Shacal2");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for Shacal2 parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "Shacal2 IV";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new ax()), 256);
        }
    }

    /* loaded from: classes.dex */
    public static class CMAC extends BaseMac {
        public CMAC() {
            super(new c(new ax()));
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.Shacal2.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public final e a() {
                    return new ax();
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("SHACAL-2", 128, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends SymmetricAlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1157a = Shacal2.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Mac.Shacal-2CMAC", f1157a + "$CMAC");
            configurableProvider.a("Cipher.Shacal2", f1157a + "$ECB");
            configurableProvider.a("Cipher.SHACAL-2", f1157a + "$ECB");
            configurableProvider.a("KeyGenerator.Shacal2", f1157a + "$KeyGen");
            configurableProvider.a("AlgorithmParameterGenerator.Shacal2", f1157a + "$AlgParamGen");
            configurableProvider.a("AlgorithmParameters.Shacal2", f1157a + "$AlgParams");
            configurableProvider.a("KeyGenerator.SHACAL-2", f1157a + "$KeyGen");
            configurableProvider.a("AlgorithmParameterGenerator.SHACAL-2", f1157a + "$AlgParamGen");
            configurableProvider.a("AlgorithmParameters.SHACAL-2", f1157a + "$AlgParams");
        }
    }

    private Shacal2() {
    }
}
