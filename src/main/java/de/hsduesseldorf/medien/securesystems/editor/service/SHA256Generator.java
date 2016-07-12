package de.hsduesseldorf.medien.securesystems.editor.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Security;

public class SHA256Generator {

    public static byte[] generateHash(byte[] message) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = MessageDigest.getInstance("SHA-256", "BC");
        return md.digest(message);
    }

}
