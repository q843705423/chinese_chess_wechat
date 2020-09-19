package io.github.q843705423.entity.piece.car;

import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.GoReadSoldier;
import io.github.q843705423.entity.piece.common.Piece;

import java.util.List;


public abstract class Car extends Piece implements GoReadSoldier {

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        for (int dy = y - 1; dy >= 0; dy--) {
            int e = dy * Board.W + x;
            boolean b = inRangeWillAdd(list, e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }
        }
        for (int dy = y + 1; dy < Board.H; dy++) {
            int e = dy * Board.W + x;
            boolean b = inRangeWillAdd(list, e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }
        }
        for (int dx = x + 1; dx < Board.W; dx++) {
            int e = y * Board.W + dx;
            boolean b = inRangeWillAdd(list, e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }

        }

        for (int dx = x - 1; dx >= 0; dx--) {
            int e = y * Board.W + dx;
            boolean b = inRangeWillAdd(list, e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }

        }

    }

    public int moveListCount(int[] now, int[] board, int who, int y, int x) {
        int count = 0;
        for (int dy = y - 1; dy >= 0; dy--) {
            int e = dy * Board.W + x;
            boolean b = inRange(e) && notSameColor(e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }
            count++;
        }
        for (int dy = y + 1; dy < Board.H; dy++) {
            int e = dy * Board.W + x;
            boolean b = inRange(e) && notSameColor(e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }
            count++;
        }
        for (int dx = x + 1; dx < Board.W; dx++) {
            int e = y * Board.W + dx;
            boolean b = inRange(e) && notSameColor(e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }
            count++;

        }

        for (int dx = x - 1; dx >= 0; dx--) {
            int e = y * Board.W + dx;
            boolean b = inRange(e) && notSameColor(e, now, board, who);
            if (!b || board[e] != -1) {
                break;
            }
            count++;

        }
        return count;

    }

    @Override
    public int extraScore(int[] now, int[] board, int depth, int pos) {
        int boardPos = now[pos];
        int y = boardPos / Board.W;
        int x = boardPos % Board.W;
        return 3 * moveListCount(now, board, pos, y, x);
    }
}
