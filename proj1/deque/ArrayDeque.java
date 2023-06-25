package deque;

public class ArrayDeque<T> {

    T[] arr;
    int headNext;
    int tailNext;
    int size;

    public ArrayDeque() {
        arr = (T[])new Object[8];
        headNext = 0;
        tailNext = 0;
        size = 0;
    }

    public void addFirst(T item) {
        if (needResize(size + 1)) {
            resizeUp();
        }

        arr[headNext] = item;
        if (headNext > 0) {
            if (isEmpty()) {                    // corner case when empty
                tailNext = headNext + 1;
            }
            headNext = wrapIndex(headNext - 1);        // 1. circular array  2. cannot use --
        } else {
            headNext = arr.length - 1;
        }
        size++;
    }

    public void addLast(T item) {
        if (needResize(size + 1)) {
            resizeUp();
        }

        arr[tailNext] = item;
        if (tailNext < arr.length - 1) {
            if (isEmpty()) {
                headNext = wrapIndex(headNext - 1);
            }
            tailNext = wrapIndex(tailNext + 1);
        } else {
            tailNext = 0;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }

    public T removeFirst() {
        // ignore resize

        if (isEmpty()) {
            return null;
        }

        int firstInd = wrapIndex(headNext + 1);

        T item = arr[firstInd];
        arr[firstInd] = null;
        headNext = firstInd;
        size--;
        return item;
    }

    public T removeLast() {
        // ignore resize

        if (isEmpty()) {
            return null;
        }

        int lastInd = wrapIndex(tailNext - 1);

        T item = arr[lastInd];
        arr[lastInd] = null;
        tailNext = lastInd;
        size--;
        return item;
    }

    public T get(int index) {
        // SC
        if (index < 0 || index > arr.length - 1 || index > size - 1) {
            return null;
        }
        return arr[index];
    }

    // helper for resizing
    private void resizeUp() {
        // SC
        if (size == 0 || size * 4 < arr.length) {
            return;
        }

        T[] old = arr;
        arr = (T[])new Object[old.length * 2];                 // forgot!

        // separate copy in two segment
        // from headNext + 1 to either TailNext -1 or end
        // not needed or from beginning to the TailNext - 1

        int newHeadNext = arr.length - 1 - (old.length - 1 - headNext);
        for (int i_old = headNext + 1, i_new = newHeadNext + 1;
             i_old < old.length && i_old < tailNext - 1;
             i_old++, i_new++) {
            arr[i_new] = old[i_old];
        }

        if (tailNext - 1 < headNext + 1) {
            for (int i = 0; i < tailNext; i++) {
                arr[i] = old[i];
            }
        } else {
            tailNext = newHeadNext + (tailNext - headNext);
        }
        headNext = newHeadNext;
    }

    private boolean needResize(int size) {
        return size * 4 > arr.length;
    }

    private int wrapIndex(int ind) {
        if (ind > arr.length - 1) {
            return ind - arr.length;
        } else if(ind < 0) {
            return ind + arr.length;
        }
        return ind;
    }
}

