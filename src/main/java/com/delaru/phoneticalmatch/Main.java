package com.delaru.phoneticalmatch;

import java.io.File;
import java.util.Scanner;

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
    }

    private static void validateArguments(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid parameters count: (" + args.length + ") parameter passed to the program\n");
            System.out.println("The program accepts any number of words to be phonetically matched in a line and the last parameter is the path to the .txt file that has the dictionary to be matched.\n");
            System.out.println("Ex: java -jar phoneticalmatch-0.1.0.jar word1 word2 dictionary.txt");
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

}
