package de.hsduesseldorf.medien.securesystems.editor.service.encryptor;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface DocumentEncryptor {

    public abstract byte[] encrypt(byte[] input) throws GeneralSecurityException;

    public abstract byte[] decrypt(byte[] input, int lenght) throws GeneralSecurityException;

    default byte[] decrypt(byte[] input) throws GeneralSecurityException {
        return this.decrypt(input,input.length);
    }
}
