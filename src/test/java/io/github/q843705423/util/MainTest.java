package io.github.q843705423.util;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.ScoreNode;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void init() {

        String fen = "r1bakabnr/9/3c3c1/p3p1p1p/2pn5/8P/P1P1P1P2/1CN3NC1/8R/R1BAKAB2 w - - 0 5";
//        String[] split = fen.split(" ");
        Protocol protocol = new Protocol(fen);
        int[] init = Main.init(protocol.getPiecePlacementData());
        int[] board = Main.initToBoard(init);


        int[] dfs = Main.dfs(init, board, 0, 4, protocol.isRed(), protocol.isRed(), new int[30], new int[30],new String[30], 0,new ScoreNode());
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