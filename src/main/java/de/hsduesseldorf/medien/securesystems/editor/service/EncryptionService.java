package de.hsduesseldorf.medien.securesystems.editor.service;


import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.options.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.options.Padding;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

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

    public static Document encryptAES(final Document document, String password, CipherName cipherName, BlockMode blockMode, Padding padding) {

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1", PROVIDER_NAME);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), Hex.encode("test".getBytes()), 1024, 128);
            SecretKey key = factory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance(cipherName + "/" + blockMode + "/" + padding, PROVIDER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted = cipher.doFinal(Hex.encode(document.getPayload()));
            return new Document(new Date(), document.getCipherName(), encrypted);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
