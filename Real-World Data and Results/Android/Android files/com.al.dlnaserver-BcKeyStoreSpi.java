package org.bouncycastle.jcajce.provider.keystore.bc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.a.s.p;
import org.bouncycastle.a.z.u;
import org.bouncycastle.b.ad;
import org.bouncycastle.b.b.s;
import org.bouncycastle.b.f.v;
import org.bouncycastle.b.g.b;
import org.bouncycastle.b.g.c;
import org.bouncycastle.b.g.e;
import org.bouncycastle.b.g.f;
import org.bouncycastle.b.h.g;
import org.bouncycastle.b.i;
import org.bouncycastle.b.l;
import org.bouncycastle.f.b.a;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;

/* loaded from: classes.dex */
public class BcKeyStoreSpi extends KeyStoreSpi {
    protected int c;

    /* renamed from: a  reason: collision with root package name */
    protected Hashtable f1096a = new Hashtable();
    protected SecureRandom b = l.a();
    private final JcaJceHelper d = new BCJcaJceHelper();

    /* loaded from: classes.dex */
    public static class BouncyCastleStore extends BcKeyStoreSpi {
        public BouncyCastleStore() {
            super(1);
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi, java.security.KeyStoreSpi
        public void engineLoad(InputStream inputStream, char[] cArr) {
            this.f1096a.clear();
            if (inputStream != null) {
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                int readInt = dataInputStream.readInt();
                if (readInt == 2 || readInt == 0 || readInt == 1) {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    if (bArr.length != 20) {
                        throw new IOException("Key store corrupted.");
                    }
                    dataInputStream.readFully(bArr);
                    int readInt2 = dataInputStream.readInt();
                    if (readInt2 < 0 || readInt2 > 65536) {
                        throw new IOException("Key store corrupted.");
                    }
                    CipherInputStream cipherInputStream = new CipherInputStream(dataInputStream, a(readInt == 0 ? "OldPBEWithSHAAndTwofish-CBC" : "PBEWithSHAAndTwofish-CBC", 2, cArr, bArr, readInt2));
                    s sVar = new s();
                    a(new b(cipherInputStream, sVar));
                    byte[] bArr2 = new byte[sVar.b()];
                    sVar.a(bArr2, 0);
                    byte[] bArr3 = new byte[sVar.b()];
                    a.a(cipherInputStream, bArr3);
                    if (!org.bouncycastle.f.a.b(bArr2, bArr3)) {
                        this.f1096a.clear();
                        throw new IOException("KeyStore integrity check failed.");
                    }
                    return;
                }
                throw new IOException("Wrong version of key store.");
            }
        }

        @Override // org.bouncycastle.jcajce.provider.keystore.bc.BcKeyStoreSpi, java.security.KeyStoreSpi
        public void engineStore(OutputStream outputStream, char[] cArr) {
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            byte[] bArr = new byte[20];
            int nextInt = (this.b.nextInt() & 1023) + 1024;
            this.b.nextBytes(bArr);
            dataOutputStream.writeInt(this.c);
            dataOutputStream.writeInt(20);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(dataOutputStream, a("PBEWithSHAAndTwofish-CBC", 1, cArr, bArr, nextInt));
            c cVar = new c(new s());
            a(new org.bouncycastle.f.b.b(cipherOutputStream, cVar));
            cipherOutputStream.write(cVar.a());
            cipherOutputStream.close();
        }
    }

