package com.google.android.gms.internal.ads;

import java.security.KeyFactory;
import java.security.Provider;

/* loaded from: classes.dex */
public final class za implements yw<KeyFactory> {
    @Override // com.google.android.gms.internal.ads.yw
    public final /* synthetic */ KeyFactory a(String str, Provider provider) {
        return provider == null ? KeyFactory.getInstance(str) : KeyFactory.getInstance(str, provider);
    }
}
