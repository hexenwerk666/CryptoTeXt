package de.hsduesseldorf.medien.securesystems.editor.service;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherType;
import de.hsduesseldorf.medien.securesystems.editor.options.Padding;
import de.hsduesseldorf.medien.securesystems.editor.service.EncryptionService;
import org.junit.Test;

import java.util.Date;

public class EncryptionServiceTest {

    @Test
    public void encryptAES() throws Exception {
        Document document = new Document(new Date(), CipherType.AES, "test".getBytes());
        Document encryptedDocument = EncryptionService.encryptAES(document,"test",CipherType.AES, CipherMode.CBC, Padding.PKCS5PADDING);
        System.out.println(new String(encryptedDocument.getPayload()));
    }

}