package PartOne.Assignment.Week2;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    private int count;
    private Node head;
    // construct an empty randomized queue
    public RandomizedQueue() {
        count = 0;
        head = null;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;
        if (count != 0) {
            oldHead.previous = head;
        }
        count++;
    }

    // remove and return a random item
    public Item dequeue() {
        validate();
        int n = StdRandom.uniform(count);
        Item item;
        if (n == 0) {
            item = head.item;
            head = head.next;
            if (head != null) {
                head.previous = null;
            }
        } else {
            int i = 0;
            Node current = head;
            while (i < n) {
                current = current.next;
                i++;
            }
            item = current.item;
            Node pre = current.previous;
            Node nex = current.next;
            if (pre != null) {
                pre.next = current.next;
            }
            if (nex != null) {
                nex.previous = current.previous;
            }
        }
        count--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validate();
        int n = StdRandom.uniform(count);
        int i = 0;
        Node current = head;
        while (i < n) {
            current = current.next;
            i++;
        }
        return current.item;
    }

    private void validate() {
        if (count == 0) {
            throw new java.util.NoSuchElementException();
        }
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] base;
        private final int length;
        private int currentIndex;
        public RandomizedQueueIterator() {
            currentIndex = 0;
            length = size();
            base = (Item[]) new Object[length];
            Node cur = head;
            for (int i = 0; i < length; i++) {
                base[i] = cur.item;
                cur = cur.next;
            }
            for (int i = 0; i < length; i++) {
                int newIndex = StdRandom.uniform(length);
                if (newIndex != i) {
                    Item temp = base[i];
                    base[i] = base[newIndex];
                    base[newIndex] = temp;
                }
            }
        }
        public boolean hasNext() {
            return currentIndex < length;
        }

        public Item next() {
            if (currentIndex < length) {
                return base[currentIndex++];
            }
            throw new java.util.NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
//        PartOne.Assignment.Week2.RandomizedQueue<String> randomizedQueue = new PartOne.Assignment.Week2.RandomizedQueue<>();
//        randomizedQueue.enqueue("a");
//        randomizedQueue.enqueue("b");
//        randomizedQueue.enqueue("c");
//        randomizedQueue.enqueue("d");
//        randomizedQueue.enqueue("e");
//        randomizedQueue.enqueue("f");
//        randomizedQueue.enqueue("g");
//        System.out.println(randomizedQueue.dequeue());
//        System.out.println(randomizedQueue.dequeue());
//        System.out.println(randomizedQueue.dequeue());
//        System.out.println(randomizedQueue.size());
//        System.out.println(randomizedQueue.isEmpty());
//        Iterator<String> iter = randomizedQueue.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.size();
        rq.isEmpty();
        rq.enqueue(3);
        rq.dequeue();
    }
}