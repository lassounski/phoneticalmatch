package com.delaru.phoneticalmatch;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author delaru
 */
public class MatchableStringTest {

    private final LexicalNormalizer lexicalNormalizer = new LexicalNormalizer();

    @Test
    public void shouldReturnEqualHashcodeForTwoObjectsWithTheSameStringLength() {
        MatchableString string1 = new MatchableString(lexicalNormalizer.normalize("Toooonnnnnnyyyyy"));
        MatchableString string2 = new MatchableString(lexicalNormalizer.normalize("Tom"));
        MatchableString string3 = new MatchableString(lexicalNormalizer.normalize("Tom"));
        MatchableString string4 = new MatchableString(lexicalNormalizer.normalize("Dom"));
        MatchableString string5 = new MatchableString(lexicalNormalizer.normalize("Mom"));
        MatchableString string6 = new MatchableString(lexicalNormalizer.normalize("Dat"));
        MatchableString string7 = new MatchableString(lexicalNormalizer.normalize("Unusual"));

        assertThat(string1.hashCode(), is(equalTo(string2.hashCode())));
        assertThat(string2.hashCode(), is(equalTo(string3.hashCode())));
        assertThat(string3.hashCode(), is(equalTo(string4.hashCode())));
        assertThat(string4.hashCode(), is(equalTo(string6.hashCode())));

        assertThat(string1.hashCode(), is(not(equalTo(string7.hashCode()))));
        assertThat(string2.hashCode(), is(not(equalTo(string7.hashCode()))));
        assertThat(string4.hashCode(), is(not(equalTo(string7.hashCode()))));
        assertThat(string5.hashCode(), is(not(equalTo(string7.hashCode()))));
        assertThat(string6.hashCode(), is(not(equalTo(string7.hashCode()))));
    }

    @Test
    public void shouldReturnTrueForTwoObjectsWithCharacterSimilarity() {
        MatchableString string1 = new MatchableString(lexicalNormalizer.normalize("Toooonnnnnnyyyyy"));
        MatchableString string2 = new MatchableString(lexicalNormalizer.normalize("Tom"));

        assertThat(string1, is(equalTo(string2)));
    }
    
    @Test
    public void shouldReturnFalseForTwoObjectsWithoutCharacterSimilarity() {
        MatchableString string1 = new MatchableString(lexicalNormalizer.normalize("Unusual"));
        MatchableString string2 = new MatchableString(lexicalNormalizer.normalize("Tom"));

        assertThat(string1, is(not(equalTo(string2))));
    
        string1 = new MatchableString(lexicalNormalizer.normalize("Bon"));
        string2 = new MatchableString(lexicalNormalizer.normalize("Tom"));
        
        assertThat(string1, is(not(equalTo(string2))));
    }
}
