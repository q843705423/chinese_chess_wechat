package io.github.q843705423.entity.piece.house;

import io.github.q843705423.entity.piece.common.Board;

public class RedHouse extends House {

    static int[][] positionScore = new int[][]{
            {60, 60, 60, 60, 60, 60, 60, 60, 60,},
            {60, 60, 250, 60, 60, 60, 250, 60, 60,},
            {150, 60, 60, 60, 60, 60, 60, 60, 150,},
            {60, 180, 100, 210, 60, 210, 100, 180, 60,},
            {60, 150, 60, 180, 60, 180, 60, 150, 60,},

            {0, 50, 200, 90, 50, 90, 200, 50, 0,},
            {0, 50, 50, 60, 40, 60, 60, 60, 0,},
            {120, 50, 130, 120, 50, 120, 130, 50, 120,},
            {0, 0, 0, 20, 80, 20, 80, 60, -30,},
            {-30, -50, 0, 0, 0, 0, 0, -50, -30,},
    };

    @Override
    public String englishName() {
        return "N";
    }

    @Override
    public String chinaName() {
        return "马";
    }


    @Override
    public boolean isRed() {
        return true;
    }

    @Override
    public int extraScore(int[] now, int[] board, int depth, int pos) {
        int boardPos = now[pos];
        int x = boardPos % Board.W;
        int y = boardPos / Board.W;
        return positionScore[y][x] + super.extraScore(now, board, depth, pos);
    }
}
