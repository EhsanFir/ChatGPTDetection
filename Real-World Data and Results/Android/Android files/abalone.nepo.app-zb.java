package com.google.android.gms.internal.ads;

import java.security.KeyPairGenerator;
import java.security.Provider;

/* loaded from: classes.dex */
public final class zb implements yw<KeyPairGenerator> {
    @Override // com.google.android.gms.internal.ads.yw
    public final /* synthetic */ KeyPairGenerator a(String str, Provider provider) {
        return provider == null ? KeyPairGenerator.getInstance(str) : KeyPairGenerator.getInstance(str, provider);
    }
}
