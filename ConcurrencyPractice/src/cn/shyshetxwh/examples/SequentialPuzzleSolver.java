package cn.shyshetxwh.examples;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FileName: SequentialPuzzleSolver
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/15 0015 18:56
 */
public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P start = puzzle.initialPosition();
        return search(new Node<P, M>(start, null, null));
    }

    private List<M> search(Node<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos))
                return node.asMoveList();
            for (M move : puzzle.legalMoves(node.pos)) {
                P next = puzzle.move(node.pos, move);
                Node<P, M> nextNode = new Node<>(next, move, node);
                List<M> result = search(nextNode);
                if (result != null)
                    return result;
            }
        }
        return null;
    }
}
