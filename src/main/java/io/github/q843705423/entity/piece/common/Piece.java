package io.github.q843705423.entity.piece.common;

import io.github.q843705423.interfaces.Nameable;
import io.github.q843705423.util.CanMove;

import java.util.List;

public abstract class Piece implements CanMove, Nameable, ColorAble, GoRead, ScoreAble, ExchangeExpansion {


    /**
     * 判断子力是否已经过河
     *
     * @param now now
     * @param pos pos
     * @return 过河
     */
    public boolean goRiver(int[] now, int pos) {
        int i = now[pos];
        int y = i / Board.W;
        if (y <= 4 && isRed()) {
            return true;
        }
        if (y > 4 && !isRed()) {
            return true;
        }
        return false;


    }


    public int getX(int now) {
        return now - ((now >> 4) << 4);
    }

    public int getPos(int y, int x) {
        return (y << 4) + x;

    }

    public int getY(int now) {
        return now >> 4;
    }

    public boolean inRange(int e) {
        return e >= 0 && e < Board.W * Board.H;
    }

    public static String show(int k) {
        return PieceFactory.index2Bing.get(k).toString();
    }

    public boolean inRangeWillAdd(List<Integer> list, int e, int[] now, int[] board, int who) {
        if (inRange(e) && notSameColor(e, now, board, who)) {
            list.add(e);
            return true;
        }
        return false;

    }

    protected boolean notSameColor(int e, int[] now, int[] board, int who) {
        if (e == -1) {
            return true;
        }
        int pos = board[e];
        if (pos == -1) {
            return true;
        }
        boolean red = isRed(who);
        boolean red1 = isRed(pos);
        return red ^ red1;

    }

    public boolean isRed(int pos) {
        return pos > 15;
    }

    public int extraScore(int[] now, int[] board, int depth, int pos) {
        return 0;
    }

    @Override
    public boolean exchangeExpansion() {
        return false;
    }
}
