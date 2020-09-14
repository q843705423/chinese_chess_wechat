package io.github.q843705423.entity.piece.soldier;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AbstractBingTest {

    @Test
    public void extraScore() {
        Protocol protocol = new Protocol("5k3/9/9/9/9/P3P3P/9/9/9/4K4 w - - 0 10");
        Board.init(protocol);
        int calculate = Main.calculate(Board.now,  Board.board, 0);
        System.out.println(calculate);

    }

    @Test
    public void ex(){
        Protocol protocol = new Protocol("2bakabr1/5r3/1cn3nc1/p3p4/2p3p1p/6P2/P1P1P4/1CN3N1C/3RA4/2BAK1B1R b - - 0 9");
        Board.init(protocol);
//        int calculate = Main.calculate(Board.now,  Board.board, 0);
/*
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
*/
        Board.go();
    }
}