package PartOne.Assignment.Week2;

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
        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.previous = oldTail;
        if (count == 0) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        count++;
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
        Item item = tail.item;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
        }
        count--;
        if (count == 0) {
            head = null;
        }
        return item;
    }
    private void removalValidation() {
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
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (hasNext()) {
                Item item = current.item;
                current = current.next;
                return item;
            } else {
                throw new java.util.NoSuchElementException();
            }
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("d");
        deque.addFirst("c");
        deque.addLast("e");
        deque.addLast("f");
        deque.removeFirst();
        deque.removeLast();
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(deque.size());
    }
}

