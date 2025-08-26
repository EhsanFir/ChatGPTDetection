package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.zip.Deflater;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: UMSLUtils.java */
/* loaded from: classes.dex */
public class f {
    public static int a;
    private static final byte[] b = {10, 1, 11, 5, 4, ap.m, 7, 9, 23, 3, 1, 6, 8, 12, ap.k, 91};
    private static Object c = new Object();

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b4, code lost:
        if (0 == 0) goto L_0x00b9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b6, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r10, java.lang.String r11, java.lang.String r12, byte[] r13) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.f.a(android.content.Context, java.lang.String, java.lang.String, byte[]):boolean");
    }

    public static byte[] a(String str) throws IOException {
        Throwable th;
        IOException e;
        FileChannel channel;
        byte[] bArr;
        FileChannel fileChannel = null;
        synchronized (c) {
            ULog.i("walle", "[stateless] begin read envelope, thread is " + Thread.currentThread());
            try {
                try {
                    channel = new RandomAccessFile(str, "r").getChannel();
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                MappedByteBuffer load = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).load();
                System.out.println(load.isLoaded());
                bArr = new byte[(int) channel.size()];
                if (load.remaining() > 0) {
                    load.get(bArr, 0, load.remaining());
                }
                ULog.i("walle", "[stateless] end read envelope, thread id " + Thread.currentThread());
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e3) {
                    }
                }
            } catch (IOException e4) {
                e = e4;
                fileChannel = channel;
                ULog.i("walle", "[stateless] write envelope, e is " + e.getMessage());
                throw e;
            } catch (Throwable th3) {
                th = th3;
                fileChannel = channel;
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e5) {
                    }
                }
                throw th;
            }
        }
        return bArr;
    }

    public static File a(Context context) {
        File file;
        Throwable th;
        File file2;
        File[] listFiles;
        File[] listFiles2;
        File file3 = null;
        try {
        } catch (Throwable th2) {
            th = th2;
            file = null;
        }
        synchronized (c) {
            try {
                ULog.i("walle", "get last envelope begin, thread is " + Thread.currentThread());
                if (!(context == null || context.getApplicationContext() == null)) {
                    String str = context.getApplicationContext().getFilesDir() + "/" + a.e;
                    if (!TextUtils.isEmpty(str) && (file2 = new File(str)) != null && file2.isDirectory() && (listFiles = file2.listFiles()) != null && listFiles.length > 0) {
                        file = null;
                        for (File file4 : listFiles) {
                            try {
                                if (file4 != null && file4.isDirectory() && (listFiles2 = file4.listFiles()) != null && listFiles2.length > 0) {
                                    Arrays.sort(listFiles2, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.1
                                        /* renamed from: a */
                                        public int compare(File file5, File file6) {
                                            long lastModified = file5.lastModified() - file6.lastModified();
                                            if (lastModified > 0) {
                                                return 1;
                                            }
                                            if (lastModified == 0) {
                                                return 0;
                                            }
                                            return -1;
                                        }
                                    });
                                    File file5 = listFiles2[0];
                                    if (file5 != null && (file == null || file.lastModified() > file5.lastModified())) {
                                        file = file5;
                                    }
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                file3 = file;
                                try {
                                    throw th;
                                } catch (Throwable th4) {
                                    th = th4;
                                    file = file3;
                                    UMCrashManager.reportCrash(context, th);
                                    return file;
                                }
                            }
                        }
                        ULog.i("walle", "get last envelope end, thread is " + Thread.currentThread());
                        return file;
                    }
                }
                file = null;
                ULog.i("walle", "get last envelope end, thread is " + Thread.currentThread());
                return file;
            } catch (Throwable th5) {
                th = th5;
            }
        }
    }

    public static void a(Context context, String str, int i) {
        try {
            if (str == null) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            File file = new File(str);
            if (!file.isDirectory()) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (c) {
                File[] listFiles = file.listFiles();
                ULog.i("AmapLBS", "[lbs-build] delete file begin " + listFiles.length + ", thread is " + Thread.currentThread());
                if (listFiles == null || listFiles.length < i) {
                    ULog.i("AmapLBS", "[lbs-build] file size < max");
                } else {
                    ULog.i("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : listFiles) {
                        if (file2 != null) {
                            arrayList.add(file2);
                        }
                    }
                    if (arrayList != null && arrayList.size() >= i) {
                        Collections.sort(arrayList, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.2
                            /* renamed from: a */
                            public int compare(File file3, File file4) {
                                if (file3 != null && file4 != null && file3.lastModified() < file4.lastModified()) {
                                    return -1;
                                }
                                if (file3 == null || file4 == null || file3.lastModified() != file4.lastModified()) {
                                    return 1;
                                }
                                return 0;
                            }
                        });
                        if (ULog.DEBUG) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ULog.i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                            }
                        }
                        for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                            if (!(arrayList == null || arrayList.get(i3) == null)) {
                                ULog.i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                try {
                                    ((File) arrayList.get(i3)).delete();
                                    arrayList.remove(i3);
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }
                ULog.i("AmapLBS", "[lbs-build] delete file end " + listFiles.length + ", thread is " + Thread.currentThread());
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
    }

    public static boolean a(long j, long j2) {
        if (j > j2) {
            return true;
        }
        return false;
    }

    public static byte[] a(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int deflate = deflater.deflate(bArr2);
                    a += deflate;
                    byteArrayOutputStream.write(bArr2, 0, deflate);
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            deflater.end();
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(b));
        return instance.doFinal(bArr);
    }

    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            return null;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(bArr[i])));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }
}
