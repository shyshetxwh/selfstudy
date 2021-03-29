package cn.shyshetxwh.examples;

import junit.framework.TestCase;

/**
 * FileName: BoundBufferTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/16 0016 10:34
 */
public class BoundBufferTest extends TestCase {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;

    public void testIsEmptyWhenConstructed() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        assertTrue("empty", bb.isEmpty());
        assertFalse("full", bb.isFull());
        //assertFalse(bb.isEmpty());
    }

    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assertTrue(bb.isFull());
        assertFalse(bb.isEmpty());
        //assertTrue("empty", bb.isEmpty());
    }

    public void testTakeBlocksWhenEmpty() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    int take = bb.take();
                    fail();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        };
        try {
            t.start();
            Thread.sleep(LOCKUP_DETECT_TIMEOUT);
            t.interrupt();
            t.join(LOCKUP_DETECT_TIMEOUT);
            assertFalse(t.isAlive());
        } catch (InterruptedException e) {
            fail();
        }
    }


    void testLeak() throws InterruptedException {
        BoundedBuffer<Big> bb = new BoundedBuffer<>(CAPACITY);
        int heapSize1 = snapshotHeap();
        for (int i = 0; i < CAPACITY; i++) {
            bb.put(new Big());
        }
        for (int i = 0; i < CAPACITY; i++) {
            bb.take();
        }
        int heapSize2 = snapshotHeap();
        assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }

    private int snapshotHeap() {
        /* Snapshot heap and return heap size */
        return 0;
    }

    class Big {
        double[] data = new double[100000];
    }


}
