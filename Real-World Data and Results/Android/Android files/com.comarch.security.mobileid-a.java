package com.comarch.mobileid;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f983a = {-8, 69, 18, 50, 20, -119, 113, 0, 99, 51, -57, -37, -95, -70, -70, 55, -103, 124, -59, 78, -31, 41, -6, -111, -52, 121, 42, -88, -104, 25, 19, 1};

    /* renamed from: b  reason: collision with root package name */
    private byte[] f984b;

    /* renamed from: c  reason: collision with root package name */
    private Cipher f985c;
    private Cipher d;
    private MessageDigest e;
    private IvParameterSpec f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a() {
        try {
            this.f985c = Cipher.getInstance("AES/ECB/NoPadding");
            this.d = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.e = MessageDigest.getInstance("SHA256");
            this.f = new IvParameterSpec(new byte[this.d.getBlockSize()]);
        } catch (GeneralSecurityException e) {
            c.b.a.a.a("security exception", e.getMessage(), e);
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a(byte[] bArr) {
        if (bArr.length != this.d.getBlockSize() * 2) {
            c.b.a.a.c(this, "malicious seed - wrong len");
            return null;
        }
        try {
            this.f985c.init(1, new SecretKeySpec(f983a, "AES"));
            this.d.init(2, new SecretKeySpec(this.f985c.doFinal(this.e.digest(this.f984b)), "AES"), this.f);
            return this.d.doFinal(bArr);
        } catch (BadPaddingException unused) {
            c.b.a.a.c(this, "malicious seed");
            return null;
        } catch (GeneralSecurityException e) {
            c.b.a.a.a(this, "crypto error", e);
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] b(byte[] bArr) {
        try {
            this.f985c.init(1, new SecretKeySpec(f983a, "AES"));
            this.d.init(1, new SecretKeySpec(this.f985c.doFinal(this.e.digest(this.f984b)), "AES"), this.f);
            return this.d.doFinal(bArr);
        } catch (GeneralSecurityException e) {
            c.b.a.a.a(this, "crypto error", e);
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(byte[] bArr) {
        this.f984b = bArr;
    }
}
