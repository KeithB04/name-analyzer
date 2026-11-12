
import java.util.Arrays;

/**
 * Basic tests for the NameAnalyzer program.
 */
public class NameAnalyzerTest {

    private static int testsPassed = 0;

    public static void main(String[] args) {
        testParseNames();
        testEmptyEntries();
        testNameCount();
        testLetterCount();
        testNameLength();
        testShortestName();
        testLongestName();
        testStandardDeviation();

        System.out.println("\nAll " + testsPassed + " tests passed!");
    }

    /**
     * Checks whether two values are equal.
     */
    private static void checkEquals(
            Object expected, Object actual, String testName) {

        if (!expected.equals(actual)) {
            throw new AssertionError(testName
                    + " failed. Expected: " + expected
                    + ", Actual: " + actual);
        }

        testsPassed++;
        System.out.println("PASSED: " + testName);
    }

    /**
     * Tests comma-separated name parsing.
     */
    private static void testParseNames() {
        String[] expected = {"John", "Mary", "Alex"};
        String[] actual =
                NameAnalyzer.parseNames("John, Mary, Alex");

        checkEquals(
                Arrays.toString(expected),
                Arrays.toString(actual),
                "Parse Names");
    }

    /**
     * Tests removal of empty entries and extra spaces.
     */
    private static void testEmptyEntries() {
        String[] expected = {"John", "Mary"};
        String[] actual =
                NameAnalyzer.parseNames(" , John, , Mary, ");

        checkEquals(
                Arrays.toString(expected),
                Arrays.toString(actual),
                "Empty Entries");
    }

    /**
     * Tests the total number of names.
     */
    private static void testNameCount() {
        String[] names = {"John", "Mary", "Alex"};

        checkEquals(3, NameAnalyzer.numNames(names), "Name Count");
    }

    /**
     * Tests total letter count excluding spaces.
     */
    private static void testLetterCount() {
        String input = "John Smith, Mary, Alex";

        checkEquals(17, NameAnalyzer.numLetters(input),
                "Letter Count");
    }

    /**
     * Tests name length excluding spaces.
     */
    private static void testNameLength() {
        checkEquals(9, NameAnalyzer.getNameLength("John Smith"),
                "Name Length");
    }

    /**
     * Tests shortest name identification.
     */
    private static void testShortestName() {
        String[] names = {"Alexander", "John Smith", "Amy"};

        checkEquals("Amy", NameAnalyzer.getShortName(names),
                "Shortest Name");
    }

    /**
     * Tests longest name identification.
     */
    private static void testLongestName() {
        String[] names = {"Amy", "John Smith", "Alexander"};

        checkEquals("John Smith", NameAnalyzer.getLongName(names),
                "Longest Name");
    }

    /**
     * Tests population standard deviation.
     */
    private static void testStandardDeviation() {
        String[] names = {"Amy", "John", "Alexander"};

        double mean = 16.0 / 3.0;
        double expected = Math.sqrt(62.0 / 9.0);
        double actual =
                NameAnalyzer.getStandardDeviation(mean, names, 3);

        if (Math.abs(expected - actual) > 0.0001) {
            throw new AssertionError(
                    "Standard Deviation failed. Expected: "
                    + expected + ", Actual: " + actual);
        }

        testsPassed++;
        System.out.println("PASSED: Standard Deviation");
    }
}
