package de.hsduesseldorf.medien.securesystems.editor.model;

import de.hsduesseldorf.medien.securesystems.editor.model.properties.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.Padding;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

@XmlRootElement
public class Document {

    private Date lastModified;

    private CipherName cipherName;

    private BlockMode blockMode;

    private Padding padding;

    private Integer blockSize;

    private Integer payloadLength;

    private byte[] payload;

    private File file;


    public Document(Date lastModified, CipherName cipherName, BlockMode blockMode, Padding padding, Integer blockSize, Integer payloadLength, byte[] payload, File file) {
        this.lastModified = lastModified;
        this.cipherName = cipherName;
        this.blockMode = blockMode;
        this.padding = padding;
        this.blockSize = blockSize;
        this.payloadLength = payloadLength;
        this.payload = payload;
        this.file = file;
    }

    public Document(Date lastModified, Options options, Integer blockSize, Integer payloadLength, byte[] payload, File file) {
        this.lastModified = lastModified;
        this.cipherName = options.cipherName;
        this.blockMode = options.blockMode;
        this.padding = options.padding;
        this.blockSize = blockSize;
        this.payloadLength = payloadLength;
        this.payload = payload;
        this.file = file;
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

    public void setCipherName(CipherName cipherName) {
        this.cipherName = cipherName;
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

    public Integer getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
    }

    public Integer getPayloadLength() {
        return payloadLength;
    }

    public void setPayloadLength(Integer payloadLength) {
        this.payloadLength = payloadLength;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @XmlTransient
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Options getOptions() {
        return new Options(this.cipherName, this.blockMode, this.padding);
    }

    public void setOptions(Options options) {
        this.cipherName = options.getCipherName();
        this.blockMode = options.getBlockMode();
        this.padding = options.getPadding();
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