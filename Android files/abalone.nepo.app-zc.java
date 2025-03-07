package com.google.android.gms.internal.ads;

import java.security.Provider;
import javax.crypto.Mac;

/* loaded from: classes.dex */
public final class zc implements yw<Mac> {
    @Override // com.google.android.gms.internal.ads.yw
    public final /* synthetic */ Mac a(String str, Provider provider) {
        return provider == null ? Mac.getInstance(str) : Mac.getInstance(str, provider);
    }
}
