package de.hsduesseldorf.medien.securesystems.editor.service.encryptor.impl;

import de.hsduesseldorf.medien.securesystems.editor.exception.InvalidChecksum;
import de.hsduesseldorf.medien.securesystems.editor.model.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PBEDocumentEncryptorTest {

    static char[] PASSWORD = "test".toCharArray();
    static Logger LOG = LoggerFactory.getLogger(PBEDocumentEncryptorTest.class);
    static byte[] TEST_MESSAGE = new byte[]{
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    List<PBEDocumentEncryptor> cut;

    @Before
    public void setup() throws Exception {
        // generate instances of all supported cipher and pbe combinations
        cut = new ArrayList<>();
        cut.add(new PBEDocumentEncryptor(PASSWORD, 256, "AES/CBC/PKCS7Padding", "PBEWithSHA256And256BitAES-CBC-BC", false));
        cut.add(new PBEDocumentEncryptor(PASSWORD, 64, "PBEWithSHAAnd3KeyTripleDES", "PBEWithSHAAnd3KeyTripleDES", true));
        cut.add(new PBEDocumentEncryptor(PASSWORD, 128, "ARC4", "PBEWithSHAAnd128BitRC4", true));
    }

    @Test
    public void encrypt() throws Exception {
        for (PBEDocumentEncryptor p : cut) {
            Document document = new Document();
            document.setPayload(TEST_MESSAGE);
            document.setPayloadLength(TEST_MESSAGE.length);
            document.setEncrypted(false);
            document = p.encrypt(document);
            LOG.debug("cleartext lenght:\t" + TEST_MESSAGE.length);
            LOG.debug("ciphertext length:\t" + document.getPayload().length);
            Assert.assertNotEquals(new String(TEST_MESSAGE), new String(document.getPayload()));
        }

    }

    @Test
    public void decrypt() throws Exception {
        for (PBEDocumentEncryptor p : cut) {
            Document document = new Document();
            document.setPayload(TEST_MESSAGE);
            document.setPayloadLength(TEST_MESSAGE.length);
            document.setEncrypted(false);
            document = p.encrypt(document);
            document = p.decrypt(document);
            LOG.debug("cleartext lenght:\t" + TEST_MESSAGE.length);
            LOG.debug("ciphertext length:\t" + document.getPayload().length);
            Assert.assertEquals(new String(TEST_MESSAGE), new String(document.getPayload()));
        }

    }

    @Test
    public void decrypt_invalid_checkup() throws Exception {
        for (PBEDocumentEncryptor p : cut) {
            Document document = new Document();
            document.setPayload(TEST_MESSAGE);
            document.setPayloadLength(TEST_MESSAGE.length);
            document.setEncrypted(false);
            document = p.encrypt(document);
            document.getPayload()[0] = 0x01;
            exception.expect(InvalidChecksum.class);
            p.decrypt(document);
        }

    }
}