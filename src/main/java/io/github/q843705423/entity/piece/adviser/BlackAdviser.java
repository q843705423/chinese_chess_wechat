package io.github.q843705423.entity.piece.adviser;

import io.github.q843705423.entity.piece.minister.Minister;

import java.util.List;

public class BlackAdviser extends Minister {
    @Override
    public String englishName() {
        return "a";
    }

    @Override
    public String chinaName() {
        return "士";
    }

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        //TODO

    }

    @Override
    public boolean isRed() {
        return false;
    }
}
