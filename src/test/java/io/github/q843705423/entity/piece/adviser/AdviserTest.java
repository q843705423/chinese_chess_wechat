package io.github.q843705423.entity.piece.adviser;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AdviserTest {

    @Test
    public void moveList() {
        Protocol protocol = new Protocol("3a1k3/9/5a3/9/9/9/9/9/9/3AKA3 w - - 0 1");
        Board.init(protocol);
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
    }
    @Test
    public void moveList2() {
        Protocol protocol = new Protocol("3a1k3/9/5a3/9/9/9/9/9/9/3AKA3 b - - 0 1");
        Board.init(protocol);
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
    }
    @Test
    public void moveList3() {
        Protocol protocol = new Protocol("3k5/9/9/9/9/9/9/9/4A4/5K3 w - - 0 1");
        Board.init(protocol);
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
    }
}