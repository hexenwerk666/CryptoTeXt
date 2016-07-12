package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;

import de.hsduesseldorf.medien.securesystems.editor.exception.InvalidChecksum;
import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.service.SHA256Generator;
import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.DocumentEncryptor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PBEDocumentEncryptor implements DocumentEncryptor {

    static int iterCount = 65536;
    static Logger LOG = LoggerFactory.getLogger(PBEDocumentEncryptor.class);
    char[] password;
    int keySize;
    String cipherName;
    String pbeMethod;
    boolean streamMode;

    public PBEDocumentEncryptor(char[] password, int keySize, String cipherName, String pbeMethod, boolean streamMode) {
        this.password = password;
        this.keySize = keySize;
        this.cipherName = cipherName;
        this.pbeMethod = pbeMethod;
        this.streamMode = streamMode;
    }

    SecretKey generateSecret(byte[] salt) throws GeneralSecurityException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(pbeMethod,"BC");
        KeySpec spec = new PBEKeySpec(this.password, salt, iterCount, keySize);
        SecretKey secret = factory.generateSecret(spec);
        return secret;
    }

    @Override
    public Document encrypt(Document document) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        byte[] salt = new byte[256];
        new SecureRandom().nextBytes(salt);
        document.setSalt(salt);
        SecretKey secret = generateSecret(salt);
        Cipher cipher = Cipher.getInstance(cipherName, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters parameters = cipher.getParameters();
        if (!this.streamMode) {
            byte iv[] = parameters.getParameterSpec(IvParameterSpec.class).getIV();
            document.setIv(iv);
        }
        document.setHash(SHA256Generator.generateHash(document.getPayload()));
        byte[] ciphertext = cipher.doFinal(document.getPayload());
        document.setEncrypted(true);
        document.setPayload(ciphertext);
        return document;
    }

    @Override
    public Document decrypt(Document document) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey secret = generateSecret(document.getSalt());
        Cipher cipher = Cipher.getInstance(cipherName, "BC");

        if (!streamMode)
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(document.getIv()));
        else
            cipher.init(Cipher.DECRYPT_MODE, secret);

        byte[] cleartext = cipher.doFinal(document.getPayload());
        document.setPayload(cleartext);
        document.setEncrypted(false);

        if (!Arrays.equals(SHA256Generator.generateHash(cleartext), document.getHash()))
            throw new InvalidChecksum();

        return document;
    }
}

