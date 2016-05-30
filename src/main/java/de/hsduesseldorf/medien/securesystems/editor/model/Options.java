package de.hsduesseldorf.medien.securesystems.editor.model;

import de.hsduesseldorf.medien.securesystems.editor.model.properties.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.Padding;

public class Options {

    CipherName cipherName;
    Integer blockSize;
    BlockMode blockMode;
    Padding padding;

    public Options(CipherName cipherName, BlockMode blockMode, Padding padding, Integer blockSize) {
        this.cipherName = cipherName;
        this.blockMode = blockMode;
        this.padding = padding;
        this.blockSize = blockSize;
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

    public Integer getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
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
                ", blockMode=" + blockMode +
                ", padding=" + padding +
                '}';
    }
}
