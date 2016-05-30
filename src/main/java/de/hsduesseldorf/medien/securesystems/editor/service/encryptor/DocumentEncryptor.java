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

    public abstract Document encrypt(Document document) throws GeneralSecurityException;

    public abstract Document decrypt(Document document) throws GeneralSecurityException;
}
