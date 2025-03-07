package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.a.n.a;
import org.bouncycastle.a.n.c;
import org.bouncycastle.b.e.h;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes.dex */
public final class CAST5 {

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
                AlgorithmParameters a2 = a("CAST5");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for CAST5 parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends BaseAlgorithmParameters {

        /* renamed from: a  reason: collision with root package name */
        private byte[] f1114a;
        private int b = 128;

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected final AlgorithmParameterSpec a(Class cls) {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.f1114a);
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to CAST5 parameters object.");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() {
            byte[] bArr = new byte[this.f1114a.length];
            System.arraycopy(this.f1114a, 0, bArr, 0, this.f1114a.length);
            return bArr;
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                return new a(engineGetEncoded(), this.b).k();
            }
            if (str.equals("RAW")) {
                return engineGetEncoded();
            }
            return null;
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (algorithmParameterSpec instanceof IvParameterSpec) {
                this.f1114a = ((IvParameterSpec) algorithmParameterSpec).getIV();
                return;
            }
            throw new InvalidParameterSpecException("IvParameterSpec required to initialise a CAST5 parameters algorithm parameters object");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) {
            this.f1114a = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.f1114a, 0, this.f1114a.length);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) {
            if (a(str)) {
                a a2 = a.a(new org.bouncycastle.a.l(bArr).b());
                this.b = a2.b();
                this.f1114a = a2.a();
            } else if (str.equals("RAW")) {
                engineInit(bArr);
            } else {
                throw new IOException("Unknown parameters format in IV parameters object");
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "CAST5 Parameters";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new h()), 64);
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new h());
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("CAST5", 128, new org.bouncycastle.b.h());
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends AlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1115a = CAST5.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.CAST5", f1115a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.1.2.840.113533.7.66.10", "CAST5");
            configurableProvider.a("AlgorithmParameterGenerator.CAST5", f1115a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator.1.2.840.113533.7.66.10", "CAST5");
            configurableProvider.a("Cipher.CAST5", f1115a + "$ECB");
            configurableProvider.a("Cipher", c.u, f1115a + "$CBC");
            configurableProvider.a("KeyGenerator.CAST5", f1115a + "$KeyGen");
            configurableProvider.a("Alg.Alias.KeyGenerator", c.u, "CAST5");
        }
    }

    private CAST5() {
    }
}
