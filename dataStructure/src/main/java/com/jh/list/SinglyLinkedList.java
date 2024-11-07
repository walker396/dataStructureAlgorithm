package com.jh.list;

import java.util.function.Consumer;

public class SinglyLinkedList {
    private Node head = null;

    private class Node {
        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value) {
        head = new Node(value, head);
    }

    public void loop(Consumer consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public Node findLast() {
        if (head == null) {
            return null;
        }
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();

        if (last == null) {
            head = new Node(value, head);
        } else {
            last.next = new Node(value, null);
        }
    }

    public int get(int position){
        int index = 0;
        for (Node p = head; p.next !=null ; p = p.next) {
            if(index == position){
                return p.value;
            }
            index++;
        }
        throw new IllegalArgumentException(String.format("The index %d is illegal.", position));
    }

    public void recursiveLoop(Consumer before, Consumer after,Node node){
        if(node==null){
            return;
        }
        before.accept(node.value);
        recursiveLoop(before, after, node.next);
        after.accept(node.value);
    }
    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        linkedList.addFirst(4);
        linkedList.addFirst(5);
        linkedList.loop((ele) -> {
            System.out.printf("%d ", ele);
        });
        System.out.println("=========");
        linkedList.addLast(6);
        linkedList.addLast(7);
        linkedList.loop((ele) -> {
            System.out.printf("%d ", ele);
        });
        System.out.println("=========");
        System.out.println(linkedList.get(1));
        System.out.println("=========");
        linkedList.recursiveLoop(System.out::println,System.out::println, linkedList.head);
    }
}
