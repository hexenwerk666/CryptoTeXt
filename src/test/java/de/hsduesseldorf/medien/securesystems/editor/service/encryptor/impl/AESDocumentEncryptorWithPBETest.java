package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;

import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESDocumentEncryptorWithPBETest {

    private static Logger LOG = LoggerFactory.getLogger(AESDocumentEncryptorWithPBETest.class);
    static char[] PASSWORD = "test".toCharArray();
    private static byte[] TEST_MESSAGE = new byte[] {
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 };

    AESDocumentEncryptorWithPBE cut;

    @Before
    public void setup() throws Exception {
        cut = new AESDocumentEncryptorWithPBE(PASSWORD);
    }


    @Test
    public void encrypt() throws Exception {
        Document document = new Document();
        document.setPayload(TEST_MESSAGE);
        document.setEncrypted(false);
        cut.encrypt(document);
        Assert.assertNotEquals(new String(TEST_MESSAGE),new String(document.getPayload()));
    }

    @Test
    public void decrypt() throws Exception {
        Document document = new Document();
        document.setPayload(TEST_MESSAGE);
        document.setEncrypted(false);
        document = cut.encrypt(document);
        document = cut.decrypt(document);
        Assert.assertEquals(new String(TEST_MESSAGE),new String(document.getPayload()));
    }

}