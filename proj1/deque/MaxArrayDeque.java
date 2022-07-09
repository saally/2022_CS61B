package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> defaultComparator;
    public MaxArrayDeque(Comparator<T> c) {
        defaultComparator = c;
    }
    public T max() {
        return max(defaultComparator);
    }
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T maxItem = (T) get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(maxItem, (T) get(i)) < 0) {
                maxItem = (T) get(i);
            }
        }
        return maxItem;
    }
}
