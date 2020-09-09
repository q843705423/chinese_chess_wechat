package io.github.q843705423.entity.piece;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.house.RedHouse;
import io.github.q843705423.entity.piece.kind.BlackKind;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.ArrayList;

public class HouseTest {

    @Test
    public void moveList() {
        RedHouse redHouse = new RedHouse();
        Protocol protocol = new Protocol("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
        int[] init = Main.init(protocol.getPiecePlacementData());
        int[] board = Main.initToBoard(init);
        ArrayList<Integer> list = new ArrayList<>();
        redHouse.moveList(init, board, true, 23, 9, 1, list);
        System.out.println(list);
    }

    @Test
    public void moveList2() {

        Protocol protocol = new Protocol("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
        int[] init = Main.init(protocol.getPiecePlacementData());
        int[] board = Main.initToBoard(init);
        ArrayList<Integer> list = new ArrayList<>();
        new BlackKind().moveList(init, board, true, 21, 9, 1, list);
        System.out.println(list);
    }
}