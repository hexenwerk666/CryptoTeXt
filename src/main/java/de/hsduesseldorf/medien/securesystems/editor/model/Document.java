package de.hsduesseldorf.medien.securesystems.editor.model;

import de.hsduesseldorf.medien.securesystems.editor.options.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherName;
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

    private CipherName cipherName;

    private BlockMode blockMode;

    private Padding padding;

    private Integer length;

    private byte[] payload;

    @Deprecated
    public Document(Date lastModified, CipherName cipherName, byte[] payload) {
        this.lastModified = lastModified;
        this.cipherName = cipherName;
        this.payload = payload;
    }

    public Document(Date lastModified, CipherName cipherName, BlockMode blockMode, Padding padding, Integer length, byte[] payload) {
        this.lastModified = lastModified;
        this.cipherName = cipherName;
        this.blockMode = blockMode;
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

    public CipherName getCipherName() {
        return cipherName;
    }

    public BlockMode getBlockMode() {
        return blockMode;
    }

    public void setBlockMode(BlockMode blockMode) {
        this.blockMode = blockMode;
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

    public void setCipherName(CipherName cipherName) {
        this.cipherName = cipherName;
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
        if (cipherName != document.cipherName) return false;
        return Arrays.equals(payload, document.payload);

    }

    @Override
    public int hashCode() {
        int result = lastModified != null ? lastModified.hashCode() : 0;
        result = 31 * result + (cipherName != null ? cipherName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(payload);
        return result;
    }
}
