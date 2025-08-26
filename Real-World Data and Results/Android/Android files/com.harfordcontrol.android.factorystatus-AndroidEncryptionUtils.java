package com.harfordcontrol.android.utils;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/* loaded from: classes.dex */
public class AndroidEncryptionUtils {
    private static volatile KeyGenerator aesKeyGen;
    private static final RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger("19527229834557071693850733419460704998426342639363882990471719762599043507434708619343027652461585973989483791514283190946645459447502150036574450354971120450168895752218944816459566625233022354239039547047953545281680048905915827473908108311563938917947521932889525344296975678996809309842888175809041393669302432213687079683429754210467515643012329323869566167226222387882026434976350017018534665198472162861092134926344251921086625944474381530127765844851273353388690572700482253925465675625156765713474045231687387445285835225663752594193342406716536240000927856075196515614241705387022098371911083406945902014323"), new BigInteger("65537"));
    private static volatile PublicKey encryptKey = null;

    public static PublicKey getRsaPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        initEncryption();
        return encryptKey;
    }

    public static SecretKey createAesSharedKey() throws InvalidKeySpecException, NoSuchAlgorithmException {
        initEncryption();
        return aesKeyGen.generateKey();
    }

    private static void initEncryption() throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (encryptKey == null || aesKeyGen == null) {
            encryptKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
            aesKeyGen = KeyGenerator.getInstance("AES");
            aesKeyGen.init(128, new SecureRandom());
        }
    }

    public static byte[] encryptPassword(String password) throws Exception {
        initEncryption();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, encryptKey);
        return cipher.doFinal(password.getBytes("utf-8"));
    }
}
