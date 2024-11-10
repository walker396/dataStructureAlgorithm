package com.jh.queue;

import java.util.Iterator;

public class LinkedListQueue<E> implements Queue<E>,Iterable<E>{

    private class Node<E>{
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next){
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head = new Node(null, null);
    private Node<E> tail = head;
    private int size = 0;
    private int capacity = Integer.MAX_VALUE;

    {
        tail.next = head;
    }
    public LinkedListQueue(int capacity){
        this.capacity = capacity;
    }
    @Override
    public boolean offer(E value) {
        if(isFull()){
            return false;
        }
        Node newNode = new Node(value, head);
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(isEmpty()){
            return null;
        }
        Node<E> node = head.next;
        head.next = head.next.next;
        if (node == tail) {
            tail = head;
        }
        size--;
        return node.value;
    }

    @Override
    public E peek() {
        if(head == tail){
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };

    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue(2000);
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        queue.offer(40);
        queue.offer(50);
        queue.offer(60);
        queue.offer(70);

        queue.forEach(System.out::println);
    }
}
