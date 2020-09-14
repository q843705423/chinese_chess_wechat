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
}