package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.DocumentEncryptor;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;

public class ARC4DocumentEncryptor implements DocumentEncryptor {
    static final int IT_COUNT = 65536;
    static final int KEY_SIZE = 128;

    char[] password;

    public ARC4DocumentEncryptor(char[] password) {
        this.password = password;
    }

    SecretKey generateSecret(byte[] salt) throws GeneralSecurityException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA128");
        KeySpec spec = new PBEKeySpec(this.password, salt, IT_COUNT, KEY_SIZE);
        SecretKey temp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(temp.getEncoded(), "AES");
        return secret;
    }

    @Override
    public Document encrypt(Document document) throws GeneralSecurityException {
        return null;
    }

    @Override
    public Document decrypt(Document document) throws GeneralSecurityException {
        return null;
    }
}
