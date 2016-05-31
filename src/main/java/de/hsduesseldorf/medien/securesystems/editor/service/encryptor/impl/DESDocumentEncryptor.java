package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;


import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;
import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.DocumentEncryptor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.Arrays;


public class DESDocumentEncryptor implements DocumentEncryptor {

    private static final Logger LOG = LoggerFactory.getLogger(DESDocumentEncryptor.class);

    byte[] keyBytes = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};

    Options options;

    public DESDocumentEncryptor(Options options) {
        this.options = options;
    }

    @Override
    public byte[] encrypt(byte[] input) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        String optionsParam = options.getCipherName() + "/" + options.getBlockMode() + "/" + options.getPadding();
        SecretKeySpec key = new SecretKeySpec(keyBytes, options.getCipherName().name());
        Cipher cipher = Cipher.getInstance(optionsParam, "BC");
        LOG.debug("input : " + Hex.toHexString(input));
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, output, 0);
        ctLength += cipher.doFinal(output, ctLength);
        LOG.debug("cipher: " + Hex.toHexString(output) + " bytes: " + ctLength);
        return output;
    }

    @Override
    public byte[] decrypt(byte[] input, int lenght) throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        String optionsParam = options.getCipherName() + "/" + options.getBlockMode() + "/" + options.getPadding();
        SecretKeySpec key = new SecretKeySpec(keyBytes, options.getCipherName().name());


        int ctLength = input.length;

        Cipher cipher = Cipher.getInstance(optionsParam, "BC");

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] output = new byte[cipher.getOutputSize(ctLength)];

        int ptLength = cipher.update(input, 0, ctLength, output, 0);
        LOG.debug("cipher " + Hex.toHexString(input));
        cipher.doFinal(output, ptLength);
        LOG.debug("decypted " + Hex.toHexString(output));
        output = Arrays.copyOf(output, lenght);
        LOG.debug("remove padding " + Hex.toHexString(output));
        return output;
    }
}
