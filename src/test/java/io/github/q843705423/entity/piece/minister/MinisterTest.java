package io.github.q843705423.entity.piece.minister;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MinisterTest {

    @Test
    public void moveList() {
        Protocol protocol = new Protocol("3k2b2/9/9/9/2b6/6B2/9/B8/9/4K4 w - - 0 1");
        Board.init(protocol);
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
    }

    @Test
    public void moveList2() {
        Protocol protocol = new Protocol("2baka3/7c1/3cb1n2/p3p3p/2Rn2p2/1p3r3/P3P1PCP/1C2N4/8N/2BAKAB2 b - - 0 20");
        Board.init(protocol);
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
    }
}