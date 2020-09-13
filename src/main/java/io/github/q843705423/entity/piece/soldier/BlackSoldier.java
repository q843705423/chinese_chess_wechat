package io.github.q843705423.entity.piece.soldier;

import io.github.q843705423.entity.piece.common.Board;

import java.util.List;

public class BlackSoldier extends AbstractBing {
    @Override
    public String englishName() {
        return "p";
    }

    @Override
    public String chinaName() {
        return "卒";
    }


    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        int posi = y * Board.W + x;

        int e = posi + Board.W;
        inRangeWillAdd(list, e, now, board, who);

        if (y >= 5) {
            if (x - 1 >= 0) {
                inRangeWillAdd(list, y * Board.W + x - 1, now, board, who);
            }
            if (x + 1 <= Board.W - 1) {
                inRangeWillAdd(list, y * Board.W + x + 1, now, board, who);
            }
        }

    }

    @Override
    public boolean isRed() {
        return false;
    }
}
