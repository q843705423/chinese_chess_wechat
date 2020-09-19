package io.github.q843705423.entity.piece.soldier;

import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.GoReadSoldier;
import io.github.q843705423.entity.piece.common.Piece;

public abstract class AbstractSoldier extends Piece implements GoReadSoldier {

    /**
     * 当兵过河以后，离皇帝越近，分数越高
     *
     * @param now   now
     * @param board board
     * @param depth depth
     * @param pos   pos
     * @return 0
     */
    public int extraScore(int[] now, int[] board, int depth, int pos) {
        boolean goRiver = goRiver(now, pos);
        //如果兵过河，或者对面的兵没了
        if (goRiver || now[pos ^ 16] == -1) {
            int soldierBoardPos = now[pos];
            int otherKind = isRed() ? 5 : 21;
            int soldierX = soldierBoardPos % Board.W;
            int soldierY = soldierBoardPos / Board.W;
            int kindX = now[otherKind] % Board.W;
            int kindY = now[otherKind] / Board.W;

            int i = Math.abs(soldierX - kindX) + Math.abs(soldierY - kindY);
            return (goRiver ? 100 : 50) + (i == 0 ? 1000 : (400 / i));
        }
        return 0;
    }

}
