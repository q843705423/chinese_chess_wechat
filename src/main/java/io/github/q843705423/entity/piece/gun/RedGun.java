package io.github.q843705423.entity.piece.gun;

import java.util.List;

public class RedGun extends Gun {
    @Override
    public String englishName() {
        return "C";
    }

    @Override
    public String chinaName() {
        return "炮";
    }

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {

    }

    @Override
    public boolean isRed() {
        return true;
    }
}
