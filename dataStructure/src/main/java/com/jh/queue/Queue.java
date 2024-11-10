package com.jh.queue;

public interface Queue<E> {
    boolean offer(E value);

    E poll();

    E peek();

    boolean isEmpty();

    boolean isFull();
}
