package de.hsduesseldorf.medien.securesystems.editor.model.properties;


import java.util.Arrays;
import java.util.List;

/**
 * supported ciphers
 */
public enum CipherName {

    Plaintext(new BlockMode[0], new Padding[0], new Integer[0]),
    //AES(BlockMode.values(), Padding.values(), new Integer[]{128, 192, 256}),
    DES(BlockMode.values(), Padding.values(), new Integer[]{64}),
    ARC4(new BlockMode[0], new Padding[0], new Integer[]{128});

    List<BlockMode> providedBlockModi;
    List<Padding> providedPaddings;
    List<Integer> providedKeySizes;

    CipherName(BlockMode[] blockModi, Padding[] paddings, Integer[] providedKeySizes) {
        this.providedBlockModi = Arrays.asList(blockModi);
        this.providedPaddings = Arrays.asList(paddings);
        this.providedKeySizes = Arrays.asList(providedKeySizes);
    }

    public List<BlockMode> getProvidedBlockModi() {
        return providedBlockModi;
    }

    public List<Padding> getProvidedPaddings() {
        return providedPaddings;
    }

    public List<Integer> getProvidedKeySizes() {
        return providedKeySizes;
    }
}
