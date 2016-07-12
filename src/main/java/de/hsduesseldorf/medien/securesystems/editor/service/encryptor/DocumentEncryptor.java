package de.hsduesseldorf.medien.securesystems.editor.service.encryptor;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;

import java.security.GeneralSecurityException;

public interface DocumentEncryptor {

    public abstract Document encrypt(Document document) throws GeneralSecurityException;

    public abstract Document decrypt(Document document) throws GeneralSecurityException;
}
