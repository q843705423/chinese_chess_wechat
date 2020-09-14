package io.github.q843705423.entity.piece.car;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void extraScore() {
        Protocol protocol = new Protocol("3arkb2/4a4/8b/9/9/9/9/B8/4A4/R1B1KA3 w - - 0 1");
        Board.init(protocol);
        int calculate = Main.calculate(Board.now, Board.board, 0);
        System.out.println(calculate);
    }


    @Test
    public void extraScore2() {
        Protocol protocol = new Protocol("4kab2/6R2/4b4/p1p1p3p/6p2/2P6/r7P/3C4B/3KA4/2B2A3 w - - 0 33");
        Board.init(protocol);
        String go = Board.go();
        System.out.println(go);
    }
}