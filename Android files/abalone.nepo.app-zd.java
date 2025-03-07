package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.Provider;

/* loaded from: classes.dex */
public final class zd implements yw<MessageDigest> {
    @Override // com.google.android.gms.internal.ads.yw
    public final /* synthetic */ MessageDigest a(String str, Provider provider) {
        return provider == null ? MessageDigest.getInstance(str) : MessageDigest.getInstance(str, provider);
    }
}
