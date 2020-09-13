package io.github.q843705423.entity.piece.common;

import static io.github.q843705423.entity.piece.common.PieceFactory.index2Bing;

public interface GoReadHouse extends GoRead {

    default String read(int[] now, int[] board, int who, int where) {

        Piece piece = index2Bing.get(who);
        int origin = now[who];
        int beginX = origin % Board.W;
        int beginY = origin / Board.W;
        int endX = where % Board.W;
        int endY = where / Board.W;
        String redPos = "0九八七六五四三二一";
        String blackPos = "0123456789";

        String changeRed = "0一二三四五六七八九";
        String changeBlack = "0123456789";

        String one = piece.chinaName();
        char two = piece.isRed() ? redPos.charAt(beginX + 1) : blackPos.charAt(beginX + 1);
        String three = "";
        if (beginY == endY) {
            three = "平";
        } else {
            three = (piece.isRed() && beginY > endY) || ((!piece.isRed()) && beginY < endY) ? "进" : "退";
        }
        char four;
        if (three.equals("平")) {

            four = piece.isRed() ? redPos.charAt(Math.abs(endX + 1)) : blackPos.charAt(Math.abs(endX + 1));
        } else {

            four = piece.isRed() ? redPos.charAt(Math.abs(endX + 1)) : blackPos.charAt(Math.abs(endX + 1));
        }
        return one + two + three + four;
    }
}
