package org.bouncycastle.jcajce.util;

import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;

/* loaded from: classes.dex */
public class ProviderJcaJceHelper implements JcaJceHelper {

    /* renamed from: a  reason: collision with root package name */
    protected final Provider f1202a;

    public ProviderJcaJceHelper(Provider provider) {
        this.f1202a = provider;
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final Cipher a(String str) {
        return Cipher.getInstance(str, this.f1202a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final Mac b(String str) {
        return Mac.getInstance(str, this.f1202a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final AlgorithmParameters c(String str) {
        return AlgorithmParameters.getInstance(str, this.f1202a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final KeyFactory d(String str) {
        return KeyFactory.getInstance(str, this.f1202a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final SecretKeyFactory e(String str) {
        return SecretKeyFactory.getInstance(str, this.f1202a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final Signature f(String str) {
        return Signature.getInstance(str, this.f1202a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final CertificateFactory g(String str) {
        return CertificateFactory.getInstance(str, this.f1202a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public final SecureRandom h(String str) {
        return SecureRandom.getInstance(str, this.f1202a);
    }
}
