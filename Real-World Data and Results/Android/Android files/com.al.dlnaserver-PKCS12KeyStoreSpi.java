package org.bouncycastle.jcajce.provider.keystore.pkcs12;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.a.ah;
import org.bouncycastle.a.ak;
import org.bouncycastle.a.at;
import org.bouncycastle.a.bc;
import org.bouncycastle.a.be;
import org.bouncycastle.a.bg;
import org.bouncycastle.a.bi;
import org.bouncycastle.a.bk;
import org.bouncycastle.a.f;
import org.bouncycastle.a.f.c;
import org.bouncycastle.a.g;
import org.bouncycastle.a.p;
import org.bouncycastle.a.q;
import org.bouncycastle.a.r.b;
import org.bouncycastle.a.s.e;
import org.bouncycastle.a.s.i;
import org.bouncycastle.a.s.m;
import org.bouncycastle.a.s.n;
import org.bouncycastle.a.s.o;
import org.bouncycastle.a.s.v;
import org.bouncycastle.a.u;
import org.bouncycastle.a.x;
import org.bouncycastle.a.z.a;
import org.bouncycastle.a.z.aa;
import org.bouncycastle.a.z.j;
import org.bouncycastle.a.z.t;
import org.bouncycastle.b.b.s;
import org.bouncycastle.b.l;
import org.bouncycastle.c.b.d;
import org.bouncycastle.f.h;
import org.bouncycastle.f.k;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.PKCS12StoreParameter;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;

/* loaded from: classes.dex */
public class PKCS12KeyStoreSpi extends KeyStoreSpi implements n, aa {
    private static final DefaultSecretKeyProvider bQ = new DefaultSecretKeyProvider();
    private CertificateFactory bW;
    private p bX;
    private p bY;
    private final JcaJceHelper bP = new BCJcaJceHelper();
    private IgnoresCaseHashtable bR = new IgnoresCaseHashtable((byte) 0);
    private Hashtable bS = new Hashtable();
    private IgnoresCaseHashtable bT = new IgnoresCaseHashtable((byte) 0);
    private Hashtable bU = new Hashtable();
    private Hashtable bV = new Hashtable();
    protected SecureRandom bO = l.a();
    private a bZ = new a(b.i, bc.f343a);
    private int ca = 102400;
    private int cb = 20;

