package de.hsduesseldorf.medien.securesystems.editor.service;


import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherType;
import de.hsduesseldorf.medien.securesystems.editor.options.Padding;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.KeySpec;
import java.util.Date;

public class EncryptionService {

    private static final String PROVIDER_NAME = "BC";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static Document encryptAES(final Document document, String password, CipherType cipherType, CipherMode cipherMode, Padding padding) {

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1", PROVIDER_NAME);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), "test".getBytes(), 1024, 128);
            SecretKey key = factory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance(cipherType + "/" + cipherMode + "/" + padding, PROVIDER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted = cipher.doFinal(document.getPayload());
            return new Document(new Date(), document.getCipherType(), encrypted);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
