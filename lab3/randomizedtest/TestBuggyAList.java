package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    /** Test the Sort.sort method. */
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> aList1 = new AListNoResizing<>();
        BuggyAList<Integer> aList2 = new BuggyAList<>();


        for (int i = 0; i < 3; i += 1) {
            aList1.addLast(i);
            aList2.addLast(i);
        }

        for (int j = 0; j < 3; j += 1) {
            aList1.removeLast();
            aList2.removeLast();
        }

        for (int k = 0; k < 3; k += 1) {
            assertEquals(aList1.get(k), aList2.get(k));
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size1 = L.size();
                int size2 = L2.size();
                assertEquals(size1, size2);
                System.out.println("size1: " + size1 + " size2: " + size2);
            } else if (operationNumber == 2 && L.size() > 0 && L2.size() > 0) {
                int val1 = L.getLast();
                int val2 = L2.getLast();
                assertEquals(val1, val2);
            } else if (operationNumber == 3 && L.size() > 0 && L2.size() > 0) {
                int val1 = L.removeLast();
                int val2 = L2.removeLast();
                assertEquals(val1, val2);
            }
        }
    }
}
