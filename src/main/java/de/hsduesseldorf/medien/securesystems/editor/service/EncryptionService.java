package de.hsduesseldorf.medien.securesystems.editor.service;


import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.DocumentEncryptor;

import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;

public class EncryptionService {

    DocumentEncryptor encryptor;

    public EncryptionService(DocumentEncryptor encryptor) {
        this.encryptor = encryptor;
    }


    public Document encrypt(Document document) throws GeneralSecurityException {
        return null;
    }

    public Document decrypt(Document document) throws GeneralSecurityException {
        return null;
    }
}
