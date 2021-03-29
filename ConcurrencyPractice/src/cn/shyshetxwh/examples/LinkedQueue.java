package cn.shyshetxwh.examples;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * FileName: LinkedQueue
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/17 0017 10:02
 */
@ThreadSafe
public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curNode = tail.get();
            Node<E> tailNext = curNode.next.get();
            //如果尾结点没有被更新，则执行更新操作
            if (curNode == tail.get()) {
                //尾结点没有被更新，但是尾结点的下一个结点被更新了,则将尾结点设置为下一各结点，并继续进行更新操作
                if (tailNext != null) {
                    tail.compareAndSet(curNode, tailNext);
                } else {
                    //尾结点和尾结点的下一结点都没有被更新，则执行更新操作
                    //先更新当前尾结点的下一结点
                    if (curNode.next.compareAndSet(null, newNode)) {
                        //更新成功，继续更新为尾结点
                        tail.compareAndSet(curNode, newNode);
                        return true;
                    }
                }
            }
        }
    }
}
