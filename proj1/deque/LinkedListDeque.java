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

    LinkedListDeque() {
        head = new ListNode<>(null);
        tail = head;
        size = 0;
    }

    public void addFirst(T item) {
        // add at head
        if (head.value == null) {
            head.value = item;
        }
        // add at middle and end
        ListNode node = new ListNode(item);
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(T item) {
        // SC, when empty, add at head
        if (isEmpty()) {
            tail.value = item;
        }
        // add at middle or end
        tail.next = new ListNode<>(item);
        tail = tail.next;
        if (size == 1) {
            head = head.next;
        }
        size++;
    }


    public T removeFirst() {
        // SC
        if (isEmpty()) {
            return null;
        }

        ListNode node = new ListNode(head.value);
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            ListNode temp = head;
            head = head.next;
            temp = null;
        }
        size--;
        return (T)node.value;
    }

    public T removeLast() {
        // SC
        if (isEmpty()) {
            return null;
        }

        ListNode node = new ListNode(tail.value);
        if (tail == head) {
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
