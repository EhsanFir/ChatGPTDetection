package com.kimscom.snaptime;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class ae {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f84a;
    private final Cipher b;
    private final Cipher c;
    private final Cipher d;
    private final SharedPreferences e;

    public ae(Context context, String str, String str2, boolean z) {
        try {
            this.b = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.d = Cipher.getInstance("AES/ECB/PKCS5Padding");
            a(str2);
            this.e = context.getSharedPreferences(str, 0);
            this.f84a = z;
        } catch (UnsupportedEncodingException e) {
            throw new af(e);
        } catch (GeneralSecurityException e2) {
            throw new af(e2);
        }
    }

    private static byte[] a(Cipher cipher, byte[] bArr) {
        try {
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            throw new af(e);
        }
    }

    private void b(String str, String str2) {
        this.e.edit().putString(str, a(str2, this.b)).commit();
    }

    private String f(String str) {
        return this.f84a ? a(str, this.d) : str;
    }

    protected String a(String str, Cipher cipher) {
        try {
            return Base64.encodeToString(a(cipher, str.getBytes("UTF-8")), 2);
        } catch (UnsupportedEncodingException e) {
            throw new af(e);
        }
    }

    protected IvParameterSpec a() {
        byte[] bArr = new byte[this.b.getBlockSize()];
        System.arraycopy("fldsjfodasjifudslfjdsaofshaufihadsf".getBytes(), 0, bArr, 0, this.b.getBlockSize());
        return new IvParameterSpec(bArr);
    }

    protected void a(String str) {
        IvParameterSpec a2 = a();
        SecretKeySpec b = b(str);
        this.b.init(1, b, a2);
        this.c.init(2, b, a2);
        this.d.init(1, b);
    }

    public void a(String str, String str2) {
        if (str2 == null) {
            this.e.edit().remove(f(str)).commit();
        } else {
            b(f(str), str2);
        }
    }

    protected SecretKeySpec b(String str) {
        return new SecretKeySpec(c(str), "AES/CBC/PKCS5Padding");
    }

    protected byte[] c(String str) {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.reset();
        return instance.digest(str.getBytes("UTF-8"));
    }

    public String d(String str) {
        if (this.e.contains(f(str))) {
            return e(this.e.getString(f(str), ""));
        }
        return null;
    }

    protected String e(String str) {
        try {
            return new String(a(this.c, Base64.decode(str, 2)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new af(e);
        }
    }
}
