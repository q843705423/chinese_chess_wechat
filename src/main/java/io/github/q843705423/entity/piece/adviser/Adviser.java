package io.github.q843705423.entity.piece.adviser;

import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.GoReadHouse;
import io.github.q843705423.entity.piece.common.Piece;

import java.util.List;

public abstract class Adviser extends Piece implements GoReadHouse {

    public static int[] canGo = new int[90];

    static {
        canGo[3] = 1;
        canGo[5] = 1;
        canGo[13] = 1;
        canGo[21] = 1;
        canGo[23] = 1;
        canGo[84] = 1;
        canGo[86] = 1;
        canGo[76] = 1;
        canGo[66] = 1;
        canGo[68] = 1;
    }

    public static int[][] dt = new int[][]{
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1},
    };

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {


        for (int i = 0; i < dt.length; i++) {
            int dy = y + dt[i][0];
            int dx = x + dt[i][1];
            int go = dy * Board.W + dx;

            if (go >= 0 && go < 90 && canGo[go] == 1) {
                if ((!isRed() && dy < 4) || (isRed() && dy >= 4)) {
                    inRangeWillAdd(list, go, now, board, who);
                }
            }
        }
    }

    @Override
    public int getScore() {
        return 100;
    }
}
