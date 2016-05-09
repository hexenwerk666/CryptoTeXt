package de.hsduesseldorf.medien.securesystems.editor.service;

import de.hsduesseldorf.medien.securesystems.editor.options.CipherMode;
import de.hsduesseldorf.medien.securesystems.editor.options.CipherType;
import de.hsduesseldorf.medien.securesystems.editor.options.Padding;

import javax.crypto.Cipher;

public class CipherFactory {

    private static final String SALT = "salty";

    public static Cipher getInstance(CipherType cipherType, CipherMode cipherMode, Padding padding) {
        return null;
    }

    private String optionsParam(CipherType cipherType, CipherMode cipherMode, Padding padding) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(cipherType)
                .append("/")
                .append(cipherMode)
                .append("/")
                .append(padding);

        return stringBuilder.toString();
    }
}
