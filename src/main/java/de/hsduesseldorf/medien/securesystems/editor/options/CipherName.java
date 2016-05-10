package de.hsduesseldorf.medien.securesystems.editor.options;

import java.util.Arrays;
import java.util.List;

/**
 * supported ciphers
 */
public enum CipherName {

    Plaintext(new BlockMode[0], new Padding[0]),
    AES(BlockMode.values(), Padding.values()),
    DES(BlockMode.values(), Padding.values());

    List<BlockMode> providedBlockModi;
    List<Padding> providedPaddings;

    CipherName(BlockMode[] blockModi, Padding[] paddings) {
        this.providedBlockModi = Arrays.asList(blockModi);
        this.providedPaddings = Arrays.asList(paddings);
    }

    public List<BlockMode> getProvidedBlockModi() {
        return providedBlockModi;
    }

    public List<Padding> getProvidedPaddings() {
        return providedPaddings;
    }
}
