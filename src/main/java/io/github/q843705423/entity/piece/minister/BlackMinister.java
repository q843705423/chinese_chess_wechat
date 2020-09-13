package io.github.q843705423.entity.piece.minister;

import java.util.List;

public class BlackMinister extends Minister {
    @Override
    public String englishName() {
        return "b";
    }

    @Override
    public String chinaName() {
        return "象";
    }

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {

    }

    @Override
    public boolean isRed() {
        return false;
    }
}
