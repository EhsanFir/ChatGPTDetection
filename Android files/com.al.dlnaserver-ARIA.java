package org.bouncycastle.jcajce.provider.symmetric;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.a.p.a;
import org.bouncycastle.b.e;
import org.bouncycastle.b.e.ak;
import org.bouncycastle.b.e.d;
import org.bouncycastle.b.f;
import org.bouncycastle.b.f.y;
import org.bouncycastle.b.h;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.i.s;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseWrapCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.spec.AEADParameterSpec;

/* loaded from: classes.dex */
public final class ARIA {

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
                AlgorithmParameters a2 = a("ARIA");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for ARIA parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "ARIA IV";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC extends BaseBlockCipher {
        public CBC() {
            super(new b(new d()), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class CFB extends BaseBlockCipher {
        public CFB() {
            super(new f(new org.bouncycastle.b.i.d(new d(), 128)), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class ECB extends BaseBlockCipher {
        public ECB() {
            super(new BlockCipherProvider() { // from class: org.bouncycastle.jcajce.provider.symmetric.ARIA.ECB.1
                @Override // org.bouncycastle.jcajce.provider.symmetric.util.BlockCipherProvider
                public final e a() {
                    return new d();
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class GMAC extends BaseMac {
        public GMAC() {
            super(new org.bouncycastle.b.h.e(new org.bouncycastle.b.i.l(new d())));
        }
    }

    /* loaded from: classes.dex */
    public static class KeyFactory extends BaseSecretKeyFactory {
        public KeyFactory() {
            super("ARIA", null);
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            this(256);
        }

        public KeyGen(int i) {
            super("ARIA", i, new h());
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
        private static final String f1112a = ARIA.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.ARIA", f1112a + "$AlgParams");
            configurableProvider.a("Alg.Alias.AlgorithmParameters", a.h, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameters", a.m, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameters", a.r, "ARIA");
            configurableProvider.a("AlgorithmParameterGenerator.ARIA", f1112a + "$AlgParamGen");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.h, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.m, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.r, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.j, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.o, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.t, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.i, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.n, "ARIA");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator", a.s, "ARIA");
            configurableProvider.a("Cipher.ARIA", f1112a + "$ECB");
            configurableProvider.a("Cipher", a.g, f1112a + "$ECB");
            configurableProvider.a("Cipher", a.l, f1112a + "$ECB");
            configurableProvider.a("Cipher", a.q, f1112a + "$ECB");
            configurableProvider.a("Cipher", a.h, f1112a + "$CBC");
            configurableProvider.a("Cipher", a.m, f1112a + "$CBC");
            configurableProvider.a("Cipher", a.r, f1112a + "$CBC");
            configurableProvider.a("Cipher", a.i, f1112a + "$CFB");
            configurableProvider.a("Cipher", a.n, f1112a + "$CFB");
            configurableProvider.a("Cipher", a.s, f1112a + "$CFB");
            configurableProvider.a("Cipher", a.j, f1112a + "$OFB");
            configurableProvider.a("Cipher", a.o, f1112a + "$OFB");
            configurableProvider.a("Cipher", a.t, f1112a + "$OFB");
            configurableProvider.a("Cipher.ARIARFC3211WRAP", f1112a + "$RFC3211Wrap");
            configurableProvider.a("Cipher.ARIAWRAP", f1112a + "$Wrap");
            configurableProvider.a("Alg.Alias.Cipher", a.H, "ARIAWRAP");
            configurableProvider.a("Alg.Alias.Cipher", a.I, "ARIAWRAP");
            configurableProvider.a("Alg.Alias.Cipher", a.J, "ARIAWRAP");
            configurableProvider.a("Alg.Alias.Cipher.ARIAKW", "ARIAWRAP");
            configurableProvider.a("Cipher.ARIAWRAPPAD", f1112a + "$WrapPad");
            configurableProvider.a("Alg.Alias.Cipher", a.K, "ARIAWRAPPAD");
            configurableProvider.a("Alg.Alias.Cipher", a.L, "ARIAWRAPPAD");
            configurableProvider.a("Alg.Alias.Cipher", a.M, "ARIAWRAPPAD");
            configurableProvider.a("Alg.Alias.Cipher.ARIAKWP", "ARIAWRAPPAD");
            configurableProvider.a("KeyGenerator.ARIA", f1112a + "$KeyGen");
            configurableProvider.a("KeyGenerator", a.H, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.I, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.J, f1112a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.K, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.L, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.M, f1112a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.g, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.l, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.q, f1112a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.h, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.m, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.r, f1112a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.i, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.n, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.s, f1112a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.j, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.o, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.t, f1112a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.E, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.F, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.G, f1112a + "$KeyGen256");
            configurableProvider.a("KeyGenerator", a.B, f1112a + "$KeyGen128");
            configurableProvider.a("KeyGenerator", a.C, f1112a + "$KeyGen192");
            configurableProvider.a("KeyGenerator", a.D, f1112a + "$KeyGen256");
            configurableProvider.a("SecretKeyFactory.ARIA", f1112a + "$KeyFactory");
            configurableProvider.a("Alg.Alias.SecretKeyFactory", a.h, "ARIA");
            configurableProvider.a("Alg.Alias.SecretKeyFactory", a.m, "ARIA");
            configurableProvider.a("Alg.Alias.SecretKeyFactory", a.r, "ARIA");
            configurableProvider.a("AlgorithmParameterGenerator.ARIACCM", f1112a + "$AlgParamGenCCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + a.E, "CCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + a.F, "CCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + a.G, "CCM");
            configurableProvider.a("Alg.Alias.Cipher", a.E, "CCM");
            configurableProvider.a("Alg.Alias.Cipher", a.F, "CCM");
            configurableProvider.a("Alg.Alias.Cipher", a.G, "CCM");
            configurableProvider.a("AlgorithmParameterGenerator.ARIAGCM", f1112a + "$AlgParamGenGCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + a.B, "GCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + a.C, "GCM");
            configurableProvider.a("Alg.Alias.AlgorithmParameterGenerator." + a.D, "GCM");
            configurableProvider.a("Alg.Alias.Cipher", a.B, "GCM");
            configurableProvider.a("Alg.Alias.Cipher", a.C, "GCM");
            configurableProvider.a("Alg.Alias.Cipher", a.D, "GCM");
            b(configurableProvider, "ARIA", f1112a + "$GMAC", f1112a + "$KeyGen");
            c(configurableProvider, "ARIA", f1112a + "$Poly1305", f1112a + "$Poly1305KeyGen");
        }
    }

    /* loaded from: classes.dex */
    public static class OFB extends BaseBlockCipher {
        public OFB() {
            super(new f(new s(new d(), 128)), 128);
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305 extends BaseMac {
        public Poly1305() {
            super(new org.bouncycastle.b.h.l(new d()));
        }
    }

    /* loaded from: classes.dex */
    public static class Poly1305KeyGen extends BaseKeyGenerator {
        public Poly1305KeyGen() {
            super("Poly1305-ARIA", 256, new y());
        }
    }

    /* loaded from: classes.dex */
    public static class RFC3211Wrap extends BaseWrapCipher {
        public RFC3211Wrap() {
            super(new ak(new d()), 16);
        }
    }

    /* loaded from: classes.dex */
    public static class Wrap extends BaseWrapCipher {
        public Wrap() {
            super(new org.bouncycastle.b.e.e());
        }
    }

    /* loaded from: classes.dex */
    public static class WrapPad extends BaseWrapCipher {
        public WrapPad() {
            super(new org.bouncycastle.b.e.f());
        }
    }

    private ARIA() {
    }

    /* loaded from: classes.dex */
    public static class AlgParamsCCM extends BaseAlgorithmParameters {

        /* renamed from: a  reason: collision with root package name */
        private org.bouncycastle.a.d.a f1110a;

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected final AlgorithmParameterSpec a(Class cls) {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.a(cls)) {
                return GcmSpecUtil.a() ? GcmSpecUtil.a(this.f1110a.i()) : new AEADParameterSpec(this.f1110a.a(), this.f1110a.b() * 8);
            }
            if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.f1110a.a(), this.f1110a.b() * 8);
            }
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.f1110a.a());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() {
            return this.f1110a.k();
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                return this.f1110a.k();
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) {
            this.f1110a = org.bouncycastle.a.d.a.a(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) {
            if (!a(str)) {
                throw new IOException("unknown format specified");
            }
            this.f1110a = org.bouncycastle.a.d.a.a(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "CCM";
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (GcmSpecUtil.a(algorithmParameterSpec)) {
                this.f1110a = org.bouncycastle.a.d.a.a(GcmSpecUtil.b(algorithmParameterSpec));
            } else if (algorithmParameterSpec instanceof AEADParameterSpec) {
                this.f1110a = new org.bouncycastle.a.d.a(((AEADParameterSpec) algorithmParameterSpec).getIV(), ((AEADParameterSpec) algorithmParameterSpec).a() / 8);
            } else {
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParamsGCM extends BaseAlgorithmParameters {

        /* renamed from: a  reason: collision with root package name */
        private org.bouncycastle.a.d.b f1111a;

        @Override // org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameters
        protected final AlgorithmParameterSpec a(Class cls) {
            if (cls == AlgorithmParameterSpec.class || GcmSpecUtil.a(cls)) {
                return GcmSpecUtil.a() ? GcmSpecUtil.a(this.f1111a.i()) : new AEADParameterSpec(this.f1111a.a(), this.f1111a.b() * 8);
            }
            if (cls == AEADParameterSpec.class) {
                return new AEADParameterSpec(this.f1111a.a(), this.f1111a.b() * 8);
            }
            if (cls == IvParameterSpec.class) {
                return new IvParameterSpec(this.f1111a.a());
            }
            throw new InvalidParameterSpecException("AlgorithmParameterSpec not recognized: " + cls.getName());
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded() {
            return this.f1111a.k();
        }

        @Override // java.security.AlgorithmParametersSpi
        protected byte[] engineGetEncoded(String str) {
            if (a(str)) {
                return this.f1111a.k();
            }
            throw new IOException("unknown format specified");
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr) {
            this.f1111a = org.bouncycastle.a.d.b.a(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(byte[] bArr, String str) {
            if (!a(str)) {
                throw new IOException("unknown format specified");
            }
            this.f1111a = org.bouncycastle.a.d.b.a(bArr);
        }

        @Override // java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "GCM";
        }

        @Override // java.security.AlgorithmParametersSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec) {
            if (GcmSpecUtil.a(algorithmParameterSpec)) {
                this.f1111a = GcmSpecUtil.b(algorithmParameterSpec);
            } else if (algorithmParameterSpec instanceof AEADParameterSpec) {
                this.f1111a = new org.bouncycastle.a.d.b(((AEADParameterSpec) algorithmParameterSpec).getIV(), ((AEADParameterSpec) algorithmParameterSpec).a() / 8);
            } else {
                throw new InvalidParameterSpecException("AlgorithmParameterSpec class not recognized: " + algorithmParameterSpec.getClass().getName());
            }
        }
    }
}
