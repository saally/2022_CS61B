package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class MaxArrayDequeTest {

    public class testComparator<T> implements Comparator<T> {
        public int compare(T o1, T o2) {
            Integer o1int = (int) o1;
            Integer o2int = (int) o2;
            return o1int.compareTo(o2int);
        }
    }

    public class booleanComparator<T> implements Comparator<T> {
        public int compare(T o1, T o2) {
            int o1B = ((boolean) o1 == true)? 1: -1;
            int o2B = ((boolean) o2 == true)? 1: -1;
            return o1B - o2B;
        }
    }

    Comparator c1 = new testComparator();
    Comparator c2 = new booleanComparator();

    @Test
    public void addIsEmptySizeTest() {

        MaxArrayDeque<String> lld1 = new MaxArrayDeque<String>(c1);

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(c1);
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>(c1);
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create MaxArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        MaxArrayDeque<String>  lld1 = new MaxArrayDeque<String>(c1);
        MaxArrayDeque<Double>  lld2 = new MaxArrayDeque<Double>(c1);
        MaxArrayDeque<Boolean> lld3 = new MaxArrayDeque<Boolean>(c2);

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty MaxArrayDeque. */
    public void emptyNullReturnTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(c1);

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(c1);
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void equalsTest() {
        MaxArrayDeque<String> a = new MaxArrayDeque<>(c1);
        a.addFirst("a");
        a.addFirst("b");
        a.addFirst("c");

        MaxArrayDeque<String> b = new MaxArrayDeque<>(c1);
        b.addFirst("a");
        b.addFirst("b");
        b.addFirst("c");

        assertTrue("two deques are the same", a.equals(b));
    }
}
