package dev.hond0n.rogue.world.heuristic.genetic.gambiarra;

import dev.hond0n.rogue.algebra.Pair;
import dev.hond0n.rogue.world.element.Cell;

import java.util.ArrayList;

public class Gambiarra extends ArrayList<Cell> {
    public boolean containsPair(Pair o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Pair o) {
        return indexOfRange(o, 0, this.size());
    }

    int indexOfRange(Pair o, int start, int end) {
        Object[] es = this.toArray();
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(((Cell) es[i]).getCoord())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
