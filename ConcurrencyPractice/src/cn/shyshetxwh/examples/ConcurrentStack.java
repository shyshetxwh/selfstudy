package cn.shyshetxwh.examples;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * FileName: ConcurrentStack
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/17 0017 9:30
 */
@ThreadSafe
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> top = new AtomicReference<>();

    public void push(E item) {
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        while (true) {
            Node<E> oldHead = top.get();
            if (oldHead == null)
                return null;
            Node<E> newHead = oldHead.next;
            if (top.compareAndSet(oldHead, newHead))
                return oldHead.item;
        }
    }


    private static class Node<E> {
        public final E item;
        public Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentStack<Integer> stack = new ConcurrentStack<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    stack.push(j);
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(5);
        new Thread(() -> {
            while (true) {
                Integer i = stack.pop();
                if (i == null) {
                    return;
                }
                System.out.println(i);
            }
        }).start();
    }
}
