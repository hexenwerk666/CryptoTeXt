package de.hsduesseldorf.medien.securesystems.editor;

import org.junit.Test;

import javax.xml.bind.JAXB;
import java.io.File;
import java.util.Date;

public class DocumentTest {

    @Test
    public void test() throws Exception {
        Document document = new Document();
        document.setCipherOptions(CipherOptions.AES);
        document.setPayload(new byte[] {1,2,3,4,5,6,7});
        document.setLastModified(new Date());
        File file = new File("target/text.xml");
        JAXB.marshal(document,file);
    }

}