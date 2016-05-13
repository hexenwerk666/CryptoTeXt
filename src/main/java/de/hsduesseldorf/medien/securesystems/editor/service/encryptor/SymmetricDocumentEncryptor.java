package de.hsduesseldorf.medien.securesystems.editor.service.encryptor;


import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;

public class SymmetricDocumentEncryptor extends DocumentEncryptor{

    public SymmetricDocumentEncryptor(Options options) {
        super(options);
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
