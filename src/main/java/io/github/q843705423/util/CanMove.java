package io.github.q843705423.util;

import java.util.List;

public interface CanMove {
    //int[0]->who
    //int[1]->where
    void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list);
}
