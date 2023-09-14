import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Strings for output
        String wordBull = "bull";
        String wordBulls = "bulls";
        String wordCow = "cow";
        String wordCows = "cows";
        boolean gameWon = false;
        int turn = 1;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the length of the secret code (up to 36):");
        System.out.print("> ");
        int len = scanner.nextInt();
        len = validateNumSymbols(len);

        System.out.println("Input the number of possible symbols in the code (up to 36):");
        System.out.print("> ");
        int numSymbols = scanner.nextInt();
        numSymbols = validateNumSymbols(numSymbols);

        // Creating the secret code
        List<Character> symbols = new ArrayList<>();
        List<Character> randomSymbols = new ArrayList<>();

        for (int i = 0; i < numSymbols; i++) {
            symbols.add(getSymbol(i));
        }

        Collections.shuffle(symbols);
        randomSymbols.addAll(symbols.subList(0, len));

        StringBuilder secretCodeBuilder = new StringBuilder();
        for (char symbolChar : randomSymbols) {
            secretCodeBuilder.append(symbolChar);
        }

        String secretCode = secretCodeBuilder.toString();
        String hiddenSecretCode = secretCode.replaceAll(".", "*");
        System.out.println("The secret code is prepared: " + hiddenSecretCode + " (" + getSymbolRange(numSymbols) + ").");

        // Running the game
        while (!gameWon) {
            System.out.println("Okay, let's start a game!");
            System.out.println("Turn " + turn + ":");
            System.out.print("> ");
            String input = scanner.next();
            int bulls = 0;
            int cows = 0;

            char[] inputArray = String.format("%1$-" + len + "s", input.toLowerCase()).toCharArray();
            char[] codeArray = secretCode.toLowerCase().toCharArray();

            // Counting bulls and cows
            for (int i = 0; i < len; i++) {
                if (inputArray[i] == codeArray[i]) {
                    bulls++;
                } else if (secretCode.contains(String.valueOf(inputArray[i]))) {
                    cows++;
                }
            }

            if (bulls == len && cows == 0) {
                String word = (bulls == 1) ? wordBull : wordBulls;
                System.out.println("Grade: " + bulls + " " + word);
                System.out.println("Congratulations! You guessed the secret code.");
                gameWon = true;
            } else {
                String useCow = (cows == 1) ? wordCow : wordCows;
                String useBull = (bulls == 1) ? wordBull : wordBulls;
                System.out.println("Grade: " + bulls + " " + useBull + " and " + cows + " " + useCow);
            }
            turn++;
        }
    }

    static int validateNumSymbols(int numSymbols) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            if (numSymbols < 1 || numSymbols > 36) {
                System.out.println("Error: Invalid number of possible symbols. Please enter a number between 1 and 36 (inclusive).");
                System.out.println("Input the number of possible symbols in the code (up to 36):");
                System.out.print("> ");
                numSymbols = scanner.nextInt();
            } else {
                validInput = true;
            }
        }
        return numSymbols;
    }
    static int validateNumLength(int numLen) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            if (numLen > 36) {
                System.out.println("Error: Invalid number of length. Please enter a number between 1 and 36 (inclusive).");
                System.out.println("Input the number of length in the code (up to 36):");
                System.out.print("> ");
                numLen = scanner.nextInt();
            } else {
                validInput = true;
            }
        }
        return numLen;
    }

    static char getSymbol(int index) {
        if (index < 10) {
            return (char) ('0' + index);
        } else {
            return (char) ('a' + index - 10);
        }
    }

    static String getSymbolRange(int numSymbols) {
        StringBuilder symbolRange = new StringBuilder();
        for (int i = 0; i < numSymbols; i++) {
            symbolRange.append(getSymbol(i));
        }
        return symbolRange.toString();
    }
}