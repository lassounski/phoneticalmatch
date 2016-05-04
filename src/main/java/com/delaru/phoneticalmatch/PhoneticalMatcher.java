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
import java.util.stream.Collectors;

/**
 *
 * @author delaru
 */
public class PhoneticalMatcher {

    private Set<String> argumentWords;
    private Set<String> dictionary;
    private final File dictionaryFile;

    PhoneticalMatcher(Set<String> words, File dictionary) {
        this.argumentWords = words;
        this.dictionaryFile = dictionary;
        loadWordsFromFile();
    }

    public Set<String> getDictionary() {
        return dictionary;
    }

    public Set<String> getArgumentWords() {
        return argumentWords;
    }

    private void loadWordsFromFile() {
        try {
            dictionary = new HashSet<>(Files.readAllLines(dictionaryFile.toPath()));
        } catch (IOException ex) {
            Logger.getLogger(PhoneticalMatcher.class.getName()).log(Level.SEVERE, "There was an error while reading the dictionary file", ex);
            System.exit(-1);
        }
    }

    public void normalize() {
        LexicalNormalizer normalizer = new LexicalNormalizer();

        dictionary = dictionary.stream()
                .map(word -> normalizer.normalize(word))
                .collect(Collectors.toSet());
        argumentWords = argumentWords.stream()
                .map(word -> normalizer.normalize(word))
                .collect(Collectors.toSet());
    }

}
