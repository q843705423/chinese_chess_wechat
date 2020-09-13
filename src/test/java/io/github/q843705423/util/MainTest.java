package io.github.q843705423.util;

import io.github.q843705423.entity.Protocol;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void init() {

        String fen = "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1";
//        String[] split = fen.split(" ");
        Protocol protocol = new Protocol(fen);
        int[] init = Main.init(protocol.getPiecePlacementData());
        int[] board = Main.initToBoard(init);


        int[] dfs = Main.dfs(init, board, 0, 4, protocol.isRed(), protocol.isRed(), new int[30], new int[30],new String[30], 0);
        System.out.println("score:"+dfs[0]);
        System.out.println("which:"+dfs[1]);
        System.out.println("where:"+dfs[2]);
        init[dfs[1]] = dfs[2];
        int[] newBoard = Main.initToBoard(init);
        String s = Main.boardToFen(newBoard, protocol.isRed(), protocol.getAllTurn() + 1);
        System.out.println(s);


//        Main.showChinese(newBoard);

    }

}