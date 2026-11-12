
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A menu-driven program that analyzes a comma-separated list of names.
 * Users can sort names, filter names by different criteria, view statistics,
 * find the most frequent name, or enter a new list.
 *
 * Spaces are ignored when calculating name lengths.
 */
public class NameAnalyzer {

    /**
     * Starts the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        displayMenu();
    }

    /**
     * Displays the menu and processes user selections until the user exits.
     */
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        String input = getList(scanner);
        boolean running = true;

        while (running) {
            System.out.println("\n1) Display List Ordered");
            System.out.println("2) Display Full Names");
            System.out.println("3) Display Single Names");
            System.out.println("4) Display Name Statistics");
            System.out.println("5) Display Names with Even Length");
            System.out.println("6) Display Names with Odd Length");
            System.out.println("7) Display Names not Capitalized");
            System.out.println("8) Display Most Frequent Name");
            System.out.println("9) Enter New List of Names");
            System.out.println("0) Exit Program");
            System.out.print("Enter your selection: ");

            String selection = scanner.nextLine().trim();

            switch (selection) {
                case "1":
                    displayListOrdered(input);
                    break;
                case "2":
                    displayFullNames(input);
                    break;
                case "3":
                    displaySingleNames(input);
                    break;
                case "4":
                    displayNameStats(input);
                    break;
                case "5":
                    displayEvenNames(input);
                    break;
                case "6":
                    displayOddNames(input);
                    break;
                case "7":
                    displayNamesNoCaps(input);
                    break;
                case "8":
                    displayFrequentNames(input);
                    break;
                case "9":
                    input = getList(scanner);
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Splits a comma-separated string into an array of trimmed names.
     * Empty entries are excluded.
     *
     * @param input comma-separated list of names
     * @return array of valid names
     */
    public static String[] parseNames(String input) {
        ArrayList<String> names = new ArrayList<>();

        for (String name : input.split(",")) {
            String trimmedName = name.trim();

            if (!trimmedName.isEmpty()) {
                names.add(trimmedName);
            }
        }

        return names.toArray(new String[0]);
    }

    /**
     * Returns the number of letters in a name, excluding whitespace.
     *
     * @param name name to measure
     * @return length of the name without whitespace
     */
    public static int getNameLength(String name) {
        return name.replaceAll("\\s+", "").length();
    }

    /**
     * Displays all names in alphabetical order, ignoring case.
     *
     * @param input comma-separated list of names
     */
    public static void displayListOrdered(String input) {
        String[] names = parseNames(input);

        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);

        System.out.println("[" + String.join(", ", names) + "]");
    }

    /**
     * Displays names containing multiple words.
     *
     * @param input comma-separated list of names
     */
    public static void displayFullNames(String input) {
        String[] names = parseNames(input);

        for (String name : names) {
            if (name.matches(".*\\s+.*")) {
                System.out.println(name);
            }
        }
    }

    /**
     * Displays names containing only one word.
     *
     * @param input comma-separated list of names
     */
    public static void displaySingleNames(String input) {
        String[] names = parseNames(input);

        for (String name : names) {
            if (!name.matches(".*\\s+.*")) {
                System.out.println(name);
            }
        }
    }

    /**
     * Calculates and displays statistics for the list of names.
     *
     * @param input comma-separated list of names
     */
    public static void displayNameStats(String input) {
        String[] names = parseNames(input);

        int nameCount = numNames(names);
        int letterCount = numLetters(input);
        double averageLength = (double) letterCount / nameCount;

        String shortestName = getShortName(names);
        String longestName = getLongName(names);
        double standardDeviation =
                getStandardDeviation(averageLength, names, nameCount);

        System.out.println("Name Count: " + nameCount);
        System.out.println("Letter Count Total: " + letterCount);
        System.out.printf("Avg Name Length: %.2f%n", averageLength);
        System.out.println("Shortest Name: " + shortestName);
        System.out.println("Longest Name: " + longestName);
        System.out.printf("Population Standard Deviation: %.2f%n",
                standardDeviation);
    }

    /**
     * Returns the number of names in an array.
     *
     * @param names array of names
     * @return number of names
     */
    public static int numNames(String[] names) {
        return names.length;
    }

    /**
     * Counts the total number of characters in the names,
     * excluding commas and whitespace.
     *
     * @param input comma-separated list of names
     * @return total character count
     */
    public static int numLetters(String input) {
        int totalLetters = 0;

        for (String name : parseNames(input)) {
            totalLetters += getNameLength(name);
        }

        return totalLetters;
    }

    /**
     * Finds the shortest name, ignoring whitespace when comparing lengths.
     * Returns the first name if multiple names share the shortest length.
     *
     * @param names array of names
     * @return shortest name
     */
    public static String getShortName(String[] names) {
        String shortestName = names[0];

        for (String name : names) {
            if (getNameLength(name) < getNameLength(shortestName)) {
                shortestName = name;
            }
        }

        return shortestName;
    }

    /**
     * Finds the longest name, ignoring whitespace when comparing lengths.
     * Returns the first name if multiple names share the longest length.
     *
     * @param names array of names
     * @return longest name
     */
    public static String getLongName(String[] names) {
        String longestName = names[0];

        for (String name : names) {
            if (getNameLength(name) > getNameLength(longestName)) {
                longestName = name;
            }
        }

        return longestName;
    }

    /**
     * Calculates the population standard deviation of name lengths.
     * Whitespace is excluded from all length calculations.
     *
     * @param mean average name length
     * @param names array of names
     * @param nameCount number of names
     * @return population standard deviation
     */
    public static double getStandardDeviation(
            double mean, String[] names, int nameCount) {

        double sumOfSquares = 0;

        for (String name : names) {
            double difference = getNameLength(name) - mean;
            sumOfSquares += Math.pow(difference, 2);
        }

        return Math.sqrt(sumOfSquares / nameCount);
    }

    /**
     * Displays names with an even number of characters,
     * excluding whitespace.
     *
     * @param input comma-separated list of names
     */
    public static void displayEvenNames(String input) {
        String[] names = parseNames(input);

        for (String name : names) {
            if (getNameLength(name) % 2 == 0) {
                System.out.println(name);
            }
        }
    }

    /**
     * Displays names with an odd number of characters,
     * excluding whitespace.
     *
     * @param input comma-separated list of names
     */
    public static void displayOddNames(String input) {
        String[] names = parseNames(input);

        for (String name : names) {
            if (getNameLength(name) % 2 != 0) {
                System.out.println(name);
            }
        }
    }

    /**
     * Displays names containing words that do not begin
     * with an uppercase letter.
     *
     * @param input comma-separated list of names
     */
    public static void displayNamesNoCaps(String input) {
        String[] names = parseNames(input);

        for (String name : names) {
            String[] words = name.split("\\s+");

            for (String word : words) {
                if (!Character.isUpperCase(word.charAt(0))) {
                    System.out.println(name);
                    break;
                }
            }
        }
    }

    /**
     * Finds and displays the most frequent name, ignoring case.
     * If multiple names have the same highest frequency, the
     * first encountered name is displayed.
     *
     * @param input comma-separated list of names
     */
    public static void displayFrequentNames(String input) {
        String[] names = parseNames(input);

        ArrayList<String> uniqueNames = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        for (String currentName : names) {
            boolean found = false;

            for (int i = 0; i < uniqueNames.size(); i++) {
                if (uniqueNames.get(i).equalsIgnoreCase(currentName)) {
                    counts.set(i, counts.get(i) + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                uniqueNames.add(currentName);
                counts.add(1);
            }
        }

        int maxCount = 0;
        String mostFrequentName = "";

        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) > maxCount) {
                maxCount = counts.get(i);
                mostFrequentName = uniqueNames.get(i);
            }
        }

        if (maxCount > 1) {
            System.out.println("Most Frequent Name: " + mostFrequentName);
        } else {
            System.out.println("No Most Frequent Name");
        }
    }

    /**
     * Prompts the user to enter a comma-separated list of names.
     * Repeats until at least one valid name is provided.
     *
     * @param scanner Scanner used to read user input
     * @return validated list of names
     */
    public static String getList(Scanner scanner) {
        String input;

        do {
            System.out.print("Enter a list of names (separated by commas): ");
            input = scanner.nextLine();

            if (parseNames(input).length == 0) {
                System.out.println("Please enter at least one valid name.");
            }
        } while (parseNames(input).length == 0);

        return input;
    }
}
