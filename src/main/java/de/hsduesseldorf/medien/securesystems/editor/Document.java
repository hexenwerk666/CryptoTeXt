package de.hsduesseldorf.medien.securesystems.editor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (lastModified != null ? !lastModified.equals(document.lastModified) : document.lastModified != null)
            return false;
        if (cipherOptions != document.cipherOptions) return false;
        return Arrays.equals(payload, document.payload);

    }

    @Override
    public int hashCode() {
        int result = lastModified != null ? lastModified.hashCode() : 0;
        result = 31 * result + (cipherOptions != null ? cipherOptions.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(payload);
        return result;
    }
}
