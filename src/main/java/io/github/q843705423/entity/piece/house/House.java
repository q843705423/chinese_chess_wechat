package io.github.q843705423.entity.piece.house;

import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.GoReadHouse;
import io.github.q843705423.entity.piece.common.Piece;

import java.util.List;

public abstract class House extends Piece implements GoReadHouse {
    static int[][] moving = {
            {1, -2},
            {1, 2},
            {2, 1},
            {2, -1},
            {-1, -2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
    };
    static int[][] movingLeg = {
            {0, -1},
            {0, 1},
            {1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
            {-1, 0},
            {-1, 0},
    };
    static int[][] houseLeg = new int[Board.W * Board.H][moving.length];
    static int[][] houseMove = new int[Board.W * Board.H][moving.length];

    static {
        int z = Board.W * Board.H;
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < moving.length; j++) {

                houseLeg[i][j] = -1;
                houseMove[i][j] = -1;
            }

        }
        for (int y = 0; y < Board.H; y++) {
            for (int x = 0; x < Board.W; x++) {
                for (int i = 0; i < moving.length; i++) {
                    int dy = y + moving[i][0];
                    int dx = x + moving[i][1];
                    int dyL = y + movingLeg[i][0];
                    int dxL = x + movingLeg[i][1];
                    if (dy >= 0 && dy < Board.H && dx >= 0 && dx < Board.W) {
                        houseMove[y * Board.W + x][i] = dy * Board.W + dx;
                        houseLeg[y * Board.W + x][i] = dyL * Board.W + dxL;

                    }
                }
            }
        }


    }

    @Override
    public void moveList(int[] now, int[] board, boolean isRedTurn, int who, int y, int x, List<Integer> list) {
        int pos = now[who];
        int[] legList = houseLeg[pos];
        int[] moveList = houseMove[pos];
        for (int i = 0; i < 8; i++) {
            if (legList[i] == -1) {
                continue;
            }
            if (board[legList[i]] == -1) {
                int e = moveList[i];
                inRangeWillAdd(list, e, now, board, who);
            }
        }

    }

    public int moveListCount(int[] now, int[] board, int who, int y, int x) {
        int count = 0;
        int pos = now[who];
        int[] legList = houseLeg[pos];
        int[] moveList = houseMove[pos];
        for (int i = 0; i < 8; i++) {
            if (legList[i] == -1) {
                continue;
            }
            if (board[legList[i]] == -1) {
                int e = moveList[i];
                if (inRange(e) && notSameColor(e, now, board, who)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int extraScore(int[] now, int[] board, int depth, int pos) {
        int y = pos / Board.W;
        int x = pos % Board.W;
        int count = moveListCount(now, board, pos, y, x);
        return -40 * (8 - count);
    }


    @Override
    public int getScore() {
        return 400;
    }

    @Override
    public boolean exchangeExpansion() {
        return true;
    }
}
