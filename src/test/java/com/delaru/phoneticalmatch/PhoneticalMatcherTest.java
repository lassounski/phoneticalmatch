package com.delaru.phoneticalmatch;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author delaru
 */
public class PhoneticalMatcherTest {

    private PhoneticalMatcher phoneticalMatcher;

    @Before
    public void setUp() {
        Set<String> words = new HashSet<>();
        words.add("1ton#");
        words.add("brief");
        words.add("soon");

        ClassLoader classLoader = getClass().getClassLoader();
        File dictionaryFile = new File(classLoader.getResource("word_dict.txt").getFile());

        phoneticalMatcher = new PhoneticalMatcher(words, dictionaryFile);
    }

    @Test
    public void shouldMatchWordsInTheDictionary() {
        Map<String, List<String>> matchedWords = phoneticalMatcher.getMatchedWords();
        assertThat(matchedWords.size(), is(equalTo(3)));

        List<String> matchedWordsForString = matchedWords.get("1ton#");

        assertThat(matchedWordsForString.size(), is(equalTo(3)));
        assertThat(matchedWordsForString, hasItems("Don", "Tom", "Tooonnnnyyyy"));

        matchedWordsForString = matchedWords.get("brief");

        assertThat(matchedWordsForString.size(), is(equalTo(2)));
        assertThat(matchedWordsForString, hasItems("brave", "Braev"));

        matchedWordsForString = matchedWords.get("soon");

        assertThat(matchedWordsForString.size(), is(equalTo(2)));
        assertThat(matchedWordsForString, hasItems("son", "sunny"));
    }
}
