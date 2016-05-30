package de.hsduesseldorf.medien.securesystems.editor.model;

import de.hsduesseldorf.medien.securesystems.editor.model.properties.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.Padding;

public class Options {

    CipherName cipherName;
    Integer keySize;
    BlockMode blockMode;
    Padding padding;

    public Options(CipherName cipherName, BlockMode blockMode, Padding padding, Integer keySize) {
        this.cipherName = cipherName;
        this.blockMode = blockMode;
        this.padding = padding;
        this.keySize = keySize;
    }

    public Options() {
        // default
    }


    public CipherName getCipherName() {
        return cipherName;
    }

    public void setCipherName(CipherName cipherName) {
        this.cipherName = cipherName;
    }

    public Integer getKeySize() {
        return keySize;
    }

    public void setKeySize(Integer keySize) {
        this.keySize = keySize;
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

    @Override
    public String toString() {
        return "Options{" +
                "cipherName=" + cipherName +
                ", keySize=" + keySize +
                ", blockMode=" + blockMode +
                ", padding=" + padding +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Options options = (Options) o;

        if (cipherName != options.cipherName) return false;
        if (keySize != null ? !keySize.equals(options.keySize) : options.keySize != null) return false;
        if (blockMode != options.blockMode) return false;
        return padding == options.padding;

    }

    @Override
    public int hashCode() {
        int result = cipherName != null ? cipherName.hashCode() : 0;
        result = 31 * result + (keySize != null ? keySize.hashCode() : 0);
        result = 31 * result + (blockMode != null ? blockMode.hashCode() : 0);
        result = 31 * result + (padding != null ? padding.hashCode() : 0);
        return result;
    }
}
