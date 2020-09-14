package io.github.q843705423.entity.piece.minister;

import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.GoReadHouse;
import io.github.q843705423.entity.piece.common.Piece;

import java.util.List;

public abstract class Minister extends Piece implements GoReadHouse {

    public static int[][] go = {
            {2, 2},
            {2, -2},
            {-2, 2},
            {-2, -2},
    };

    public static int[][] leg = {
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1},
    };

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        for (int i = 0; i < go.length; i++) {
            int dy = y + go[i][0];
            int dx = x + go[i][1];
            int ly = y + leg[i][0];
            int lx = x + leg[i][1];
            int go = dy * Board.W + dx;


            int leg = ly * Board.W + lx;
            if (dx >= 0 && dx < Board.W && leg >= 0 && leg < 90) {
                if (board[leg] == -1) {
                    if ((!isRed() && dy <= 4) || (isRed() && dy > 4)) {
                        inRangeWillAdd(list, go, now, board, who);
                    }
                }
            }
        }

    }
}
