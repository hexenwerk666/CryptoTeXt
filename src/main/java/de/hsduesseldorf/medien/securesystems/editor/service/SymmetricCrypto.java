package de.hsduesseldorf.medien.securesystems.editor.service;

import de.hsduesseldorf.medien.securesystems.editor.options.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.options.Padding;

public class SymmetricCrypto {

    private BlockMode blockMode;
    private CipherName cipherName;
    private Padding padding;

    public SymmetricCrypto(BlockMode blockMode, CipherName cipherName, Padding padding) {
        throw new UnsupportedOperationException();
    }

    public SymmetricCrypto(BlockMode blockMode) {
        this.blockMode = blockMode;
    }

    public byte[] encrypt() {
        return null;
    }

    public byte[] decrypt() {
        return null;
    }
}