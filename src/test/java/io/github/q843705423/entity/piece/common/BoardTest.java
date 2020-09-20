package io.github.q843705423.entity.piece.common;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class BoardTest {

    @Test
    public void boardInit() throws IOException {
//        String s ;
        Board.init(new Protocol("1rbakab2/8r/n1c3c2/C3p3p/2p3p2/3RP4/P1P3n1P/2N1B1NC1/8R/3AKAB2 w - - 0 11"));
        String go = Board.go();
//        System.out.println(Main.list);
    }

    @Test
    public void init() {
        String fen = "4k4/9/9/9/9/9/9/3N5/9/4K4 w - - 0 1";
        Board.init(fen);
        int[] board = Board.board;
        int[] now = Board.now;
        System.out.println(Arrays.toString(now));
        Main.showChinese(board);


    }

    /**
     * 想不懂，为啥要丢兵
     *
     * @throws IOException
     */
    @Test
    public void boardInit3() throws IOException {
        Board.init(new Protocol("r1bakabnr/9/3c3c1/p3p1p1p/2pn5/8P/P1P1P1P2/1CN3NC1/8R/R1BAKAB2 w - - 0 5"));
        String go = Board.go();
    }

    /**
     * 想不懂，为啥要丢车
     *
     * @throws IOException
     */
    @Test
    public void boardInit4() throws IOException {
        Board.init(new Protocol("3akab2/3rn4/bc6r/2P4NR/8c/P8/4P3P/1C2B1N2/4A4/R1B1KA3 b - - 0"));
        String go = Board.go();
    }

    /**
     * 残局
     *
     * @throws IOException
     */
    @Test
    public void boardInit5() throws IOException {
        Board.init(new Protocol("3k5/9/9/N8/9/9/9/9/9/4K4 w - - 0 1"));
        String go = Board.go();
    }
}