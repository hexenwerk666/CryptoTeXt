package de.hsduesseldorf.medien.securesystems.editor.service.encryptor;


import de.hsduesseldorf.medien.securesystems.editor.model.Options;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.BlockMode;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.CipherName;
import de.hsduesseldorf.medien.securesystems.editor.model.properties.Padding;

import javax.crypto.Cipher;
import java.security.spec.KeySpec;

public class CipherFactory {

    public static Cipher getInstance(Options options, KeySpec key, Integer cryptoMod) {
        return getInstance(options.getCipherName(), options.getBlockMode(), options.getPadding(), key, cryptoMod);
    }

    public static Cipher getInstance(CipherName cipherName, BlockMode blockMode, Padding padding, KeySpec key, Integer cryptoMode) {
        return null;
    }
}
