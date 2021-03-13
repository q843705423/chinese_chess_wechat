package io.github.q843705423.entity.piece.gun;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GunTest {

    @Test
    public void moveList() {
        Protocol protocol = new Protocol("2b2kb2/9/9/9/9/2p3p2/1rCn1nCr1/2p3p2/9/2cK2c2 w - - 0 1");
        Board.init(protocol);
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
    }

    @Test
    public void test1(){
        Protocol protocol = new Protocol("rnbakabnr/7c1/4c4/p1p1p1p1p/9/6P2/P1P1P3P/4C1NC1/8R/RNBAKAB2 b - - 0 3");
        Board.init(protocol);
        int i = new RedGun().extraScore(Board.now, Board.board, 0, 24);
        System.out.println(i);


    }
}