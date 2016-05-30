package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.DocumentEncryptor;

public class ARC4DocumentEncryptor implements DocumentEncryptor {

    private Options options;

    public ARC4DocumentEncryptor(Options options) throws Exception {
        if(options.getCipherName()!= CipherName.ARC4) {
            throw new Exception("Invalid cipher name");
        }
        this.options = options;
    }

    @Override
    public Document encrypt(Document document) {
        return null;
    }

    @Override
    public Document decrypt(Document document) {
        return null;
    }
}
