package io.github.q843705423.entity.piece.common;

import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void init() {
        String fen = "4k4/9/9/9/9/9/9/3N5/9/4K4 w - - 0 1";
        Board.init(fen);
        int[] board = Board.board;
        int[] now = Board.now;
        System.out.println(Arrays.toString(now));
        Main.showChinese(board);


    }
}