package com.delaru.phoneticalmatch;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author delaru
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        validateArguments(args);

        Set<String> words = new HashSet<String>();

        for (int argsIndex = 0; argsIndex < args.length - 1; argsIndex++) {
            words.add(args[argsIndex]);
        }

        String filePath = args[args.length - 1];
        File dictionary = new File(filePath);
        PhoneticalMatcher phoneticalMatcher = new PhoneticalMatcher(words, dictionary);
        
        print(phoneticalMatcher.getMatchedWords());
    }

    private static void validateArguments(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid parameters count: (" + args.length + ") parameter passed to the program\n");
            System.out.println("The program accepts any number of words to be phonetically matched in a line and the last parameter is the path to the .txt file that has the dictionary to be matched.\n");
            System.out.println("Ex: mvn exec:java -Dexec.args=\"1ton# brief soon word_dict.txt\"");
            System.exit(-1);
        }

        String filePath = args[args.length - 1];
        File dictionary = new File(filePath);

        if (!dictionary.isFile()) {
            System.out.println("The last parameter (" + filePath + ") should be a file");
            System.exit(-1);
        } else {
            int lastDotIndex = dictionary.getName().lastIndexOf(".");
            String fileExtension = dictionary.getName().substring(lastDotIndex + 1);
            if (!"txt".equalsIgnoreCase(fileExtension)) {
                System.out.println("The last parameter (" + filePath + ") should be a .txt file");
                System.exit(-1);
            }
        }
    }

    private static void print(Map<String, List<String>> matchedWords) {
        StringBuilder sb = new StringBuilder("\n");
        
        matchedWords.forEach((argument, dictionaryWords) -> {
            sb.append(argument).append(":");
            dictionaryWords.stream().forEach((dictionaryWord) -> {
                sb.append(" ").append(dictionaryWord).append(",");
            });
            sb.deleteCharAt(sb.length()-1).append("\n");
        });
        
        System.out.println(sb.toString());
    }

}
