package deque;

class ListNode<T> {
    T value;
    ListNode<T> next;
    ListNode<T> pre;
    public ListNode(T item) {
        this.value = item;
        this.next = null;
    }
        }

public class LinkedListDeque<T> {
    ListNode<T> head;
    ListNode<T> tail;
    int size;

    public LinkedListDeque() {
        head = new ListNode<>(null);
        tail = head;
        size = 0;
    }

    public void addFirst(T item) {
        if (isEmpty()) {
            head.value = item;
            tail = head;
        } else {
            ListNode node = new ListNode(item);
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addLast(T item) {
        if (isEmpty()) {               // SC, empty
            tail.value = item;
            head = tail;
        } else {
            ListNode node = new ListNode(item);
            tail.next = node;
            node.pre = tail;
            tail = tail.next;
        }
        size++;
    }


    public T removeFirst() {
        // SC
        if (isEmpty()) {
            return null;
        }

        ListNode node = head;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        return (T)node.value;
    }

    public T removeLast() {
        // SC
        if (isEmpty()) {
            return null;
        }

        ListNode node = tail;
        if (size == 1) {
            tail = null;
            head = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }
        size--;
        return (T)node.value;
    }

    // index starts from 0 to size - 1
    public T get(int index) {
        // SC
        if (index > size - 1) {
            return null;
        }

        int count = 0;
        ListNode ptr = head;
        while (count < index && ptr != null) {
            ptr = ptr.next;
            count++;
        }
        return (T)ptr.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        ListNode ptr = head;
        while (ptr != null) {
            System.out.print(ptr.value);
            if (ptr.next != null) {
                System.out.print(", ");
            } else {
                System.out.println(".");
            }
            ptr = ptr.next;
        }
    }

}
