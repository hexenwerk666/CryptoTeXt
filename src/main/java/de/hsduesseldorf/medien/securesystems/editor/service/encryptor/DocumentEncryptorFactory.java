package de.hsduesseldorf.medien.securesystems.editor.service.encryptor;

import de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl.PBEDocumentEncryptor;

public class DocumentEncryptorFactory {

    public DocumentEncryptor getInstance(String type, char[] password) {
        switch (type) {
            case "AES":
                return new PBEDocumentEncryptor(password, 256, "AES/CBC/PKCS5Padding", "PBEWithSHA256And256BitAES-CBC-BC", false);
            case "DES":
                return new PBEDocumentEncryptor(password, 64, "PBEWithSHAAnd3KeyTripleDES", "PBEWithSHAAnd3KeyTripleDES", true);
            case "ARC4":
                return new PBEDocumentEncryptor(password, 128, "ARC4", "PBEWithSHAAnd128BitRC4", true);
            default:
                return null;
        }
    }

}
