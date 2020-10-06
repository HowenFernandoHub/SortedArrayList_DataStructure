/**
 *  ArrayDriver Class:
 *      - Times and tests the add (In-Order and Backwards), remove and clear methods
 *      - of the SortedArrayList class
 * @author Howen Anthony Fernando
 */

public class ArrayDriver {
    /*
     * stores the amount of time it took for the most recent test
     * needs global scope so that it can be accessed from the main method
     */
    private long testTime;

    private SortedArrayList<Integer> mySortedArrList;


    /**
     * testAddInOrder - Should result in linear growth
     * @param testSize
     * @return - the testSize and the testTime
     */
    public String[] testAddInOrder(int testSize) {
        long startTime;
        long endTime;
        mySortedArrList = new SortedArrayList<>(10000);

        startTime = System.currentTimeMillis();
            for (int i = 0; i < testSize; i++) {        // add elements in natural order from SMALLEST to LARGEST
                mySortedArrList.add(i);
            }
        endTime = System.currentTimeMillis();

        testTime = endTime - startTime;

        String[] values = {Integer.toString(testSize), Long.toString(testTime)};

        return values;
    }


    /**
     * testAddBackwards - Should result in quadratic growth
     * @param testSize
     * @return - the testSize and the testTime
     */
    public String[] testAddBackwards(int testSize) {
        long startTime;
        long endTime;

        mySortedArrList  = new SortedArrayList<>(5000);

        startTime = System.currentTimeMillis();
            for (int i = (testSize - 1); i >= 0; i--) {     // add elements in reverse order LARGEST to SMALLEST
                mySortedArrList.add(i);
            }
        endTime = System.currentTimeMillis();

        testTime = endTime - startTime;

        String[] values = {Integer.toString(testSize), Long.toString(testTime)};

        return values;
    }


    /**
     *testRemobe - should result in quadratic growth
     * @param testSize
     * @return - the testSize and the testTime
     */
    public String[] testRemove(int testSize) {
        long startTime;
        long endTime;

        mySortedArrList = getSortedArr(testSize);

        startTime = System.currentTimeMillis();
            while (!mySortedArrList.isEmpty()) {
                mySortedArrList.remove(0);
            }
        endTime = System.currentTimeMillis();

        testTime = endTime - startTime;

        String[] values = {Integer.toString(testSize), Long.toString(testTime)};

        return values;
    }


    /**
     * testClear - should result in constant time
     * @param testSize
     * @return - the testSize and the testTime
     */
    public String[] testClear(int testSize) {
        long startTime;
        long endTime;

        mySortedArrList = getSortedArr(testSize);

        // this one is timed in nanoseconds because the function takes constant time and is very fast
        startTime = System.nanoTime();
            mySortedArrList.clear();
        endTime = System.nanoTime();

        testTime = endTime - startTime;

        String[] values = {Integer.toString(testSize), Long.toString(testTime)};

        return values;
    }


    /**
     * getSortedArr - private method that instantiates and fills an array for the
     *              - testRemove and testClear functions
     * @param testSize
     * @return salToGenerate a SAL filled with elements
     */
    private SortedArrayList<Integer> getSortedArr(int testSize) {
        SortedArrayList<Integer> salToGenerate = new SortedArrayList<>(10000);
        for (int i = 0; i < testSize; i++) {
            salToGenerate.add(i);
        }
        return salToGenerate;
    }


    /**
     * testAddInOrder - overloaded method which calls the other testAddInOrder method
     *                - creates a formatted String which it will return to be printed in the main method
     * @param testSize
     * @param previousTime
     * @return - String toReturn
     */
    private String testAddInOrder(int testSize, double previousTime) {
        double ratio;               // ratio of the testTime to the previous testTime
        String[] testValues;        // holds the values of the testSize and the testTime
        String toReturn = "";       // will be printed in the main method

        System.out.println("Testing addInOrder:");

        // This will run the test on addInOrder 7 times
        for (int i = 0; i < 7; i++) {
            testValues = this.testAddInOrder(testSize);

            if (i == 0) {
                previousTime = this.testTime;
                toReturn = String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4s\n", testValues[0], testValues[1] + " milliseconds", "-");
            }

            else {
                ratio = (double)this.testTime / previousTime;
                previousTime = this.testTime;
                toReturn += String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4.2f\n", testValues[0], testValues[1] + " milliseconds", ratio);
            }

            testSize *= 2;      // Doubling test size
        }

        return toReturn;
    }


