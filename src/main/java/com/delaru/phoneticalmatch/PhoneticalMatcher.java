package com.delaru.phoneticalmatch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author delaru
 */
public class PhoneticalMatcher {

    private final Map<MatchableString, List<String>> argumentWords;
    private final Map<MatchableString, List<String>> dictionary;
    private Map<String, List<String>> matchedWords;

    PhoneticalMatcher(Set<String> words, File dictionaryFile) {
        Set<String> dictionaryWords = loadWordsFromDictionaryFile(dictionaryFile);
        
        dictionary = normalize(dictionaryWords);
        argumentWords = normalize(words);
        
        matchWords();
    }

    public Map<String, List<String>> getMatchedWords() {
        return matchedWords;
    }

    private Set<String> loadWordsFromDictionaryFile(File dictionaryFile) {
        try {
            return new HashSet<>(Files.readAllLines(dictionaryFile.toPath()));
        } catch (IOException ex) {
            Logger.getLogger(PhoneticalMatcher.class.getName()).log(Level.SEVERE, "There was an error while reading the dictionary file", ex);
            System.exit(-1);
        }
        return new HashSet();
    }

    /**
     * Transforms a set of regular Strings into a Map which is indexed by the
     * normalized form of the word and contains all the strings that fit into
     * that word category
     *
     * @param wordSet
     * @return
     */
    private Map<MatchableString, List<String>> normalize(Set<String> wordSet) {
        Map<MatchableString, List<String>> normalizedMap = new HashMap<>();
        LexicalNormalizer normalizer = new LexicalNormalizer();

        wordSet.stream().forEach((word) -> {
            MatchableString normalizedWord = new MatchableString(normalizer.normalize(word));

            if (normalizedMap.containsKey(normalizedWord)) {
                normalizedMap.get(normalizedWord).add(word);
            } else {
                List<String> words = new ArrayList<>();

                words.add(word);
                normalizedMap.put(normalizedWord, words);
            }
        });
        return normalizedMap;
    }

    private void matchWords() {
        matchedWords = new HashMap<>();
        
        argumentWords.forEach((matchableArgument,arguments) -> {
            List<String> dictionaryWords = dictionary.get(matchableArgument);
            
            arguments.stream().forEach((argument) -> {
                matchedWords.put(argument, dictionaryWords);
            });
        });
    }
}
