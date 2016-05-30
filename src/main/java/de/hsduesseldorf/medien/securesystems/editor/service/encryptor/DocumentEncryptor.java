package de.hsduesseldorf.medien.securesystems.editor.service.encryptor;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.model.Options;

public interface DocumentEncryptor {

    public abstract Document encrypt(Document document);

    public abstract Document decrypt(Document document);
}
