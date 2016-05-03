package de.hsduesseldorf.medien.securesystems.editor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Document {

    private Date lastModified;

    private CipherOptions cipherOptions;

    private byte[] payload;

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public CipherOptions getCipherOptions() {
        return cipherOptions;
    }

    public void setCipherOptions(CipherOptions cipherOptions) {
        this.cipherOptions = cipherOptions;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }
}
