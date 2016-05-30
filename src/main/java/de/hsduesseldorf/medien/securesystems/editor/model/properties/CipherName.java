package de.hsduesseldorf.medien.securesystems.editor.model.properties;


import java.util.Arrays;
import java.util.List;

/**
 * supported ciphers
 */
public enum CipherName {

    Plaintext(new BlockMode[0], new Padding[0], new Integer[0]),
    AES(BlockMode.values(), Padding.values(), new Integer[]{128, 256}),
    DES(BlockMode.values(), Padding.values(), new Integer[]{64});

    List<BlockMode> providedBlockModi;
    List<Padding> providedPaddings;
    List<Integer> providedBlockSizes;

    CipherName(BlockMode[] blockModi, Padding[] paddings, Integer[] providedBlockSizes) {
        this.providedBlockModi = Arrays.asList(blockModi);
        this.providedPaddings = Arrays.asList(paddings);
        this.providedBlockSizes = Arrays.asList(providedBlockSizes);
    }

    public List<BlockMode> getProvidedBlockModi() {
        return providedBlockModi;
    }

    public List<Padding> getProvidedPaddings() {
        return providedPaddings;
    }

    public List<Integer> getProvidedBlockSizes() {
        return providedBlockSizes;
    }
}
