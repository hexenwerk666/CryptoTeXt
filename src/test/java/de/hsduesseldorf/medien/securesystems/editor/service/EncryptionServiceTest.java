package de.hsduesseldorf.medien.securesystems.editor.service;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.options.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.options.Padding;
import org.junit.Test;

import java.util.Date;

public class EncryptionServiceTest {

    @Test
    public void encryptAES() throws Exception {
        Document document = new Document(new Date(), CipherName.AES, "test".getBytes());
        Document encryptedDocument = EncryptionService.encryptAES(document,"test", CipherName.AES, BlockMode.CBC, Padding.PKCS5PADDING);
        System.out.println(new String(encryptedDocument.getPayload()));
    }

}