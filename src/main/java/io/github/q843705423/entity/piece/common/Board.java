package io.github.q843705423.entity.piece.common;

import io.github.q843705423.util.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {

    public static int W = 9;
    public static int H = 10;

    public static int[] now = null;
    public static int[] board = null;
    public static List<String> lines = new ArrayList<>();

    public static void init() {
        lines.clear();

        String fen = "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1";
        now = Main.init(fen);
        board = Main.initToBoard(now);

    }

    public static void move(int beginY, int beginX, int endY, int endX) {
        int movePos = board[beginY * 9 + beginX];
        int beingMove = board[endY * 9 + endX];
        if (beingMove != -1) {
            now[beingMove] = -1;
        }
        board[beginY * W + beginX] = -1;
        board[endY * W + endX] = movePos;
        now[movePos] = endY * W + endX;
    }

    public static void move(String moveInfo1) {

        lines.add(moveInfo1);

        String moveInfo = moveInfo1;
        char begin_y = moveInfo.charAt(0);
        char begin_x = moveInfo.charAt(1);
        char end_y = moveInfo.charAt(2);
        char end_x = moveInfo.charAt(3);

        Board.move(W - (begin_x - '0'), begin_y - 'a', W - (end_x - '0'), end_y - 'a');
    }

    public static String go() {
        ReplaceTable.map = new HashMap<>();
        boolean playerIsRed = lines.size() % 2 == 0;
        int[] dfs = Main.dfs(now, board, 0, 8, playerIsRed, playerIsRed, new int[30], new int[30], 0);
        System.out.println(dfs[0]);
        System.out.println(dfs[1]);
        System.out.println(dfs[2]);
        if(dfs[1]==-1){
            return "nobestmove";
        }
        String trans = translateToCommand(now[dfs[1]], dfs[2]);
        now[dfs[1]] = dfs[2];
        int[] newBoard = Main.initToBoard(now);
//        String str = Main.boardToFen(newBoard, playerIsRed, 1);
        StringBuilder s = new StringBuilder("position startpos moves");
        for (int i = 0; i < lines.size(); i++) {
            s.append(" ").append(lines.get(i));
        }
        s.append(" ").append(trans);
//        System.out.println(s);
//        return s.toString();
        lines.clear();
        String s1 = "bestmove " + trans;
        System.out.println(s1);
        return s1;

    }

    public static String translateToCommand(int pos, int value) {
        return trans(pos) + trans(value);
    }

    public static String trans(int i) {
        int y = i / 9;
        int x = i - 9 * y;
        return ((char) ('a' + x) + "" + (W - y));

    }
}
