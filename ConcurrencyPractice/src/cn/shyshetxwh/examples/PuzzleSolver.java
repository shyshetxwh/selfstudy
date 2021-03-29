package cn.shyshetxwh.examples;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * FileName: PuzzleSolver
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/15 0015 19:23
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
    public PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    private final AtomicInteger taskCount = new AtomicInteger(0);


    class CountingSolverTask extends SolverTask {

        public CountingSolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                if (taskCount.decrementAndGet() == 0)
                    solution.setValue(null);
            }
        }
    }
}
