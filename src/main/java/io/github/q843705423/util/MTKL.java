package io.github.q843705423.util;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.BoardObject;

/**
 *
 */
public class MTKL {


    public static void main(String[] args) {
//        Node node = new Node("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
        Node node = new Node("9/9/3k5/9/9/3N5/9/9/9/4K4 w - - 0 1");
        long time = System.currentTimeMillis();
        node.searchMore(200_000);
        Node node1 = node.chooseGoodNode(node);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - time);
        System.out.println(node1.getZhaoName());

/*
        MTKL mtkl = new MTKL();
        int i = mtkl.playOut("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
        System.out.println(i);
*/

    }

    private static int howDie(String fen) {
        Protocol protocol = new Protocol(fen);
        Board.init(protocol);
        BoardObject boardObject = new BoardObject(protocol);
        int k = 0;
        while (true) {
            k++;
            mock(boardObject);
            boolean b = boardObject.gameIsOver();
            if (b) {
                break;
            }
        }
        return k;
    }

    public static void mock(BoardObject boardObject) {
        boardObject.randomMove();

    }

    public int playOut(String fen) {
        Protocol protocol = new Protocol(fen);
        Board.init(protocol);
        BoardObject boardObject = new BoardObject(protocol);
        while (true) {
            mock(boardObject);
            boolean b = boardObject.gameIsOver();
            if (b) {
                break;
            }
        }
        return boardObject.winOrLose();
    }

    public void test() {
        long beginTime = System.currentTimeMillis();
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int all = 10_000;
        for (int i = 0; i < all; i++) {

//            int k = howDie("9/4k4/9/9/9/3N5/9/9/9/4K4 w - - 0 1");
            int k = howDie("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
            sum += k;
            max = Math.max(max, k);
            min = Math.min(min, k);

        }
        long end = System.currentTimeMillis();
        System.out.printf("进行%d句游戏,平均%.2f着后，游戏结束,其中最多的着法局为%d着，最低的着法局为%d着\n", all, sum * 1.0 / all, min, max);

        System.out.printf("计算耗时:%d\n", end - beginTime);

    }
}
