package org.gurikin.leetcode.medium;

class CircularQueue {
    private int[] internal;
    private int size;
    private int head;
    private int tail;

    public CircularQueue(int k) {
        size = 0;
        head = 0;
        tail = -1;
        internal = new int[k];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = 0;
        }
        size++;
        tail++;
        if (tail >= internal.length) {
            tail = 0;
        }
        internal[tail] = value;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        size--;
        if (size == 0) {
            tail = head = -1;
            return true;
        }
        internal[head] = -1;
        head++;
        if (head >= internal.length) {
            head = 0;
        }
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return internal[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return internal[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == internal.length;
    }

    public static void main(String[] args) {
        CircularQueue myCircularQueue = new CircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1)); // return True
        System.out.println(myCircularQueue.enQueue(2)); // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.enQueue(4)); // return False
        System.out.println(myCircularQueue.Rear());     // return 3
        System.out.println(myCircularQueue.isFull());   // return True
        System.out.println(myCircularQueue.deQueue());  // return True
        System.out.println(myCircularQueue.enQueue(4)); // return True
        System.out.println(myCircularQueue.Rear());     // return 4
    }
}