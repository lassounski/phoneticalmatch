package com.delaru.phoneticalmatch;

import java.io.File;
import java.util.HashSet;
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
    private Set<String> words;
    private File dictionary;

    @Before
    public void setUp() {
        words = new HashSet<>();
        words.add("1ton#");
        words.add("brief");
        words.add("soon");

        ClassLoader classLoader = getClass().getClassLoader();
        dictionary = new File(classLoader.getResource("word_dict.txt").getFile());
    }

    @Test
    public void shouldLoadWordsFromTextFile() {
        phoneticalMatcher = new PhoneticalMatcher(words, dictionary);

        Set<String> dictionary = phoneticalMatcher.getDictionary();

        assertThat(dictionary.size(), is(equalTo(11)));
        assertThat(dictionary, hasItems("son", "angel", "Engel", "Braev"));
    }

    @Test
    public void shouldNormalizeAllWordsInDictionary() {
        phoneticalMatcher = new PhoneticalMatcher(words, dictionary);
        phoneticalMatcher.normalize();

        Set<String> dictionary = phoneticalMatcher.getDictionary();

        assertThat(dictionary.size(), is(equalTo(9)));
        assertThat(dictionary, hasItems("sn", "angl", "engl", "brv", "dn", "g", "gl", "tm","tn"));
    }
    
    @Test
    public void shouldNormalizeAllArgumentWords() {
        phoneticalMatcher = new PhoneticalMatcher(words, dictionary);
        phoneticalMatcher.normalize();

        Set<String> argumentWords = phoneticalMatcher.getArgumentWords();

        assertThat(argumentWords.size(), is(equalTo(3)));
        assertThat(argumentWords, hasItems("tn", "brf", "sn"));
    }

}
