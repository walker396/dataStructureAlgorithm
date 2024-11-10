package com.jh.queue;


public interface BlockingQueue<E> {
    public void offer(E e) throws InterruptedException;

    public void offer(E e, long timeout) throws InterruptedException;

    E poll() throws InterruptedException;

}