    /* loaded from: classes.dex */
    public static class BCPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new BCJcaJceHelper(), bF, bI);
        }
    }

    /* loaded from: classes.dex */
    public static class BCPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public BCPKCS12KeyStore3DES() {
            /*
                r2 = this;
                org.bouncycastle.jcajce.util.BCJcaJceHelper r0 = new org.bouncycastle.jcajce.util.BCJcaJceHelper
                r0.<init>()
                org.bouncycastle.a.p r1 = org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.BCPKCS12KeyStore3DES.bF
                r2.<init>(r0, r1, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.BCPKCS12KeyStore3DES.<init>():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class CertId {

        /* renamed from: a  reason: collision with root package name */
        byte[] f1101a;

        CertId(PublicKey publicKey) {
            this.f1101a = PKCS12KeyStoreSpi.b(publicKey).a();
        }

        CertId(byte[] bArr) {
            this.f1101a = bArr;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CertId)) {
                return false;
            }
            return org.bouncycastle.f.a.a(this.f1101a, ((CertId) obj).f1101a);
        }

        public int hashCode() {
            return org.bouncycastle.f.a.a(this.f1101a);
        }
    }

    /* loaded from: classes.dex */
    public static class DefPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore() {
            super(new DefaultJcaJceHelper(), bF, bI);
        }
    }

    /* loaded from: classes.dex */
    public static class DefPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public DefPKCS12KeyStore3DES() {
            /*
                r2 = this;
                org.bouncycastle.jcajce.util.DefaultJcaJceHelper r0 = new org.bouncycastle.jcajce.util.DefaultJcaJceHelper
                r0.<init>()
                org.bouncycastle.a.p r1 = org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.DefPKCS12KeyStore3DES.bF
                r2.<init>(r0, r1, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.DefPKCS12KeyStore3DES.<init>():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class IgnoresCaseHashtable {

        /* renamed from: a  reason: collision with root package name */
        private Hashtable f1103a;
        private Hashtable b;

        private IgnoresCaseHashtable() {
            this.f1103a = new Hashtable();
            this.b = new Hashtable();
        }

        /* synthetic */ IgnoresCaseHashtable(byte b) {
            this();
        }

        public final Object a(String str) {
            String str2 = (String) this.b.remove(str == null ? null : k.c(str));
            if (str2 == null) {
                return null;
            }
            return this.f1103a.remove(str2);
        }

        public final Enumeration a() {
            return this.f1103a.keys();
        }

        public final void a(String str, Object obj) {
            String c = str == null ? null : k.c(str);
            String str2 = (String) this.b.get(c);
            if (str2 != null) {
                this.f1103a.remove(str2);
            }
            this.b.put(c, str);
            this.f1103a.put(str, obj);
        }

        public final Object b(String str) {
            String str2 = (String) this.b.get(str == null ? null : k.c(str));
            if (str2 == null) {
                return null;
            }
            return this.f1103a.get(str2);
        }

        public final Enumeration b() {
            return this.f1103a.elements();
        }
    }

    public PKCS12KeyStoreSpi(JcaJceHelper jcaJceHelper, p pVar, p pVar2) {
        this.bX = pVar;
        this.bY = pVar2;
        try {
            this.bW = jcaJceHelper.g("X.509");
        } catch (Exception e) {
            throw new IllegalArgumentException("can't create cert factory - " + e.toString());
        }
    }

    private static int a(BigInteger bigInteger) {
        int intValue = bigInteger.intValue();
        if (intValue < 0) {
            throw new IllegalStateException("negative iteration count found");
        }
        BigInteger b = h.b("org.bouncycastle.pkcs12.max_it_count");
        if (b == null || b.intValue() >= intValue) {
            return intValue;
        }
        throw new IllegalStateException("iteration count " + intValue + " greater than " + b.intValue());
    }

    private PrivateKey a(a aVar, byte[] bArr, char[] cArr, boolean z) {
        p a2 = aVar.a();
        try {
            if (a2.a(n.bC)) {
                m a3 = m.a(aVar.b());
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(a3.b(), a(a3.a()));
                Cipher a4 = this.bP.a(a2.b());
                a4.init(4, new PKCS12Key(cArr, z), pBEParameterSpec);
                return (PrivateKey) a4.unwrap(bArr, "", 2);
            } else if (a2.equals(n.A)) {
                return (PrivateKey) a(4, cArr, aVar).unwrap(bArr, "", 2);
            } else {
                throw new IOException("exception unwrapping private key - cannot recognise: ".concat(String.valueOf(a2)));
            }
        } catch (Exception e) {
            throw new IOException("exception unwrapping private key - " + e.toString());
        }
    }

    private Set a() {
        HashSet hashSet = new HashSet();
        Enumeration a2 = this.bR.a();
        while (a2.hasMoreElements()) {
            Certificate[] engineGetCertificateChain = engineGetCertificateChain((String) a2.nextElement());
            for (int i = 0; i != engineGetCertificateChain.length; i++) {
                hashSet.add(engineGetCertificateChain[i]);
            }
        }
        Enumeration a3 = this.bT.a();
        while (a3.hasMoreElements()) {
            hashSet.add(engineGetCertificate((String) a3.nextElement()));
        }
        return hashSet;
    }

    private Cipher a(int i, char[] cArr, a aVar) {
        org.bouncycastle.a.s.k a2 = org.bouncycastle.a.s.k.a(aVar.b());
        org.bouncycastle.a.s.l a3 = org.bouncycastle.a.s.l.a(a2.a().b());
        a a4 = a.a(a2.b());
        SecretKeyFactory e = this.bP.e(a2.a().a().b());
        SecretKey generateSecret = a3.d() ? e.generateSecret(new PBEKeySpec(cArr, a3.a(), a(a3.b()), bQ.a(a4))) : e.generateSecret(new PBKDF2KeySpec(cArr, a3.a(), a(a3.b()), bQ.a(a4), a3.e()));
        Cipher instance = Cipher.getInstance(a2.b().a().b());
        f b = a2.b().b();
        if (b instanceof q) {
            instance.init(i, generateSecret, new IvParameterSpec(q.a(b).c()));
        } else {
            c a5 = c.a(b);
            instance.init(i, generateSecret, new GOST28147ParameterSpec(a5.a(), a5.b()));
        }
        return instance;
    }

    private void a(OutputStream outputStream, char[] cArr, boolean z) {
        boolean z2;
        boolean z3;
        if (cArr == null) {
            throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
        }
        g gVar = new g();
        Enumeration a2 = this.bR.a();
        while (a2.hasMoreElements()) {
            byte[] bArr = new byte[20];
            this.bO.nextBytes(bArr);
            String str = (String) a2.nextElement();
            PrivateKey privateKey = (PrivateKey) this.bR.b(str);
            m mVar = new m(bArr, 51200);
            org.bouncycastle.a.s.f fVar = new org.bouncycastle.a.s.f(new a(this.bX, mVar.i()), a(this.bX.b(), privateKey, mVar, cArr));
            g gVar2 = new g();
            if (privateKey instanceof org.bouncycastle.c.a.n) {
                org.bouncycastle.c.a.n nVar = (org.bouncycastle.c.a.n) privateKey;
                at atVar = (at) nVar.a(ak);
                if (atVar == null || !atVar.b().equals(str)) {
                    nVar.a(ak, new at(str));
                }
                if (nVar.a(al) == null) {
                    nVar.a(al, b(engineGetCertificate(str).getPublicKey()));
                }
                Enumeration b = nVar.b();
                z3 = false;
                while (b.hasMoreElements()) {
                    p pVar = (p) b.nextElement();
                    g gVar3 = new g();
                    gVar3.a(pVar);
                    gVar3.a(new bk(nVar.a(pVar)));
                    z3 = true;
                    gVar2.a(new bi(gVar3));
                }
            } else {
                z3 = false;
            }
            if (!z3) {
                g gVar4 = new g();
                Certificate engineGetCertificate = engineGetCertificate(str);
                gVar4.a(al);
                gVar4.a(new bk(b(engineGetCertificate.getPublicKey())));
                gVar2.a(new bi(gVar4));
                g gVar5 = new g();
                gVar5.a(ak);
                gVar5.a(new bk(new at(str)));
                gVar2.a(new bi(gVar5));
            }
            gVar.a(new v(bx, fVar.i(), new bk(gVar2)));
        }
        ah ahVar = new ah(new bi(gVar).a("DER"));
        byte[] bArr2 = new byte[20];
        this.bO.nextBytes(bArr2);
        g gVar6 = new g();
        a aVar = new a(this.bY, new m(bArr2, 51200).i());
        Hashtable hashtable = new Hashtable();
        Enumeration a3 = this.bR.a();
        while (a3.hasMoreElements()) {
            try {
                String str2 = (String) a3.nextElement();
                Certificate engineGetCertificate2 = engineGetCertificate(str2);
                org.bouncycastle.a.s.b bVar = new org.bouncycastle.a.s.b(ao, new be(engineGetCertificate2.getEncoded()));
                g gVar7 = new g();
                if (engineGetCertificate2 instanceof org.bouncycastle.c.a.n) {
                    org.bouncycastle.c.a.n nVar2 = (org.bouncycastle.c.a.n) engineGetCertificate2;
                    at atVar2 = (at) nVar2.a(ak);
                    if (atVar2 == null || !atVar2.b().equals(str2)) {
                        nVar2.a(ak, new at(str2));
                    }
                    if (nVar2.a(al) == null) {
                        nVar2.a(al, b(engineGetCertificate2.getPublicKey()));
                    }
                    Enumeration b2 = nVar2.b();
                    z2 = false;
                    while (b2.hasMoreElements()) {
                        p pVar2 = (p) b2.nextElement();
                        g gVar8 = new g();
                        gVar8.a(pVar2);
                        gVar8.a(new bk(nVar2.a(pVar2)));
                        gVar7.a(new bi(gVar8));
                        z2 = true;
                    }
                } else {
                    z2 = false;
                }
                if (!z2) {
                    g gVar9 = new g();
                    gVar9.a(al);
                    gVar9.a(new bk(b(engineGetCertificate2.getPublicKey())));
                    gVar7.a(new bi(gVar9));
                    g gVar10 = new g();
                    gVar10.a(ak);
                    gVar10.a(new bk(new at(str2)));
                    gVar7.a(new bi(gVar10));
                }
                gVar6.a(new v(by, bVar.i(), new bk(gVar7)));
                hashtable.put(engineGetCertificate2, engineGetCertificate2);
            } catch (CertificateEncodingException e) {
                throw new IOException("Error encoding certificate: " + e.toString());
            }
        }
        Enumeration a4 = this.bT.a();
        while (a4.hasMoreElements()) {
            try {
                String str3 = (String) a4.nextElement();
                Certificate certificate = (Certificate) this.bT.b(str3);
                boolean z4 = false;
                if (this.bR.b(str3) == null) {
                    org.bouncycastle.a.s.b bVar2 = new org.bouncycastle.a.s.b(ao, new be(certificate.getEncoded()));
                    g gVar11 = new g();
                    if (certificate instanceof org.bouncycastle.c.a.n) {
                        org.bouncycastle.c.a.n nVar3 = (org.bouncycastle.c.a.n) certificate;
                        at atVar3 = (at) nVar3.a(ak);
                        if (atVar3 == null || !atVar3.b().equals(str3)) {
                            nVar3.a(ak, new at(str3));
                        }
                        Enumeration b3 = nVar3.b();
                        while (b3.hasMoreElements()) {
                            p pVar3 = (p) b3.nextElement();
                            if (!pVar3.equals(n.al)) {
                                g gVar12 = new g();
                                gVar12.a(pVar3);
                                gVar12.a(new bk(nVar3.a(pVar3)));
                                gVar11.a(new bi(gVar12));
                                z4 = true;
                            }
                        }
                    }
                    if (!z4) {
                        g gVar13 = new g();
                        gVar13.a(ak);
                        gVar13.a(new bk(new at(str3)));
                        gVar11.a(new bi(gVar13));
                    }
                    gVar6.a(new v(by, bVar2.i(), new bk(gVar11)));
                    hashtable.put(certificate, certificate);
                }
            } catch (CertificateEncodingException e2) {
                throw new IOException("Error encoding certificate: " + e2.toString());
            }
        }
        Set a5 = a();
        Enumeration keys = this.bU.keys();
        while (keys.hasMoreElements()) {
            try {
                Certificate certificate2 = (Certificate) this.bU.get((CertId) keys.nextElement());
                if (a5.contains(certificate2) && hashtable.get(certificate2) == null) {
                    org.bouncycastle.a.s.b bVar3 = new org.bouncycastle.a.s.b(ao, new be(certificate2.getEncoded()));
                    g gVar14 = new g();
                    if (certificate2 instanceof org.bouncycastle.c.a.n) {
                        org.bouncycastle.c.a.n nVar4 = (org.bouncycastle.c.a.n) certificate2;
                        Enumeration b4 = nVar4.b();
                        while (b4.hasMoreElements()) {
                            p pVar4 = (p) b4.nextElement();
                            if (!pVar4.equals(n.al)) {
                                g gVar15 = new g();
                                gVar15.a(pVar4);
                                gVar15.a(new bk(nVar4.a(pVar4)));
                                gVar14.a(new bi(gVar15));
                            }
                        }
                    }
                    gVar6.a(new v(by, bVar3.i(), new bk(gVar14)));
                }
            } catch (CertificateEncodingException e3) {
                throw new IOException("Error encoding certificate: " + e3.toString());
            }
        }
        org.bouncycastle.a.s.a aVar2 = new org.bouncycastle.a.s.a(new org.bouncycastle.a.s.c[]{new org.bouncycastle.a.s.c(Q, ahVar), new org.bouncycastle.a.s.c(V, new e(Q, aVar, new ah(a(true, aVar, cArr, false, new bi(gVar6).a("DER")))).i())});
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        (z ? new bg(byteArrayOutputStream) : new ak(byteArrayOutputStream)).a(aVar2);
        org.bouncycastle.a.s.c cVar = new org.bouncycastle.a.s.c(Q, new ah(byteArrayOutputStream.toByteArray()));
        byte[] bArr3 = new byte[this.cb];
        this.bO.nextBytes(bArr3);
        try {
            (z ? new bg(outputStream) : new ak(outputStream)).a(new o(cVar, new i(new j(this.bZ, a(this.bZ.a(), bArr3, this.ca, cArr, false, ((q) cVar.b()).c())), bArr3, this.ca)));
        } catch (Exception e4) {
            throw new IOException("error constructing MAC: " + e4.toString());
        }
    }

    private byte[] a(String str, Key key, m mVar, char[] cArr) {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
        try {
            SecretKeyFactory e = this.bP.e(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(mVar.b(), mVar.a().intValue());
            Cipher a2 = this.bP.a(str);
            a2.init(3, e.generateSecret(pBEKeySpec), pBEParameterSpec);
            return a2.wrap(key);
        } catch (Exception e2) {
            throw new IOException("exception encrypting data - " + e2.toString());
        }
    }

    private byte[] a(p pVar, byte[] bArr, int i, char[] cArr, boolean z, byte[] bArr2) {
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i);
        Mac b = this.bP.b(pVar.b());
        b.init(new PKCS12Key(cArr, z), pBEParameterSpec);
        b.update(bArr2);
        return b.doFinal();
    }

    private byte[] a(boolean z, a aVar, char[] cArr, boolean z2, byte[] bArr) {
        p a2 = aVar.a();
        int i = z ? 1 : 2;
        if (a2.a(n.bC)) {
            m a3 = m.a(aVar.b());
            try {
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(a3.b(), a3.a().intValue());
                PKCS12Key pKCS12Key = new PKCS12Key(cArr, z2);
                Cipher a4 = this.bP.a(a2.b());
                a4.init(i, pKCS12Key, pBEParameterSpec);
                return a4.doFinal(bArr);
            } catch (Exception e) {
                throw new IOException("exception decrypting data - " + e.toString());
            }
        } else if (a2.equals(n.A)) {
            try {
                return a(i, cArr, aVar).doFinal(bArr);
            } catch (Exception e2) {
                throw new IOException("exception decrypting data - " + e2.toString());
            }
        } else {
            throw new IOException("unknown PBE algorithm: ".concat(String.valueOf(a2)));
        }
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration engineAliases() {
        Hashtable hashtable = new Hashtable();
        Enumeration a2 = this.bT.a();
        while (a2.hasMoreElements()) {
            hashtable.put(a2.nextElement(), "cert");
        }
        Enumeration a3 = this.bR.a();
        while (a3.hasMoreElements()) {
            String str = (String) a3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, "key");
            }
        }
        return hashtable.keys();
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineContainsAlias(String str) {
        return (this.bT.b(str) == null && this.bR.b(str) == null) ? false : true;
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String str) {
        Key key = (Key) this.bR.a(str);
        Certificate certificate = (Certificate) this.bT.a(str);
        if (certificate != null) {
            this.bU.remove(new CertId(certificate.getPublicKey()));
        }
        if (key != null) {
            String str2 = (String) this.bS.remove(str);
            Certificate certificate2 = str2 != null ? (Certificate) this.bV.remove(str2) : certificate;
            if (certificate2 != null) {
                this.bU.remove(new CertId(certificate2.getPublicKey()));
            }
        }
    }

    @Override // java.security.KeyStoreSpi
    public Certificate engineGetCertificate(String str) {
        if (str == null) {
            throw new IllegalArgumentException("null alias passed to getCertificate.");
        }
        Certificate certificate = (Certificate) this.bT.b(str);
        if (certificate != null) {
            return certificate;
        }
        String str2 = (String) this.bS.get(str);
        return str2 != null ? (Certificate) this.bV.get(str2) : (Certificate) this.bV.get(str);
    }

    @Override // java.security.KeyStoreSpi
    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration b = this.bT.b();
        Enumeration a2 = this.bT.a();
        while (b.hasMoreElements()) {
            String str = (String) a2.nextElement();
            if (((Certificate) b.nextElement()).equals(certificate)) {
                return str;
            }
        }
        Enumeration elements = this.bV.elements();
        Enumeration keys = this.bV.keys();
        while (elements.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            if (((Certificate) elements.nextElement()).equals(certificate)) {
                return str2;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v21, types: [java.security.cert.X509Certificate] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // java.security.KeyStoreSpi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.security.cert.Certificate[] engineGetCertificateChain(java.lang.String r10) {
        /*
            r9 = this;
            r3 = 0
            if (r10 != 0) goto L_0x000b
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "null alias passed to getCertificateChain."
            r0.<init>(r1)
            throw r0
        L_0x000b:
            boolean r0 = r9.engineIsKeyEntry(r10)
            if (r0 != 0) goto L_0x0012
        L_0x0011:
            return r3
        L_0x0012:
            java.security.cert.Certificate r1 = r9.engineGetCertificate(r10)
            if (r1 == 0) goto L_0x0011
            java.util.Vector r5 = new java.util.Vector
            r5.<init>()
        L_0x001d:
            if (r1 == 0) goto L_0x00b7
            r0 = r1
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            org.bouncycastle.a.p r2 = org.bouncycastle.a.z.m.s
            java.lang.String r2 = r2.b()
            byte[] r2 = r0.getExtensionValue(r2)
            if (r2 == 0) goto L_0x00d4
            org.bouncycastle.a.l r4 = new org.bouncycastle.a.l     // Catch: IOException -> 0x00a9
            r4.<init>(r2)     // Catch: IOException -> 0x00a9
            org.bouncycastle.a.u r2 = r4.b()     // Catch: IOException -> 0x00a9
            org.bouncycastle.a.q r2 = (org.bouncycastle.a.q) r2     // Catch: IOException -> 0x00a9
            byte[] r2 = r2.c()     // Catch: IOException -> 0x00a9
            org.bouncycastle.a.l r4 = new org.bouncycastle.a.l     // Catch: IOException -> 0x00a9
            r4.<init>(r2)     // Catch: IOException -> 0x00a9
            org.bouncycastle.a.u r2 = r4.b()     // Catch: IOException -> 0x00a9
            org.bouncycastle.a.z.b r2 = org.bouncycastle.a.z.b.a(r2)     // Catch: IOException -> 0x00a9
            byte[] r4 = r2.a()     // Catch: IOException -> 0x00a9
            if (r4 == 0) goto L_0x00d2
            java.util.Hashtable r4 = r9.bU     // Catch: IOException -> 0x00a9
            org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r6 = new org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId     // Catch: IOException -> 0x00a9
            byte[] r2 = r2.a()     // Catch: IOException -> 0x00a9
            r6.<init>(r2)     // Catch: IOException -> 0x00a9
            java.lang.Object r2 = r4.get(r6)     // Catch: IOException -> 0x00a9
            java.security.cert.Certificate r2 = (java.security.cert.Certificate) r2     // Catch: IOException -> 0x00a9
        L_0x0061:
            r4 = r2
        L_0x0062:
            if (r4 != 0) goto L_0x00d0
            java.security.Principal r6 = r0.getIssuerDN()
            java.security.Principal r2 = r0.getSubjectDN()
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x00d0
            java.util.Hashtable r2 = r9.bU
            java.util.Enumeration r7 = r2.keys()
        L_0x0078:
            boolean r2 = r7.hasMoreElements()
            if (r2 == 0) goto L_0x00d0
            java.util.Hashtable r2 = r9.bU
            java.lang.Object r8 = r7.nextElement()
            java.lang.Object r2 = r2.get(r8)
            java.security.cert.X509Certificate r2 = (java.security.cert.X509Certificate) r2
            java.security.Principal r8 = r2.getSubjectDN()
            boolean r8 = r8.equals(r6)
            if (r8 == 0) goto L_0x0078
            java.security.PublicKey r8 = r2.getPublicKey()     // Catch: Exception -> 0x00ce
            r0.verify(r8)     // Catch: Exception -> 0x00ce
        L_0x009b:
            boolean r0 = r5.contains(r1)
            if (r0 != 0) goto L_0x00b4
            r5.addElement(r1)
            if (r2 == r1) goto L_0x00b4
            r1 = r2
            goto L_0x001d
        L_0x00a9:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x00b4:
            r1 = r3
            goto L_0x001d
        L_0x00b7:
            int r0 = r5.size()
            java.security.cert.Certificate[] r3 = new java.security.cert.Certificate[r0]
            r0 = 0
            r1 = r0
        L_0x00bf:
            int r0 = r3.length
            if (r1 == r0) goto L_0x0011
            java.lang.Object r0 = r5.elementAt(r1)
            java.security.cert.Certificate r0 = (java.security.cert.Certificate) r0
            r3[r1] = r0
            int r0 = r1 + 1
            r1 = r0
            goto L_0x00bf
        L_0x00ce:
            r2 = move-exception
            goto L_0x0078
        L_0x00d0:
            r2 = r4
            goto L_0x009b
        L_0x00d2:
            r2 = r3
            goto L_0x0061
        L_0x00d4:
            r4 = r3
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineGetCertificateChain(java.lang.String):java.security.cert.Certificate[]");
    }

    @Override // java.security.KeyStoreSpi
    public Date engineGetCreationDate(String str) {
        if (str == null) {
            throw new NullPointerException("alias == null");
        } else if (this.bR.b(str) == null && this.bT.b(str) == null) {
            return null;
        } else {
            return new Date();
        }
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String str, char[] cArr) {
        if (str != null) {
            return (Key) this.bR.b(str);
        }
        throw new IllegalArgumentException("null alias passed to getKey.");
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsCertificateEntry(String str) {
        return this.bT.b(str) != null && this.bR.b(str) == null;
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsKeyEntry(String str) {
        return this.bR.b(str) != null;
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream inputStream, char[] cArr) {
        boolean z;
        String str;
        String str2;
        if (inputStream != null) {
            if (cArr == null) {
                throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedInputStream.mark(10);
            if (bufferedInputStream.read() != 48) {
                throw new IOException("stream does not represent a PKCS12 key store");
            }
            bufferedInputStream.reset();
            try {
                o a2 = o.a(new org.bouncycastle.a.l(bufferedInputStream).b());
                org.bouncycastle.a.s.c a3 = a2.a();
                Vector vector = new Vector();
                boolean z2 = false;
                if (a2.b() != null) {
                    i b = a2.b();
                    j a4 = b.a();
                    this.bZ = a4.a();
                    byte[] b2 = b.b();
                    this.ca = a(b.c());
                    this.cb = b2.length;
                    byte[] c = ((q) a3.b()).c();
                    try {
                        byte[] a5 = a(this.bZ.a(), b2, this.ca, cArr, false, c);
                        byte[] b3 = a4.b();
                        if (org.bouncycastle.f.a.b(a5, b3)) {
                            z = false;
                        } else if (cArr.length > 0) {
                            throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                        } else if (!org.bouncycastle.f.a.b(a(this.bZ.a(), b2, this.ca, cArr, true, c), b3)) {
                            throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                        } else {
                            z = true;
                        }
                    } catch (IOException e) {
                        throw e;
                    } catch (Exception e2) {
                        throw new IOException("error constructing MAC: " + e2.toString());
                    }
                } else {
                    z = false;
                }
                this.bR = new IgnoresCaseHashtable((byte) 0);
                this.bS = new Hashtable();
                if (a3.a().equals(Q)) {
                    org.bouncycastle.a.s.c[] a6 = org.bouncycastle.a.s.a.a(new org.bouncycastle.a.l(((q) a3.b()).c()).b()).a();
                    for (int i = 0; i != a6.length; i++) {
                        if (a6[i].a().equals(Q)) {
                            org.bouncycastle.a.v vVar = (org.bouncycastle.a.v) new org.bouncycastle.a.l(((q) a6[i].b()).c()).b();
                            z2 = z2;
                            for (int i2 = 0; i2 != vVar.d(); i2++) {
                                v a7 = v.a(vVar.a(i2));
                                if (a7.a().equals(bx)) {
                                    org.bouncycastle.a.s.f a8 = org.bouncycastle.a.s.f.a(a7.b());
                                    PrivateKey a9 = a(a8.a(), a8.b(), cArr, z);
                                    q qVar = null;
                                    if (a7.c() != null) {
                                        Enumeration b4 = a7.c().b();
                                        str2 = null;
                                        while (b4.hasMoreElements()) {
                                            org.bouncycastle.a.v vVar2 = (org.bouncycastle.a.v) b4.nextElement();
                                            p pVar = (p) vVar2.a(0);
                                            x xVar = (x) vVar2.a(1);
                                            u uVar = null;
                                            if (xVar.c() > 0) {
                                                uVar = (u) xVar.a(0);
                                                if (a9 instanceof org.bouncycastle.c.a.n) {
                                                    org.bouncycastle.c.a.n nVar = (org.bouncycastle.c.a.n) a9;
                                                    f a10 = nVar.a(pVar);
                                                    if (a10 == null) {
                                                        nVar.a(pVar, uVar);
                                                    } else if (!a10.i().equals(uVar)) {
                                                        throw new IOException("attempt to add existing attribute with different value");
                                                    }
                                                }
                                            }
                                            if (pVar.equals(ak)) {
                                                String b5 = ((at) uVar).b();
                                                this.bR.a(b5, a9);
                                                str2 = b5;
                                            } else {
                                                qVar = pVar.equals(al) ? (q) uVar : qVar;
                                            }
                                        }
                                    } else {
                                        str2 = null;
                                    }
                                    if (qVar != null) {
                                        String str3 = new String(org.bouncycastle.f.a.f.b(qVar.c()));
                                        if (str2 == null) {
                                            this.bR.a(str3, a9);
                                            z2 = z2;
                                        } else {
                                            this.bS.put(str2, str3);
                                            z2 = z2;
                                        }
                                    } else {
                                        z2 = true;
                                        this.bR.a("unmarked", a9);
                                    }
                                } else if (a7.a().equals(by)) {
                                    vector.addElement(a7);
                                    z2 = z2;
                                } else {
                                    System.out.println("extra in data " + a7.a());
                                    System.out.println(org.bouncycastle.a.x.a.a(a7));
                                    z2 = z2;
                                }
                            }
                            continue;
                        } else if (a6[i].a().equals(V)) {
                            e a11 = e.a(a6[i].b());
                            org.bouncycastle.a.v vVar3 = (org.bouncycastle.a.v) u.b(a(false, a11.a(), cArr, z, a11.b().c()));
                            for (int i3 = 0; i3 != vVar3.d(); i3++) {
                                v a12 = v.a(vVar3.a(i3));
                                if (a12.a().equals(by)) {
                                    vector.addElement(a12);
                                } else if (a12.a().equals(bx)) {
                                    org.bouncycastle.a.s.f a13 = org.bouncycastle.a.s.f.a(a12.b());
                                    PrivateKey a14 = a(a13.a(), a13.b(), cArr, z);
                                    org.bouncycastle.c.a.n nVar2 = (org.bouncycastle.c.a.n) a14;
                                    String str4 = null;
                                    q qVar2 = null;
                                    Enumeration b6 = a12.c().b();
                                    while (b6.hasMoreElements()) {
                                        org.bouncycastle.a.v vVar4 = (org.bouncycastle.a.v) b6.nextElement();
                                        p pVar2 = (p) vVar4.a(0);
                                        x xVar2 = (x) vVar4.a(1);
                                        u uVar2 = null;
                                        if (xVar2.c() > 0) {
                                            uVar2 = (u) xVar2.a(0);
                                            f a15 = nVar2.a(pVar2);
                                            if (a15 == null) {
                                                nVar2.a(pVar2, uVar2);
                                            } else if (!a15.i().equals(uVar2)) {
                                                throw new IOException("attempt to add existing attribute with different value");
                                            }
                                        }
                                        if (pVar2.equals(ak)) {
                                            String b7 = ((at) uVar2).b();
                                            this.bR.a(b7, a14);
                                            str4 = b7;
                                        } else {
                                            qVar2 = pVar2.equals(al) ? (q) uVar2 : qVar2;
                                        }
                                    }
                                    String str5 = new String(org.bouncycastle.f.a.f.b(qVar2.c()));
                                    if (str4 == null) {
                                        this.bR.a(str5, a14);
                                    } else {
                                        this.bS.put(str4, str5);
                                    }
                                } else if (a12.a().equals(bw)) {
                                    PrivateKey a16 = org.bouncycastle.c.b.a.a(org.bouncycastle.a.s.p.a(a12.b()));
                                    org.bouncycastle.c.a.n nVar3 = (org.bouncycastle.c.a.n) a16;
                                    String str6 = null;
                                    q qVar3 = null;
                                    Enumeration b8 = a12.c().b();
                                    while (b8.hasMoreElements()) {
                                        org.bouncycastle.a.v a17 = org.bouncycastle.a.v.a(b8.nextElement());
                                        p a18 = p.a(a17.a(0));
                                        x a19 = x.a((Object) a17.a(1));
                                        if (a19.c() > 0) {
                                            u uVar3 = (u) a19.a(0);
                                            f a20 = nVar3.a(a18);
                                            if (a20 == null) {
                                                nVar3.a(a18, uVar3);
                                            } else if (!a20.i().equals(uVar3)) {
                                                throw new IOException("attempt to add existing attribute with different value");
                                            }
                                            if (a18.equals(ak)) {
                                                String b9 = ((at) uVar3).b();
                                                this.bR.a(b9, a16);
                                                str6 = b9;
                                            } else if (a18.equals(al)) {
                                                qVar3 = (q) uVar3;
                                            }
                                        }
                                        qVar3 = qVar3;
                                    }
                                    String str7 = new String(org.bouncycastle.f.a.f.b(qVar3.c()));
                                    if (str6 == null) {
                                        this.bR.a(str7, a16);
                                    } else {
                                        this.bS.put(str6, str7);
                                    }
                                } else {
                                    System.out.println("extra in encryptedData " + a12.a());
                                    System.out.println(org.bouncycastle.a.x.a.a(a12));
                                }
                            }
                            z2 = z2;
                        } else {
                            System.out.println("extra " + a6[i].a().b());
                            System.out.println("extra " + org.bouncycastle.a.x.a.a(a6[i].b()));
                            z2 = z2;
                        }
                    }
                }
                this.bT = new IgnoresCaseHashtable((byte) 0);
                this.bU = new Hashtable();
                this.bV = new Hashtable();
                for (int i4 = 0; i4 != vector.size(); i4++) {
                    v vVar5 = (v) vector.elementAt(i4);
                    org.bouncycastle.a.s.b a21 = org.bouncycastle.a.s.b.a(vVar5.b());
                    if (!a21.a().equals(ao)) {
                        throw new RuntimeException("Unsupported certificate type: " + a21.a());
                    }
                    try {
                        Certificate generateCertificate = this.bW.generateCertificate(new ByteArrayInputStream(((q) a21.b()).c()));
                        q qVar4 = null;
                        if (vVar5.c() != null) {
                            Enumeration b10 = vVar5.c().b();
                            str = null;
                            while (b10.hasMoreElements()) {
                                org.bouncycastle.a.v a22 = org.bouncycastle.a.v.a(b10.nextElement());
                                p a23 = p.a(a22.a(0));
                                x a24 = x.a((Object) a22.a(1));
                                if (a24.c() > 0) {
                                    u uVar4 = (u) a24.a(0);
                                    if (generateCertificate instanceof org.bouncycastle.c.a.n) {
                                        org.bouncycastle.c.a.n nVar4 = (org.bouncycastle.c.a.n) generateCertificate;
                                        f a25 = nVar4.a(a23);
                                        if (a25 == null) {
                                            nVar4.a(a23, uVar4);
                                        } else if (!a25.i().equals(uVar4)) {
                                            throw new IOException("attempt to add existing attribute with different value");
                                        }
                                    }
                                    if (a23.equals(ak)) {
                                        str = ((at) uVar4).b();
                                    } else if (a23.equals(al)) {
                                        qVar4 = (q) uVar4;
                                    }
                                }
                                qVar4 = qVar4;
                            }
                        } else {
                            str = null;
                        }
                        this.bU.put(new CertId(generateCertificate.getPublicKey()), generateCertificate);
                        if (!z2) {
                            if (qVar4 != null) {
                                this.bV.put(new String(org.bouncycastle.f.a.f.b(qVar4.c())), generateCertificate);
                            }
                            if (str != null) {
                                this.bT.a(str, generateCertificate);
                            }
                        } else if (this.bV.isEmpty()) {
                            String str8 = new String(org.bouncycastle.f.a.f.b(b(generateCertificate.getPublicKey()).a()));
                            this.bV.put(str8, generateCertificate);
                            this.bR.a(str8, this.bR.a("unmarked"));
                        }
                    } catch (Exception e3) {
                        throw new RuntimeException(e3.toString());
                    }
                }
            } catch (Exception e4) {
                throw new IOException(e4.getMessage());
            }
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String str, Certificate certificate) {
        if (this.bR.b(str) != null) {
            throw new KeyStoreException("There is a key entry with the name " + str + ".");
        }
        this.bT.a(str, certificate);
        this.bU.put(new CertId(certificate.getPublicKey()), certificate);
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
        if (!(key instanceof PrivateKey)) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        } else if (!(key instanceof PrivateKey) || certificateArr != null) {
            if (this.bR.b(str) != null) {
                engineDeleteEntry(str);
            }
            this.bR.a(str, key);
            if (certificateArr != null) {
                this.bT.a(str, certificateArr[0]);
                for (int i = 0; i != certificateArr.length; i++) {
                    this.bU.put(new CertId(certificateArr[i].getPublicKey()), certificateArr[i]);
                }
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) {
        throw new RuntimeException("operation not supported");
    }

    @Override // java.security.KeyStoreSpi
    public int engineSize() {
        Hashtable hashtable = new Hashtable();
        Enumeration a2 = this.bT.a();
        while (a2.hasMoreElements()) {
            hashtable.put(a2.nextElement(), "cert");
        }
        Enumeration a3 = this.bR.a();
        while (a3.hasMoreElements()) {
            String str = (String) a3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, "key");
            }
        }
        return hashtable.size();
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream outputStream, char[] cArr) {
        a(outputStream, cArr, false);
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) {
        char[] password;
        if (loadStoreParameter == null) {
            throw new IllegalArgumentException("'param' arg cannot be null");
        } else if ((loadStoreParameter instanceof PKCS12StoreParameter) || (loadStoreParameter instanceof d)) {
            PKCS12StoreParameter pKCS12StoreParameter = loadStoreParameter instanceof PKCS12StoreParameter ? (PKCS12StoreParameter) loadStoreParameter : new PKCS12StoreParameter(((d) loadStoreParameter).a(), loadStoreParameter.getProtectionParameter(), ((d) loadStoreParameter).b());
            KeyStore.ProtectionParameter protectionParameter = loadStoreParameter.getProtectionParameter();
            if (protectionParameter == null) {
                password = null;
            } else if (protectionParameter instanceof KeyStore.PasswordProtection) {
                password = ((KeyStore.PasswordProtection) protectionParameter).getPassword();
            } else {
                throw new IllegalArgumentException("No support for protection parameter of type " + protectionParameter.getClass().getName());
            }
            a(pKCS12StoreParameter.a(), password, pKCS12StoreParameter.b());
        } else {
            throw new IllegalArgumentException("No support for 'param' of type " + loadStoreParameter.getClass().getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DefaultSecretKeyProvider {

        /* renamed from: a  reason: collision with root package name */
        private final Map f1102a;

        public final int a(a aVar) {
            Integer num = (Integer) this.f1102a.get(aVar.a());
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }

        DefaultSecretKeyProvider() {
            HashMap hashMap = new HashMap();
            hashMap.put(new p("1.2.840.113533.7.66.10"), 128);
            hashMap.put(n.D, 192);
            hashMap.put(org.bouncycastle.a.o.b.u, 128);
            hashMap.put(org.bouncycastle.a.o.b.C, 192);
            hashMap.put(org.bouncycastle.a.o.b.K, 256);
            hashMap.put(org.bouncycastle.a.q.a.f398a, 128);
            hashMap.put(org.bouncycastle.a.q.a.b, 192);
            hashMap.put(org.bouncycastle.a.q.a.c, 256);
            hashMap.put(org.bouncycastle.a.f.a.f, 256);
            this.f1102a = Collections.unmodifiableMap(hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static t b(PublicKey publicKey) {
        try {
            org.bouncycastle.a.z.u a2 = org.bouncycastle.a.z.u.a(publicKey.getEncoded());
            s sVar = new s();
            byte[] bArr = new byte[sVar.b()];
            byte[] d = a2.c().d();
            sVar.a(d, 0, d.length);
            sVar.a(bArr, 0);
            return new t(bArr);
        } catch (Exception e) {
            throw new RuntimeException("error creating key");
        }
    }
}
