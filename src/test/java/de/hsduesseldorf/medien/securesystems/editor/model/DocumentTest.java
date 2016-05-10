package de.hsduesseldorf.medien.securesystems.editor.model;

import de.hsduesseldorf.medien.securesystems.editor.options.CipherName;
import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentTest {

    @Test
    public void test() throws Exception {
        Document document = new Document();
        document.setCipherName(CipherName.AES);
        String expected = "Hallo Welt";
        document.setPayload(expected.getBytes());
        document.setLastModified(new Date());
        File file = new File("target/text.xml");
        JAXB.marshal(document,file);
        Document readFromFile = JAXB.unmarshal(file,Document.class);
        assertEquals(document,readFromFile);
    }

}