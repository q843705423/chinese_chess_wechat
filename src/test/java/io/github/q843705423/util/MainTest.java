package io.github.q843705423.util;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import org.junit.Test;

import java.util.List;

public class MainTest {

    @Test
    public void calcluate() {

        Protocol protocol = new Protocol("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
        Board.init(protocol);
        int calculate = Main.calculate(Board.now, Board.board, 0);
        System.out.println(calculate);
    }


    /**
     * 禁止着法
     */
    @Test
    public void test2() {
        Protocol protocol = new Protocol("position startpos moves h2e2 b7e7 h0g2 b9c7 i0h0 h7g7 b0c2 g7g3 a0b0 c6c5 b2a2 h9g7 g0i2 g6g5 b0b4 g3c3 d0e1 c3c0 b4b0 c0c1 h0h4 c5c4 b0c0 c1d1 h4c4 a9a7 c0d0 d1c1 c2b4 a7b7 a2b2 c7b5 c4c1 b7c7 c1c7 b5c7 b4c6 e7e8 d0d8 g7f5 b2c2 f5g3 c2c7 g3e2 c7b7 e2g1 e0d0 e8i8 b7b9 f9e8 d8d4 g1i2 g2f4 i2g3 f4e6 i8i3 d4f4 i9i7 c6b8 i3e3 e6g5 g9e7 f4h4 e7g5 h4h9 e8f9 h9h2 i7c7 h2d2 c7c0 d0d1 c0c1 d1d0 c1c0 d0d1 c0c1 d1d0 c1c0 d0d1");
        Board.init(protocol);
        Board.go();


    }

    public static void k(String moveInfo) {

        char begin_y = moveInfo.charAt(0);
        char begin_x = moveInfo.charAt(1);
        char end_y = moveInfo.charAt(2);
        char end_x = moveInfo.charAt(3);

        int[] a = new int[]{Board.W - (begin_x - '0'), begin_y - 'a', Board.W - (end_x - '0'), end_y - 'a'};
        System.out.println(a[0]);
        System.out.println(a[1]);
    }
}