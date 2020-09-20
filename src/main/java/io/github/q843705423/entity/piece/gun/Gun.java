package io.github.q843705423.entity.piece.gun;

import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.GoReadSoldier;
import io.github.q843705423.entity.piece.common.Piece;

import java.util.List;

public abstract class Gun extends Piece implements GoReadSoldier {

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        {
            int through = 0;
            for (int dy = y + 1; dy < Board.H; dy++) {
                int e = dy * Board.W + x;
                through = getThrough(now, board, who, list, through, e);
                if (through == 2) {
                    break;
                }
            }
        }

        {
            int through = 0;
            for (int dy = y - 1; dy >= 0; dy--) {
                int e = dy * Board.W + x;
                through = getThrough(now, board, who, list, through, e);
                if (through == 2) {
                    break;
                }
            }
        }
        {
            int through = 0;
            for (int dx = x + 1; dx < Board.W; dx++) {
                int e = y * Board.W + dx;
                through = getThrough(now, board, who, list, through, e);
                if (through == 2) {
                    break;
                }
            }
        }

        {
            int through = 0;
            for (int dx = x - 1; dx >= 0; dx--) {
                int e = y * Board.W + dx;
                through = getThrough(now, board, who, list, through, e);
                if (through == 2) {
                    break;
                }
            }
        }
    }

    private int getThrough(int[] now, int[] board, int who, List<Integer> list, int through, int e) {
        if (through == 0 && board[e] == -1) {
            list.add(e);
        }
        if (board[e] != -1 && through == 1 && notSameColor(e, now, board, who)) {
            list.add(e);
        }
        if (board[e] != -1) {
            through++;
        }
        return through;
    }

    /**
     * @param now
     * @param board
     * @param depth
     * @param pos
     * @return
     */
    public int extraScore(int[] now, int[] board, int depth, int pos) {
        int boardPos = now[pos];
        int x = boardPos % Board.W;
        if (x == 4) {
            return 150;

        }
        return 0;
//        return goRiver(now, pos) ? -30 : 30;
    }

    @Override
    public int getScore() {
        return 500;
    }

    @Override
    public boolean exchangeExpansion() {
        return true;
    }
}
