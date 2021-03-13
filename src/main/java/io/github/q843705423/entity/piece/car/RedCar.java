package io.github.q843705423.entity.piece.car;

import io.github.q843705423.entity.piece.common.Board;

public class RedCar extends Car {

    static int positionScore[][] = new int[][]{
            {0, 20, 20, 200, 100, 200, 20, 20, 0,},
            {20, 60, 60, 210, 100, 210, 60, 60, 20,},
            {30, 30, 30, 40, 30, 40, 30, 40, 30,},
            {70, 80, 80, 200, 80, 100, 60, 80, 70,},
            {70, 80, 80, 200, 80, 200, 60, 80, 70,},

            {160, 180, 180, 220, 200, 220, 180, 180, 160,},
            {40, 170, 40, 60, 40, 180, 60, 60, 50,},
            {40, 120, 30, 60, 30, 180, 30, 40, 20,},
            {100, 120, 120, 180, 60, 180, 120, 120, 100,},
            {0, 120, 10, 60, 10, 40, 10, 60, 0,},
    };

    @Override
    public String englishName() {
        return "R";
    }

    @Override
    public String chinaName() {
        return "车";
    }


    @Override
    public boolean isRed() {
        return true;
    }

    public int extraScore(int[] now, int[] board, int depth, int pos) {
        int boardPos = now[pos];
        int y = boardPos / Board.W;
        int x = boardPos % Board.W;
        int i = 10 * moveListCount(now, board, pos, y, x);
        return Math.min(positionScore[y][x] + i * 10, 220);
    }
}
