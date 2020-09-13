package io.github.q843705423.entity.piece.common;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.util.Main;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static int W = 9;
    public static int H = 10;
    public static Protocol protocol = null;

    public static int[] now = null;
    public static int[] board = null;
    public static List<String> lines = new ArrayList<>();

    public static void init(String fen) {

        lines.clear();
        now = Main.init(fen);
        board = Main.initToBoard(now);

    }

    public static void init(Protocol protocol) {
        Board.protocol = protocol;
        init();

    }

    public static void init() {
        lines.clear();
        String fen = protocol.getPiecePlacementData();
        now = Main.init(fen);
        board = Main.initToBoard(now);
        List<String> moveList = protocol.getMoveList();
        for (String s : moveList) {
            Board.move(s);
        }

    }

    public static void autoMove(Protocol protocol) {
        List<String> moveList = protocol.getMoveList();
        for (int i = 0; i < moveList.size(); i++) {
            String moveInfo = moveList.get(i);
            move(moveInfo);
        }
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
        long startTime = System.currentTimeMillis();
        boolean playerIsRed = protocol.isRed();
        int[] dfs = Main.dfs(now, board, lines.size(), lines.size() + 6, playerIsRed, playerIsRed, new int[30], new int[30], new String[30], 0);
        if (dfs[1] == -1 || (playerIsRed && dfs[0] == -50000) || (!playerIsRed && dfs[0] == 50000)) {
            System.out.println("nobestmove");
            return "nobestmove";
        }
        System.out.println(dfs[0]);
        System.out.println(dfs[1]);
        System.out.println(dfs[2]);
        System.out.println(Main.zhao(dfs[1], dfs[2]));
        String trans = translateToCommand(now[dfs[1]], dfs[2]);
        now[dfs[1]] = dfs[2];
        lines.clear();
        String s1 = "bestmove " + trans;
        System.out.println(s1);
        ReplaceTableV2.show();
        long endTime = System.currentTimeMillis();
        System.out.println("spend Time:" + (endTime - startTime));
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

    public static int hashCode(int[] now) {

        if (now == null)
            return 0;

        int result = 1;
        for (int element : now)
            result = 101 * result + (element == -1 ? 33 : element);

        return result;
    }
}
