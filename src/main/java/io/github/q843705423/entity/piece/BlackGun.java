package io.github.q843705423.entity.piece;

import io.github.q843705423.entity.piece.common.Piece;

import java.util.List;

public class BlackGun extends Piece {
    @Override
    public String englishName() {
        return "c";
    }

    @Override
    public String chinaName() {
        return "砲";
    }

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {

    }

    @Override
    public boolean isRed() {
        return false;
    }
}
