package io.github.q843705423.entity.piece.house;

import io.github.q843705423.entity.piece.common.Board;

public class BlackHouse extends House {

    static int[][] positionScore = new int[10][9];

    static {
        for (int i = 0; i < Board.H; i++) {
            for (int j = 0; j < Board.W; j++) {
                positionScore[i][j] = RedHouse.positionScore[Board.H - 1 - i][j];
            }
        }

    }

    @Override
    public String englishName() {
        return "n";
    }

    @Override
    public String chinaName() {
        return "馬";
    }


    @Override
    public boolean isRed() {
        return false;
    }

    @Override
    public int extraScore(int[] now, int[] board, int depth, int pos) {
        int boardPos = now[pos];
        int x = boardPos % Board.W;
        int y = boardPos / Board.W;
        return positionScore[y][x] + super.extraScore(now, board, depth, pos);
    }
}
