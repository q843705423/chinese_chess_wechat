package io.github.q843705423.entity.piece.soldier;

import io.github.q843705423.entity.piece.common.Board;

import java.util.List;

public class RedSoldier extends AbstractSoldier {


    @Override
    public String englishName() {
        return "P";
    }

    @Override
    public String chinaName() {
        return "兵";
    }

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        int posi = y * Board.W + x;
        int e = posi - Board.W;
        if (inRange(e)) {
            list.add(e);
        }
        if (y < 5) {
            if (x - 1 >= 0) {
                int e1 = y * Board.W + x - 1;
                if (inRange(e1))
                    list.add(e1);
            }
            if (x + 1 < Board.W) {
                int e1 = y * Board.W + x + 1;
                if (inRange(e1)) {
                    list.add(e1);

                }
            }
        }
    }


    @Override
    public boolean isRed() {
        return true;
    }
}
