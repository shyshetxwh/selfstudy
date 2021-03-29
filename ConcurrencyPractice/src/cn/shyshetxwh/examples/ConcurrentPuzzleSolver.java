package cn.shyshetxwh.examples;

import java.util.List;
import java.util.concurrent.*;

/**
 * FileName: ConcurrentPuzzleSolver
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/15 0015 19:05
 */
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P, Boolean> seen;
    /*private*/ ValueLatch<Node<P, M>> solution = new ValueLatch<>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
        this.exec = Executors.newCachedThreadPool();
        this.seen = new ConcurrentHashMap<>();
        if (exec instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor type = (ThreadPoolExecutor) exec;
            type.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    public List<M> solve() throws InterruptedException {
        try {
            P start = puzzle.initialPosition();
            exec.execute(newTask(start, null, null));
            Node<P, M> solNode = solution.getValue();
            return (solNode == null) ? null : solNode.asMoveList();
        } finally {
            exec.shutdown();
        }

    }

    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return new SolverTask(p, m, n);
    }

    class SolverTask extends Node<P, M> implements Runnable {
        public SolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            if (solution.isSet() || seen.putIfAbsent(pos, true) != null)
                return;
            if (puzzle.isGoal(pos))
                solution.setValue(this);
            else {
                for (M m : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos, m), m, this));
                }
            }
        }
    }

}
