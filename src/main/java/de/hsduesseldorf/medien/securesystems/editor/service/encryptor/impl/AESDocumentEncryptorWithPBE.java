package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.DocumentEncryptor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.KeySpec;

public class AESDocumentEncryptorWithPBE implements DocumentEncryptor {

    private static Logger LOG = LoggerFactory.getLogger(AESDocumentEncryptorWithPBE.class);

    static final int IT_COUNT = 65536;
    static final int KEY_SIZE = 256;
    char[] password;

    public AESDocumentEncryptorWithPBE(char[] password) throws Exception {
        this.password = password;
    }

    SecretKey generateSecret(byte[] salt) throws GeneralSecurityException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(this.password, salt, IT_COUNT, KEY_SIZE);
        SecretKey temp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(temp.getEncoded(), "AES");
        return secret;
    }

    @Override
    public Document encrypt(Document document) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        byte[] salt = new byte[256];
        new SecureRandom().nextBytes(salt);
        document.setSalt(salt);
        SecretKey secret = generateSecret(salt);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters parameters = cipher.getParameters();
        byte iv[] = parameters.getParameterSpec(IvParameterSpec.class).getIV();
        document.setIv(iv);
        byte[] ciphertext = cipher.doFinal(document.getPayload());
        document.setEncrypted(true);
        document.setPayload(ciphertext);
        return document;
    }

    @Override
    public Document decrypt(Document document) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey secret = generateSecret(document.getSalt());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(document.getIv()));
        byte[] cleartext = cipher.doFinal(document.getPayload());
        document.setPayload(cleartext);
        document.setEncrypted(false);
        return document;
    }
}
