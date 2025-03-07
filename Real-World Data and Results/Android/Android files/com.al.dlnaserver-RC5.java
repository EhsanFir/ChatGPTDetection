package org.bouncycastle.jcajce.provider.symmetric;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.b.e.ah;
import org.bouncycastle.b.e.ai;
import org.bouncycastle.b.h;
import org.bouncycastle.b.h.a;
import org.bouncycastle.b.i.b;
import org.bouncycastle.b.l;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes.dex */
public final class RC5 {

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
                AlgorithmParameters a2 = a("RC5");
                a2.init(new IvParameterSpec(bArr));
                return a2;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override // java.security.AlgorithmParameterGeneratorSpi
        protected void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for RC5 parameter generation.");
        }
    }

    /* loaded from: classes.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "RC5 IV";
        }
    }

    /* loaded from: classes.dex */
    public static class CBC32 extends BaseBlockCipher {
        public CBC32() {
            super(new b(new ah()), 64);
        }
    }

    /* loaded from: classes.dex */
    public static class CFB8Mac32 extends BaseMac {
        public CFB8Mac32() {
            super(new org.bouncycastle.b.h.b(new ah()));
        }
    }

    /* loaded from: classes.dex */
    public static class ECB32 extends BaseBlockCipher {
        public ECB32() {
            super(new ah());
        }
    }

    /* loaded from: classes.dex */
    public static class ECB64 extends BaseBlockCipher {
        public ECB64() {
            super(new ai());
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen32 extends BaseKeyGenerator {
        public KeyGen32() {
            super("RC5", 128, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class KeyGen64 extends BaseKeyGenerator {
        public KeyGen64() {
            super("RC5-64", 256, new h());
        }
    }

    /* loaded from: classes.dex */
    public static class Mac32 extends BaseMac {
        public Mac32() {
            super(new a(new ah()));
        }
    }

    /* loaded from: classes.dex */
    public static class Mappings extends AlgorithmProvider {

        /* renamed from: a  reason: collision with root package name */
        private static final String f1149a = RC5.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public final void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("Cipher.RC5", f1149a + "$ECB32");
            configurableProvider.a("Alg.Alias.Cipher.RC5-32", "RC5");
            configurableProvider.a("Cipher.RC5-64", f1149a + "$ECB64");
            configurableProvider.a("KeyGenerator.RC5", f1149a + "$KeyGen32");
            configurableProvider.a("Alg.Alias.KeyGenerator.RC5-32", "RC5");
            configurableProvider.a("KeyGenerator.RC5-64", f1149a + "$KeyGen64");
            configurableProvider.a("AlgorithmParameters.RC5", f1149a + "$AlgParams");
            configurableProvider.a("AlgorithmParameters.RC5-64", f1149a + "$AlgParams");
            configurableProvider.a("Mac.RC5MAC", f1149a + "$Mac32");
            configurableProvider.a("Alg.Alias.Mac.RC5", "RC5MAC");
            configurableProvider.a("Mac.RC5MAC/CFB8", f1149a + "$CFB8Mac32");
            configurableProvider.a("Alg.Alias.Mac.RC5/CFB8", "RC5MAC/CFB8");
        }
    }

    private RC5() {
    }
}
