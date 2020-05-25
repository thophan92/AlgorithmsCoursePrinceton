import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    private Node head;
    private Node tail;
    private int count;
    // construct an empty deque
    public Deque() {
        count = 0;
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        addValidation(item);
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        if (count == 0) {
            tail = head;
        } else {
          oldHead.previous = head;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        addValidation(item);

    }

    // remove and return the item from the front
    public Item removeFirst() {
        removalValidation();
        Item item = head.item;
        head = head.next;
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        removalValidation();

    }
    private void removalValidation(){
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
    }
    private void addValidation(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

    }

}