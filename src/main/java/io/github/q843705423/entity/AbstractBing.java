package io.github.q843705423.entity;

import io.github.q843705423.entity.piece.common.Piece;

public abstract class AbstractBing extends Piece {

    public boolean isGoodX(int i) {
        return i >= 0 && i <= 8;
    }

    public boolean isGoodY(int y1) {
        return y1 >= 0 && y1 <= 9;
    }

    /*
        @Override
        public int basePrice(BaseInfo baseInfo) {
            return inMySelf(baseInfo) ? 2 : 5;
        }

        @Override
        public int movePrice(BaseInfo baseInfo) {

            int[] ints = moveList(baseInfo);
            int count = 0;
            for (int anInt : ints) {
                if (anInt == -1) {
                    break;
                }
                count++;
            }
            return count;
        }

        @Override
        public int attackPrice(BaseInfo baseInfo) {
            int[] ints = moveList(baseInfo);
            int value = 0;
            for (int pos : ints) {
                if (pos == -1) {
                    break;
                }
                int x = getX(pos);
                int y = getY(pos);

                int[][] board = baseInfo.getBoard();
                int i = board[y][x];
                int qizi = StaticQizi.getQizi(i, baseInfo);
                value += qizi;
            }
            return value;
        }

        @Override
        public int defendPrice(BaseInfo baseInfo) {
            return 0;
        }

        @Override
        public int[] moveList(BaseInfo baseInfo) {
            int x = baseInfo.getX();
            int y = baseInfo.getY();
            int[] ints = new int[3];
            Arrays.fill(ints, -1);
            int k = 0;
            int y1 = y + (baseInfo.isRed() ? -1 : 1);
            if (isGoodY(y1)) {
                ints[k++] = getPos(y1, x);
            }
            if (isGoodX(x - 1)) {

                ints[k++] = getPos(y, x - 1);
            }
            if (isGoodX(x + 1)) {
                ints[k] = getPos(y, x + 1);
            }
            return ints;
        }

        @Override
        public int[] eatList(BaseInfo baseInfo) {
            return moveList(baseInfo);
        }
    */

}
