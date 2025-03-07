package com.google.android.gms.internal.ads;

import java.security.Provider;
import java.security.Signature;

/* loaded from: classes.dex */
public final class ze implements yw<Signature> {
    @Override // com.google.android.gms.internal.ads.yw
    public final /* synthetic */ Signature a(String str, Provider provider) {
        return provider == null ? Signature.getInstance(str) : Signature.getInstance(str, provider);
    }
}
