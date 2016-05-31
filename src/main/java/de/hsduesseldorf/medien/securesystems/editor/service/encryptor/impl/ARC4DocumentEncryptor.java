package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.DocumentEncryptor;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

public class ARC4DocumentEncryptor implements DocumentEncryptor {

    private static Logger LOG = LoggerFactory.getLogger(ARC4DocumentEncryptor.class);

    byte[] keyBytes = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};

    @Override
    public Document encrypt(Document document) throws GeneralSecurityException {
        SecretKeySpec key = new SecretKeySpec(keyBytes, "BC");
        Cipher cipher = Cipher.getInstance("ARC4", "BC");
        byte[] input = document.getPayload();
        byte[] output = new byte[input.length];
        cipher.init(Cipher.ENCRYPT_MODE, key);
        int ctLenght = cipher.update(input, 0, input.length, output, 0);
        LOG.debug("input: " + Hex.toHexString(input));
        cipher.doFinal(output, ctLenght);
        LOG.debug("output: " + Hex.toHexString(output));
        document.setEncrypted(true);
        document.setPayload(output);
        return document;
    }

    @Override
    public Document decrypt(Document document) throws GeneralSecurityException {
        SecretKeySpec key = new SecretKeySpec(keyBytes, "BC");
        Cipher cipher = Cipher.getInstance("ARC4", "BC");
        byte[] input = document.getPayload();
        byte[] output = new byte[input.length];
        cipher.init(Cipher.DECRYPT_MODE, key);
        int ctLenght = cipher.update(input, 0, input.length, output, 0);
        LOG.debug("input: " + Hex.toHexString(input));
        cipher.doFinal(output, ctLenght);
        LOG.debug("output: " + Hex.toHexString(output));
        document.setEncrypted(false);
        document.setPayload(output);
        return document;
    }
}
