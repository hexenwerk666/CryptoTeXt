package de.hsduesseldorf.medien.securesystems.editor.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.File;
import java.security.SecureRandom;
import java.util.Date;

@XmlRootElement
public class Document {

    private static final byte[] DEFAULT_SALT = "salt".getBytes();

    private Date lastModified;

    private Options options;

    private Integer payloadLength;

    private byte[] salt;

    private byte[] iv;

    private byte[] payload;

    private Boolean isEncrypted;

    private File file;


    public Document(Date lastModified, Options options, Integer payloadLength, byte[] salt, byte[] iv, byte[] payload, File file) {
        this.lastModified = lastModified;
        this.options = options;
        this.payloadLength = payloadLength;
        this.salt = salt;
        this.iv = iv;
        this.payload = payload;
        this.file = file;
    }

    public Document(Date lastModified, Options options, Integer payloadLength, byte[] salt, byte[] payload, File file) {
        this(lastModified, options, payloadLength, salt, null, payload, file);
        this.iv = new byte[options.blockSize];
        new SecureRandom().nextBytes(iv);
    }

    public Document(Date lastModified, Options options, Integer payloadLength, byte[] payload, File file) {
        this(lastModified, options, payloadLength, DEFAULT_SALT, null, payload, file);
        this.iv = new byte[options.blockSize];
        new SecureRandom().nextBytes(iv);
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

    public Integer getPayloadLength() {
        return payloadLength;
    }

    public void setPayloadLength(Integer payloadLength) {
        this.payloadLength = payloadLength;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
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
        return this.options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Boolean getEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(Boolean encrypted) {
        isEncrypted = encrypted;
    }
}