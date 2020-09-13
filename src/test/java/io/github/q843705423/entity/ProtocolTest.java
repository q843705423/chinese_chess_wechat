package io.github.q843705423.entity;

import io.github.q843705423.entity.piece.common.Board;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProtocolTest {

    @Test
    public void getPiecePlacementData() {
        Protocol protocol = new Protocol("position fen 4k4/9/9/9/9/9/9/3N5/9/4K4 w - - 0 1");
        assert protocol.getPiecePlacementData().equals("4k4/9/9/9/9/9/9/3N5/9/4K4");
        assert protocol.getActiveColor().equals("w");

    }

    @Test
    public void a() {
        String s = "position startpos moves c3c4 g6g5";
        Protocol protocol = new Protocol(s);
        Board.init(protocol);
        String go = Board.go();
        System.out.println(go);

    }

    @Test
    public void ab(){
        String s = "rnba1abnr/4k4/1c5c1/p1p5p/5P3/6p2/P1P3P1P/1C4NC1/4R4/RNBAKAB2 b - - 0 6";
        Board.init(new Protocol(s));
        String go = Board.go();
        System.out.println(go);
    }
}