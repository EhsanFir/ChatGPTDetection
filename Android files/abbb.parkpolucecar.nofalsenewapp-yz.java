package com.google.android.gms.internal.ads;

import java.security.Provider;
import javax.crypto.KeyAgreement;

/* loaded from: classes.dex */
public final class yz implements yw<KeyAgreement> {
    @Override // com.google.android.gms.internal.ads.yw
    public final /* synthetic */ KeyAgreement a(String str, Provider provider) {
        return provider == null ? KeyAgreement.getInstance(str) : KeyAgreement.getInstance(str, provider);
    }
}
