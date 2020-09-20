package io.github.q843705423.entity.piece.kind;

import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.GoReadSoldier;
import io.github.q843705423.entity.piece.common.Piece;

import java.util.List;

public abstract class Kind extends Piece implements GoReadSoldier {

    static int[] k = new int[90];

    static {
        for (int i = 0; i < Board.W * Board.H; i++) {
            k[i] = 0;
        }
        k[3] = k[4] = k[5] = 1;
        k[12] = k[13] = k[14] = 1;
        k[21] = k[22] = k[23] = 1;

        k[84] = k[85] = k[86] = 1;
        k[75] = k[76] = k[77] = 1;
        k[66] = k[67] = k[68] = 1;

    }

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        int boardPos = now[who];
        {
            int e = boardPos - 1;
            check(list, e, now, board, who);
        }
        {
            int e = boardPos + 1;
            check(list, e, now, board, who);
        }

        {
            int e = boardPos + Board.W;
            check(list, e, now, board, who);
        }
        {
            int e = boardPos - Board.W;
            check(list, e, now, board, who);
        }
        {
            //王将照面
            if (opposite(boardPos, now, board, who)) {
                int black = (!this.isRed() ? boardPos : now[5]);
                int red = this.isRed() ? boardPos : now[21];
                int e = isRed() ? black : red;
                inRangeWillAdd(list, e, now, board, who);

            }

        }


    }

    private void check(List<Integer> list, int e, int[] now, int[] board, int who) {
        if (e >= 0 && e < 90 && k[e] == 1) {
            inRangeWillAdd(list, e, now, board, who);
        }
    }

    private boolean opposite(int e, int[] now, int[] board, int who) {
        int black = (!this.isRed() ? e : now[5]);
        int red = this.isRed() ? e : now[21];

        int blackX = black % Board.W;
        int redX = red % Board.W;
        if (blackX != redX) {
            return false;
        }
        for (int i = black + 9; i < red; i += 9) {
            if (board[i] != -1) {
                return false;
            }
        }
        return true;


    }

    @Override
    public int getScore() {
        return 500000;
    }

    @Override
    public boolean exchangeExpansion() {
        return true;
    }
}
