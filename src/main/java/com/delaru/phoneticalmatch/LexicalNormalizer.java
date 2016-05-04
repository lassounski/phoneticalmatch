
package com.delaru.phoneticalmatch;

/**
 *
 * @author delaru
 */
public class LexicalNormalizer {

    protected String removeNonAlphabetical(String word) {
        return word.replaceAll("(\\W|\\d)", "");
    }

    protected String removeSpecialLettersAfterFirstOne(String word) {
        return word.substring(0, 1) + word.substring(1).replaceAll("(a|A|e|E|i|I|o|O|u|U|h|H|w|W|y|Y)", "");
    }

    /**
     * Will remove duplicate letters
     * Does not remove letters with different casing
     * @param word
     * @return 
     */
    protected String transformRepeatedLettersInOnlyOneLetter(String word) {
        return word.chars()
                .mapToObj(character -> String.valueOf((char) character))
                .reduce((acc, character) -> {
                    if (!acc.endsWith(character)) {
                        acc += character;
                    }
                    return acc;
                })
                .get();
    }

    public String normalize(String word) {
        word = word.toLowerCase();
        word = removeNonAlphabetical(word);
        word = removeSpecialLettersAfterFirstOne(word);
        return transformRepeatedLettersInOnlyOneLetter(word);
    }

}
