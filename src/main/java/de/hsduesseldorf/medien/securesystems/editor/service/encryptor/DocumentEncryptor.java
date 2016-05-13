package de.hsduesseldorf.medien.securesystems.editor.service.encryptor;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;

public abstract class DocumentEncryptor {

    protected Options options;

    public DocumentEncryptor(Options options) {
        this.options = options;
    }

    public abstract Document encrypt(Document document);

    public abstract Document decrypt(Document document);
}
