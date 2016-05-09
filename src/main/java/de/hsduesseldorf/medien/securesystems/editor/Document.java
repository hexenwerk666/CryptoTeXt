package de.hsduesseldorf.medien.securesystems.editor;

import de.hsduesseldorf.medien.securesystems.editor.options.CipherMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherType;
import de.hsduesseldorf.medien.securesystems.editor.options.Padding;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Date;

/**
 * model of a xml document
 */
@XmlRootElement
public class Document {

    private Date lastModified;

    private CipherType cipherType;

    private CipherMode cipherMode;

    private Padding padding;

    private Integer length;

    private byte[] payload;

    @Deprecated
    public Document(Date lastModified, CipherType cipherType, byte[] payload) {
        this.lastModified = lastModified;
        this.cipherType = cipherType;
        this.payload = payload;
    }

    public Document(Date lastModified, CipherType cipherType, CipherMode cipherMode, Padding padding, Integer length, byte[] payload) {
        this.lastModified = lastModified;
        this.cipherType = cipherType;
        this.cipherMode = cipherMode;
        this.padding = padding;
        this.length = length;
        this.payload = payload;
    }

    public Document() {
        // default
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public CipherType getCipherType() {
        return cipherType;
    }

    public CipherMode getCipherMode() {
        return cipherMode;
    }

    public void setCipherMode(CipherMode cipherMode) {
        this.cipherMode = cipherMode;
    }

    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setCipherType(CipherType cipherType) {
        this.cipherType = cipherType;
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
        if (cipherType != document.cipherType) return false;
        return Arrays.equals(payload, document.payload);

    }

    @Override
    public int hashCode() {
        int result = lastModified != null ? lastModified.hashCode() : 0;
        result = 31 * result + (cipherType != null ? cipherType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(payload);
        return result;
    }
}
