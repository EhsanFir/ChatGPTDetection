package com.google.android.gms.internal.ads;

import java.security.Provider;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public final class yy implements yw<Cipher> {
    @Override // com.google.android.gms.internal.ads.yw
    public final /* synthetic */ Cipher a(String str, Provider provider) {
        return provider == null ? Cipher.getInstance(str) : Cipher.getInstance(str, provider);
    }
}
