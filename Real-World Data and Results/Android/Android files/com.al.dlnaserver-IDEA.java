package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.a.n.b;
import org.bouncycastle.a.n.c;
import org.bouncycastle.b.e.ab;
import org.bouncycastle.b.h;
import org.bouncycastle.b.h.a;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes.dex */
public final class IDEA {

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
                AlgorithmParameters a2 = a("IDEA");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for IDEA parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends BaseAlgorithmParameters {

        /* renamed from: a  reason: collision with root package name */
        private byte[] f1134a;

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected final AlgorithmParameterSpec a(Class cls) {
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.f1134a);
            }
            throw new InvalidParameterSpecException("unknown parameter spec passed to IV parameters object.");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() {
            return engineGetEncoded("ASN.1");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                return new b(engineGetEncoded("RAW")).k();
            }
            if (!str.equals("RAW")) {
                return null;
            }
            byte[] bArr = new byte[this.f1134a.length];
            System.arraycopy(this.f1134a, 0, bArr, 0, this.f1134a.length);
            return bArr;
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (!(algorithmParameterSpec instanceof IvParameterSpec)) {
                throw new InvalidParameterSpecException("IvParameterSpec required to initialise a IV parameters algorithm parameters object");
            }
            this.f1134a = ((IvParameterSpec) algorithmParameterSpec).getIV();
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) {
            this.f1134a = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.f1134a, 0, this.f1134a.length);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) {
            if (str.equals("RAW")) {
                engineInit(bArr);
            } else if (str.equals("ASN.1")) {
                engineInit(b.a(bArr).a());
            } else {
                throw new IOException("Unknown parameters format in IV parameters object");
            }
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "IDEA Parameters";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new org.bouncycastle.b.i.b(new ab()), 64);
        }
    }

    /* loaded from: classes.dex */
    public static class CFB8Mac extends BaseMac {
        public CFB8Mac() {
            super(new org.bouncycastle.b.h.b(new ab()));
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new ab());
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("IDEA", 128, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class Mac extends BaseMac {
        public Mac() {
            super(new a(new ab()));
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends AlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1135a = IDEA.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameterGenerator.IDEA", f1135a + "$AlgParamGen");
            configurableProvider.a("AlgorithmParameterGenerator.1.3.6.1.4.1.188.7.1.1.2", f1135a + "$AlgParamGen");
            configurableProvider.a("AlgorithmParameters.IDEA", f1135a + "$AlgParams");
            configurableProvider.a("AlgorithmParameters.1.3.6.1.4.1.188.7.1.1.2", f1135a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA", "PKCS12PBE");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDIDEA-CBC", "PKCS12PBE");
            configurableProvider.a("Cipher.IDEA", f1135a + "$ECB");
            configurableProvider.a("Cipher", c.v, f1135a + "$CBC");
            configurableProvider.a("Cipher.PBEWITHSHAANDIDEA-CBC", f1135a + "$PBEWithSHAAndIDEA");
            configurableProvider.a("KeyGenerator.IDEA", f1135a + "$KeyGen");
            configurableProvider.a("KeyGenerator", c.v, f1135a + "$KeyGen");
            configurableProvider.a("SecretKeyFactory.PBEWITHSHAANDIDEA-CBC", f1135a + "$PBEWithSHAAndIDEAKeyGen");
            configurableProvider.a("Mac.IDEAMAC", f1135a + "$Mac");
            configurableProvider.a("Alg.Alias.Mac.IDEA", "IDEAMAC");
            configurableProvider.a("Mac.IDEAMAC/CFB8", f1135a + "$CFB8Mac");
            configurableProvider.a("Alg.Alias.Mac.IDEA/CFB8", "IDEAMAC/CFB8");
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHAAndIDEA extends BaseBlockCipher {
        public PBEWithSHAAndIDEA() {
            super(new org.bouncycastle.b.i.b(new ab()));
        }
    }

    /* loaded from: classes.dex */
    public static class PBEWithSHAAndIDEAKeyGen extends PBESecretKeyFactory {
        public PBEWithSHAAndIDEAKeyGen() {
            super("PBEwithSHAandIDEA-CBC", null, true, 2, 1, 128, 64);
        }
    }

    private IDEA() {
    }
}
