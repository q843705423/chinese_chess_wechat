package io.github.q843705423.entity.piece.soldier;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

public class AbstractSoldierTest {

    @Test
    public void extraScore() {
        Protocol protocol = new Protocol("5k3/9/9/p3p1p1p/9/9/P1P1P1P1P/9/9/4K4 w - - 0 1");
        Board.init(protocol);
        System.out.println(Main.calculate(Board.now, Board.board, 0));
    }

    @Test
    public void s() {
        Protocol protocol = new Protocol("2bakab2/r8/1cn5n/6p2/p1p1p4/7N1/P3P2C1/1CN1B4/8R/3AKAB2 b - - 0 15");
        Board.init(protocol);
        String go = Board.go();
        System.out.println(go);
    }
}