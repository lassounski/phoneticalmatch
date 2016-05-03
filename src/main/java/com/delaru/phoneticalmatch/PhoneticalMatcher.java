/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delaru.phoneticalmatch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author delaru
 */
public class PhoneticalMatcher {

    private final Set<String> words;
    private final File dictionaryFile;
    private Set<String> dictionary;

    PhoneticalMatcher(Set<String> words, File dictionary) {
        this.words = words;
        this.dictionaryFile = dictionary;
        loadWordsFromFile();
    }

    Set<String> getDictionary() {
        return dictionary;
    }

    private void loadWordsFromFile() {
        try {
            dictionary = new HashSet<>(Files.readAllLines(dictionaryFile.toPath()));
        } catch (IOException ex) {
            Logger.getLogger(PhoneticalMatcher.class.getName()).log(Level.SEVERE, "There was an error while reading the dictionary file", ex);
            System.exit(-1);
        }
    }

}
