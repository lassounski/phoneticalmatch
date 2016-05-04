
package com.delaru.phoneticalmatch;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author delaru
 */
public class LexicalNormalizerTest {

    private LexicalNormalizer lexicalNormalizer = new LexicalNormalizer();

    @Test
    public void shouldRemoveNonAlphabeticCharactersFromWord() {
        String superman = lexicalNormalizer.removeNonAlphabetical("1super$,man");

        assertThat(superman, is(equalTo("superman")));
    }

    @Test
    public void shouldRemoveSomeLettersAfterTheFirstOne() {
        String superman = lexicalNormalizer.removeSpecialLettersAfterFirstOne("1suUphHeEruU$,maAiInwWyY");
        String az = lexicalNormalizer.removeSpecialLettersAfterFirstOne("aeiouyzw");

        assertThat(superman, is(equalTo("1spr$,mn")));
        assertThat(az, is(equalTo("az")));
    }
    
    @Test
    public void shouldTreatRepeatedLettersAsOnlyOne() {
        String superman = lexicalNormalizer.transformRepeatedLettersInOnlyOneLetter("suppperrrr,man");
        String tony = lexicalNormalizer.transformRepeatedLettersInOnlyOneLetter("toooonnnnnnyyyyyyyy");

        assertThat(superman, is(equalTo("super,man")));
        assertThat(tony, is(equalTo("tony")));
    }
    
    @Test
    public void shouldNormalizeAWord() {
        String normalizedWord1 = lexicalNormalizer.normalize("Toooonnnnyyyyyy");
        String normalizedWord2 = lexicalNormalizer.normalize("124&!*($DashiduQWUhD18912hd9HSCAS98eh1d(*&#!");
        String normalizedWord3 = lexicalNormalizer.normalize("*3Kirill11");
        String normalizedWord4 = lexicalNormalizer.normalize("Cihau123CANO 1od2ij1 O DSJA");
        String normalizedWord5 = lexicalNormalizer.normalize("HSCAS98eh1d");
        
        assertThat(normalizedWord1, is(equalTo("tn")));
        assertThat(normalizedWord2, is(equalTo("dsdqdscsd")));
        assertThat(normalizedWord3, is(equalTo("krl")));
        assertThat(normalizedWord4, is(equalTo("cndjdsj")));
        assertThat(normalizedWord5, is(equalTo("hscsd")));
    }
}
