package com.delaru.phoneticalmatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author delaru
 */
public class MatchableString {

    private final String value;
    private List<Set<Character>> equivalentCharacters;

    MatchableString(String word) {
        this.value = word;
        initializeEquivalentCharacters();
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + value.length();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MatchableString)) {
            return false;
        }
        if (this.value.length() != ((MatchableString) obj).getValue().length()) {
            return false;
        }
        
        final MatchableString other = (MatchableString) obj;
        char[] thisCharacters = value.toCharArray();
        char[] otherCharacters = other.getValue().toCharArray();
        boolean equivalentStrings = true;

        for (int lengthIndex = 0; lengthIndex < value.length(); lengthIndex++) {
            if (thisCharacters[lengthIndex] != otherCharacters[lengthIndex]) {
                for (Set<Character> equivalentCharacter : equivalentCharacters) {
                    if (equivalentCharacter.contains(thisCharacters[lengthIndex])) {
                        if (!equivalentCharacter.contains(otherCharacters[lengthIndex])) {
                            equivalentStrings = false;
                        }
                    }
                }
            }
        }
        return equivalentStrings;
    }

    @Override
    public String toString() {
        return value;
    }

    private void initializeEquivalentCharacters() {
        equivalentCharacters = new ArrayList<>();
        equivalentCharacters.add(addCharactersToList('a', 'e', 'i', 'o', 'u'));
        equivalentCharacters.add(addCharactersToList('c', 'g', 'j', 'k', 'q', 's', 'x', 'y', 'z'));
        equivalentCharacters.add(addCharactersToList('b', 'f', 'p', 'v', 'w'));
        equivalentCharacters.add(addCharactersToList('d', 't'));
        equivalentCharacters.add(addCharactersToList('m', 'n'));

    }

    private Set<Character> addCharactersToList(Character... characters) {
        Set<Character> characterSet = new HashSet<>();
        characterSet.addAll(Arrays.asList(characters));
        return characterSet;
    }

}
