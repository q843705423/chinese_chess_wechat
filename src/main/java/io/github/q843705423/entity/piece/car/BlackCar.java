package io.github.q843705423.entity.piece.car;

import io.github.q843705423.entity.piece.common.Board;

public class BlackCar extends Car {

    public static int[][] positionScore = new int[10][9];

    static {
        for (int i = 0; i < Board.H; i++) {
            for (int j = 0; j < Board.W; j++) {
                positionScore[i][j] = RedCar.positionScore[Board.H - 1 - i][j];
            }
        }

    }

    @Override
    public String englishName() {
        return "r";
    }

    @Override
    public String chinaName() {
        return "車";
    }


    @Override
    public boolean isRed() {
        return false;
    }


    public int extraScore(int[] now, int[] board, int depth, int pos) {
        int boardPos = now[pos];
        int y = boardPos / Board.W;
        int x = boardPos % Board.W;
        int i = 10 * moveListCount(now, board, pos, y, x);
        return Math.min(positionScore[y][x] + i * 10, 220);
    }
}