    /* loaded from: classes.dex */
    public static class Std extends BcKeyStoreSpi {
        public Std() {
            super(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class StoreEntry {

        /* renamed from: a  reason: collision with root package name */
        int f1097a;
        String b;
        Object c;
        Certificate[] d;
        Date e;

        StoreEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
            this.e = new Date();
            this.f1097a = 4;
            this.b = str;
            this.d = certificateArr;
            byte[] bArr = new byte[20];
            BcKeyStoreSpi.this.b.setSeed(System.currentTimeMillis());
            BcKeyStoreSpi.this.b.nextBytes(bArr);
            int nextInt = (BcKeyStoreSpi.this.b.nextInt() & 1023) + 1024;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(20);
            dataOutputStream.write(bArr);
            dataOutputStream.writeInt(nextInt);
            DataOutputStream dataOutputStream2 = new DataOutputStream(new CipherOutputStream(dataOutputStream, BcKeyStoreSpi.this.a("PBEWithSHAAnd3-KeyTripleDES-CBC", 1, cArr, bArr, nextInt)));
            BcKeyStoreSpi.b(key, dataOutputStream2);
            dataOutputStream2.close();
            this.c = byteArrayOutputStream.toByteArray();
        }

        StoreEntry(String str, Certificate certificate) {
            this.e = new Date();
            this.f1097a = 1;
            this.b = str;
            this.c = certificate;
            this.d = null;
        }

        StoreEntry(String str, Date date, int i, Object obj, Certificate[] certificateArr) {
            this.e = new Date();
            this.b = str;
            this.e = date;
            this.f1097a = i;
            this.c = obj;
            this.d = certificateArr;
        }

        StoreEntry(String str, Date date, Object obj) {
            this.e = new Date();
            this.b = str;
            this.e = date;
            this.f1097a = 1;
            this.c = obj;
        }

        StoreEntry(String str, byte[] bArr, Certificate[] certificateArr) {
            this.e = new Date();
            this.f1097a = 3;
            this.b = str;
            this.c = bArr;
            this.d = certificateArr;
        }

        final Object a(char[] cArr) {
            Key b;
            if ((cArr == null || cArr.length == 0) && (this.c instanceof Key)) {
                return this.c;
            }
            if (this.f1097a == 4) {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) this.c));
                try {
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(bArr);
                    try {
                        return BcKeyStoreSpi.this.b(new DataInputStream(new CipherInputStream(dataInputStream, BcKeyStoreSpi.this.a("PBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr, dataInputStream.readInt()))));
                    } catch (Exception e) {
                        DataInputStream dataInputStream2 = new DataInputStream(new ByteArrayInputStream((byte[]) this.c));
                        byte[] bArr2 = new byte[dataInputStream2.readInt()];
                        dataInputStream2.readFully(bArr2);
                        int readInt = dataInputStream2.readInt();
                        try {
                            b = BcKeyStoreSpi.this.b(new DataInputStream(new CipherInputStream(dataInputStream2, BcKeyStoreSpi.this.a("BrokenPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr2, readInt))));
                        } catch (Exception e2) {
                            DataInputStream dataInputStream3 = new DataInputStream(new ByteArrayInputStream((byte[]) this.c));
                            bArr2 = new byte[dataInputStream3.readInt()];
                            dataInputStream3.readFully(bArr2);
                            readInt = dataInputStream3.readInt();
                            b = BcKeyStoreSpi.this.b(new DataInputStream(new CipherInputStream(dataInputStream3, BcKeyStoreSpi.this.a("OldPBEWithSHAAnd3-KeyTripleDES-CBC", 2, cArr, bArr2, readInt))));
                        }
                        if (b != null) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                            dataOutputStream.writeInt(bArr2.length);
                            dataOutputStream.write(bArr2);
                            dataOutputStream.writeInt(readInt);
                            DataOutputStream dataOutputStream2 = new DataOutputStream(new CipherOutputStream(dataOutputStream, BcKeyStoreSpi.this.a("PBEWithSHAAnd3-KeyTripleDES-CBC", 1, cArr, bArr2, readInt)));
                            BcKeyStoreSpi.b(b, dataOutputStream2);
                            dataOutputStream2.close();
                            this.c = byteArrayOutputStream.toByteArray();
                            return b;
                        }
                        throw new UnrecoverableKeyException("no match");
                    }
                } catch (Exception e3) {
                    throw new UnrecoverableKeyException("no match");
                }
            } else {
                throw new RuntimeException("forget something!");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Version1 extends BcKeyStoreSpi {
        public Version1() {
            super(1);
        }
    }

    public BcKeyStoreSpi(int i) {
        this.c = i;
    }

    private Certificate a(DataInputStream dataInputStream) {
        String readUTF = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        try {
            return this.d.g(readUTF).generateCertificate(new ByteArrayInputStream(bArr));
        } catch (NoSuchProviderException e) {
            throw new IOException(e.toString());
        } catch (CertificateException e2) {
            throw new IOException(e2.toString());
        }
    }

    private static void a(Certificate certificate, DataOutputStream dataOutputStream) {
        try {
            byte[] encoded = certificate.getEncoded();
            dataOutputStream.writeUTF(certificate.getType());
            dataOutputStream.writeInt(encoded.length);
            dataOutputStream.write(encoded);
        } catch (CertificateEncodingException e) {
            throw new IOException(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Key b(DataInputStream dataInputStream) {
        KeySpec keySpec;
        int read = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        String readUTF2 = dataInputStream.readUTF();
        byte[] bArr = new byte[dataInputStream.readInt()];
        dataInputStream.readFully(bArr);
        if (readUTF.equals("PKCS#8") || readUTF.equals("PKCS8")) {
            keySpec = new PKCS8EncodedKeySpec(bArr);
        } else if (readUTF.equals("X.509") || readUTF.equals("X509")) {
            keySpec = new X509EncodedKeySpec(bArr);
        } else if (readUTF.equals("RAW")) {
            return new SecretKeySpec(bArr, readUTF2);
        } else {
            throw new IOException("Key format " + readUTF + " not recognised!");
        }
        try {
            switch (read) {
                case 0:
                    return org.bouncycastle.c.b.a.a(p.a(bArr));
                case 1:
                    return org.bouncycastle.c.b.a.a(u.a(bArr));
                case 2:
                    return this.d.e(readUTF2).generateSecret(keySpec);
                default:
                    throw new IOException("Key type " + read + " not recognised!");
            }
        } catch (Exception e) {
            throw new IOException("Exception creating key: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Key key, DataOutputStream dataOutputStream) {
        byte[] encoded = key.getEncoded();
        if (key instanceof PrivateKey) {
            dataOutputStream.write(0);
        } else if (key instanceof PublicKey) {
            dataOutputStream.write(1);
        } else {
            dataOutputStream.write(2);
        }
        dataOutputStream.writeUTF(key.getFormat());
        dataOutputStream.writeUTF(key.getAlgorithm());
        dataOutputStream.writeInt(encoded.length);
        dataOutputStream.write(encoded);
    }

    protected final Cipher a(String str, int i, char[] cArr, byte[] bArr, int i2) {
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
            SecretKeyFactory e = this.d.e(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i2);
            Cipher a2 = this.d.a(str);
            a2.init(i, e.generateSecret(pBEKeySpec), pBEParameterSpec);
            return a2;
        } catch (Exception e2) {
            throw new IOException("Error initialising store of key store: ".concat(String.valueOf(e2)));
        }
    }

    protected final void a(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        for (int read = dataInputStream.read(); read > 0; read = dataInputStream.read()) {
            String readUTF = dataInputStream.readUTF();
            Date date = new Date(dataInputStream.readLong());
            int readInt = dataInputStream.readInt();
            Certificate[] certificateArr = null;
            if (readInt != 0) {
                certificateArr = new Certificate[readInt];
                for (int i = 0; i != readInt; i++) {
                    certificateArr[i] = a(dataInputStream);
                }
            }
            switch (read) {
                case 1:
                    this.f1096a.put(readUTF, new StoreEntry(readUTF, date, a(dataInputStream)));
                    break;
                case 2:
                    this.f1096a.put(readUTF, new StoreEntry(readUTF, date, 2, b(dataInputStream), certificateArr));
                    break;
                case 3:
                case 4:
                    byte[] bArr = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(bArr);
                    this.f1096a.put(readUTF, new StoreEntry(readUTF, date, read, bArr, certificateArr));
                    break;
                default:
                    throw new IOException("Unknown object type in store.");
            }
        }
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration engineAliases() {
        return this.f1096a.keys();
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineContainsAlias(String str) {
        return this.f1096a.get(str) != null;
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String str) {
        if (this.f1096a.get(str) != null) {
            this.f1096a.remove(str);
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream inputStream, char[] cArr) {
        this.f1096a.clear();
        if (inputStream != null) {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int readInt = dataInputStream.readInt();
            if (readInt == 2 || readInt == 0 || readInt == 1) {
                int readInt2 = dataInputStream.readInt();
                if (readInt2 <= 0) {
                    throw new IOException("Invalid salt detected");
                }
                byte[] bArr = new byte[readInt2];
                dataInputStream.readFully(bArr);
                int readInt3 = dataInputStream.readInt();
                g gVar = new g(new s());
                if (cArr == null || cArr.length == 0) {
                    a((InputStream) dataInputStream);
                    dataInputStream.readFully(new byte[gVar.b()]);
                    return;
                }
                byte[] c = ad.c(cArr);
                v vVar = new v(new s());
                vVar.a(c, bArr, readInt3);
                i b = readInt != 2 ? vVar.b(gVar.b()) : vVar.b(gVar.b() * 8);
                org.bouncycastle.f.a.a(c, (byte) 0);
                gVar.a(b);
                a(new e(dataInputStream, gVar));
                byte[] bArr2 = new byte[gVar.b()];
                gVar.a(bArr2, 0);
                byte[] bArr3 = new byte[gVar.b()];
                dataInputStream.readFully(bArr3);
                if (!org.bouncycastle.f.a.b(bArr2, bArr3)) {
                    this.f1096a.clear();
                    throw new IOException("KeyStore integrity check failed.");
                }
                return;
            }
            throw new IOException("Wrong version of key store.");
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
        if (!(key instanceof PrivateKey) || certificateArr != null) {
            try {
                this.f1096a.put(str, new StoreEntry(str, key, cArr, certificateArr));
            } catch (Exception e) {
                throw new KeyStoreException(e.toString());
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) {
        this.f1096a.put(str, new StoreEntry(str, bArr, certificateArr));
    }

    @Override // java.security.KeyStoreSpi
    public int engineSize() {
        return this.f1096a.size();
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream outputStream, char[] cArr) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        byte[] bArr = new byte[20];
        int nextInt = (this.b.nextInt() & 1023) + 1024;
        this.b.nextBytes(bArr);
        dataOutputStream.writeInt(this.c);
        dataOutputStream.writeInt(20);
        dataOutputStream.write(bArr);
        dataOutputStream.writeInt(nextInt);
        g gVar = new g(new s());
        f fVar = new f(gVar);
        v vVar = new v(new s());
        byte[] c = ad.c(cArr);
        vVar.a(c, bArr, nextInt);
        if (this.c < 2) {
            gVar.a(vVar.b(gVar.b()));
        } else {
            gVar.a(vVar.b(gVar.b() * 8));
        }
        for (int i = 0; i != c.length; i++) {
            c[i] = 0;
        }
        a(new org.bouncycastle.f.b.b(dataOutputStream, fVar));
        byte[] bArr2 = new byte[gVar.b()];
        gVar.a(bArr2, 0);
        dataOutputStream.write(bArr2);
        dataOutputStream.close();
    }

    @Override // java.security.KeyStoreSpi
    public Certificate engineGetCertificate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.f1096a.get(str);
        if (storeEntry != null) {
            if (storeEntry.f1097a == 1) {
                return (Certificate) storeEntry.c;
            }
            Certificate[] certificateArr = storeEntry.d;
            if (certificateArr != null) {
                return certificateArr[0];
            }
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration elements = this.f1096a.elements();
        while (elements.hasMoreElements()) {
            StoreEntry storeEntry = (StoreEntry) elements.nextElement();
            if (!(storeEntry.c instanceof Certificate)) {
                Certificate[] certificateArr = storeEntry.d;
                if (certificateArr != null && certificateArr[0].equals(certificate)) {
                    return storeEntry.b;
                }
            } else if (((Certificate) storeEntry.c).equals(certificate)) {
                return storeEntry.b;
            }
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Certificate[] engineGetCertificateChain(String str) {
        StoreEntry storeEntry = (StoreEntry) this.f1096a.get(str);
        if (storeEntry != null) {
            return storeEntry.d;
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Date engineGetCreationDate(String str) {
        StoreEntry storeEntry = (StoreEntry) this.f1096a.get(str);
        if (storeEntry != null) {
            return storeEntry.e;
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String str, char[] cArr) {
        StoreEntry storeEntry = (StoreEntry) this.f1096a.get(str);
        if (storeEntry == null || storeEntry.f1097a == 1) {
            return null;
        }
        return (Key) storeEntry.a(cArr);
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsCertificateEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.f1096a.get(str);
        return storeEntry != null && storeEntry.f1097a == 1;
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsKeyEntry(String str) {
        StoreEntry storeEntry = (StoreEntry) this.f1096a.get(str);
        return (storeEntry == null || storeEntry.f1097a == 1) ? false : true;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String str, Certificate certificate) {
        StoreEntry storeEntry = (StoreEntry) this.f1096a.get(str);
        if (storeEntry == null || storeEntry.f1097a == 1) {
            this.f1096a.put(str, new StoreEntry(str, certificate));
            return;
        }
        throw new KeyStoreException("key store already has a key entry with alias ".concat(String.valueOf(str)));
    }

    protected final void a(OutputStream outputStream) {
        Enumeration elements = this.f1096a.elements();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        while (elements.hasMoreElements()) {
            StoreEntry storeEntry = (StoreEntry) elements.nextElement();
            dataOutputStream.write(storeEntry.f1097a);
            dataOutputStream.writeUTF(storeEntry.b);
            dataOutputStream.writeLong(storeEntry.e.getTime());
            Certificate[] certificateArr = storeEntry.d;
            if (certificateArr == null) {
                dataOutputStream.writeInt(0);
            } else {
                dataOutputStream.writeInt(certificateArr.length);
                for (int i = 0; i != certificateArr.length; i++) {
                    a(certificateArr[i], dataOutputStream);
                }
            }
            switch (storeEntry.f1097a) {
                case 1:
                    a((Certificate) storeEntry.c, dataOutputStream);
                    break;
                case 2:
                    b((Key) storeEntry.c, dataOutputStream);
                    break;
                case 3:
                case 4:
                    byte[] bArr = (byte[]) storeEntry.c;
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(bArr);
                    break;
                default:
                    throw new IOException("Unknown object type in store.");
            }
        }
        dataOutputStream.write(0);
    }
}