    /**
     * testAddBackwards - overloaded method which calls the other testAddBackwards method
     * @param testSize
     * @param previousTime
     * @return String toReturn - a String which contains all the data from the time complexity evaluations
     */
    private String testAddBackwards(int testSize, double previousTime) {
        double ratio;               // ratio of the testTime to the previous testTime
        String[] testValues;        // holds the values of the testSize and the testTime
        String toReturn = "";       // will be printed in the main method

        System.out.println("Testing addBackwards:");

        // This will run the test on addBackwards 5 times
        for (int i = 0; i < 5; i++) {
            testValues = this.testAddBackwards(testSize);

            if (i == 0) {
                previousTime = this.testTime;
                toReturn = String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4s\n", testValues[0], testValues[1] + " milliseconds", "-");
            }

            else {
                ratio = (double)this.testTime / previousTime;
                previousTime = this.testTime;
                toReturn += String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4.2f\n", testValues[0], testValues[1] + " milliseconds", ratio);
            }

            testSize *= 2;      // Doubling test size
        }

        return toReturn;
    }


    /**
     * testRemove - overloaded method which calls the other testRemove method
     * @param testSize
     * @param previousTime
     * @return String toReturn - a String which contains all the data from the time complexity evaluations
     */
    private String testRemove(int testSize, double previousTime) {
        double ratio;               // ratio of the testTime to the previous testTime
        String[] testValues;        // holds the values of the testSize and the testTime
        String toReturn = "";       // will be printed in the main method

        System.out.println("Testing remove:");

        for (int i = 0; i < 5; i++) {
            testValues = this.testRemove(testSize);

            if (i == 0) {
                previousTime = this.testTime;
                toReturn = String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4s\n", testValues[0], testValues[1] + " milliseconds", "-");
            }

            else {
                ratio = (double)this.testTime / previousTime;
                previousTime = this.testTime;
                toReturn += String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4.2f\n", testValues[0], testValues[1] + " milliseconds", ratio);
            }

            testSize *= 2;      // Doubling test size
        }

        return toReturn;
    }


    /**
     * testClear - overloaded method which calls the other testClear method
     * @param testSize
     * @param previousTime
     * @return String toRturn - a String which contains all the data from the time complexity evaluations
     */
    private String testClear(int testSize, double previousTime) {
        double ratio;               // ratio of the testTime to the previous testTime
        String[] testValues;        // holds the values of the testSize and the testTime
        String toReturn = "";       // will be printed in the main method

        System.out.println("Testing clear:");

        for (int i = 0; i < 5; i++) {
            testValues = this.testClear(testSize);

            if (i == 0) {
                previousTime = this.testTime;
                toReturn = String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4s\n", testValues[0], testValues[1] + " nanoseconds", "-");
            }

            else {
                ratio = (double)this.testTime / previousTime;
                previousTime = this.testTime;
                toReturn += String.format("    Test Size: %-10s    Time: %-18s    Ratio: %-4.2f\n", testValues[0], testValues[1] + " nanoseconds", ratio);
            }

            testSize *= 2;      // Doubling test size
        }

        return toReturn;
    }


    /**
     * main method - calls the testing methods in the ArrayDriver class
     * @param args
     */
    public static void main(String[] args) {

        ArrayDriver aD = new ArrayDriver();

        /* ****** TESTING ADD IN ORDER ******* */
        System.out.println(aD.testAddInOrder(1000000, 0));

        /* ****** TESTING ADD BACKWARDS ****** */
        System.out.println(aD.testAddBackwards(5000, 0));

        /* ****** TESTING REMOVE ******* */
        System.out.println(aD.testRemove(5000, 0));

        /* ****** TESTING CLEAR ******* */
        System.out.println(aD.testClear(500000, 0));

    }


}
