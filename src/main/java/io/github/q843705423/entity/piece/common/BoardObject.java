package io.github.q843705423.entity.piece.common;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.ScoreNode;
import io.github.q843705423.util.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.github.q843705423.entity.piece.common.PieceFactory.index2Bing;

public class BoardObject {

    /**
     * 棋盘宽度
     */
    public int W = 9;
    /**
     * 棋盘高度
     */
    public int H = 10;

    /**
     * ucci协议数据
     */
    public Protocol protocol = null;

    public int[] now = null;
    public int[] board = null;
    public List<String> lines = new ArrayList<>();


    public BoardObject(Protocol protocol) {
        init(protocol);

    }


    private void init(Protocol protocol) {
        this.protocol = protocol;
        init();

    }

    private void init() {
        lines.clear();
        String fen = protocol.getPiecePlacementData();
        now = Main.init(fen);
        board = Main.initToBoard(now);
        List<String> moveList = protocol.getMoveList();
        for (String s : moveList) {
            move(s);
        }

    }

    private void autoMove(Protocol protocol) {
        List<String> moveList = protocol.getMoveList();
        for (int i = 0; i < moveList.size(); i++) {
            String moveInfo = moveList.get(i);
            move(moveInfo);
        }
    }

    private void move(int beginY, int beginX, int endY, int endX) {
        int movePos = board[beginY * 9 + beginX];
        int beingMove = board[endY * 9 + endX];
        if (beingMove != -1) {
            now[beingMove] = -1;
        }
        board[beginY * W + beginX] = -1;
        board[endY * W + endX] = movePos;
        now[movePos] = endY * W + endX;
    }

    private void move(String moveInfo1) {

        lines.add(moveInfo1);

        String moveInfo = moveInfo1;
        char begin_y = moveInfo.charAt(0);
        char begin_x = moveInfo.charAt(1);
        char end_y = moveInfo.charAt(2);
        char end_x = moveInfo.charAt(3);

        move(W - (begin_x - '0'), begin_y - 'a', W - (end_x - '0'), end_y - 'a');
    }

    public String go() {
        long startTime = System.currentTimeMillis();
        boolean playerIsRed = protocol.isRed();
        int myDepth = (int) (Math.log(5000_0000) / Math.log(Main.getMaybeList(now, board, playerIsRed).size()));
        int hisDepth = (int) (Math.log(5000_0000) / Math.log(Main.getMaybeList(now, board, !playerIsRed).size()));
        int depth = Math.min(Math.min(myDepth, hisDepth), 10);
        System.out.printf("我方深度:%d对方深度%d,最终深度:%d\n", myDepth, hisDepth, depth);
        ScoreNode father = new ScoreNode();
        int[] dfs = Main.dfs(now, board, lines.size(), lines.size() + depth, playerIsRed, playerIsRed, new int[30], new int[30], new String[30], 0, father);
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

    public String translateToCommand(int pos, int value) {
        return trans(pos) + trans(value);
    }

    public String trans(int i) {
        int y = i / 9;
        int x = i - 9 * y;
        return ((char) ('a' + x) + "" + (W - y));

    }


    Random random = new Random();

    private int temp_x = -1;
    private int temp_y;
    private int temp_z;
    private int temp_h = 0;
    private int beginNowPos;//某个子
    private int endBoardPos;//移动到哪里去
    private int endNowPos;

    public void show() {
        Main.showChinese(board);
    }


    public boolean randomMove() {
        List<int[]> maybeList = Main.getMaybeList(now, board, protocol.isRed());
        if (maybeList.size() == 0) {
            return false;
        }
        int i = random.nextInt(maybeList.size());
        int[] whoToWhere = maybeList.get(i);
        move(whoToWhere);
        //输出着法
//        System.out.println(Main.zhao(whoToWhere[0], whoToWhere[1]));

        protocol.changeColor();
        return true;
    }

    public void move(int a, int b) {
        this.beginNowPos = a;
        this.endBoardPos = b;
        endNowPos = board[endBoardPos];
        temp_x = -1;
        temp_y = 0;
        temp_z = 0;
        temp_h = 0;
        if (endNowPos != -1) {//发生吃子
            temp_x = now[endNowPos];
            now[endNowPos] = -1;
        }
        temp_y = now[beginNowPos];
        now[beginNowPos] = endBoardPos;
        temp_z = board[endBoardPos];
        board[endBoardPos] = beginNowPos;
        if (temp_y != -1) {
            temp_h = board[temp_y];
            board[temp_y] = -1;
        }
    }

    /**
     * 进行这次移动
     *
     * @param whoToWhere whoToWhere
     */
    public void move(int[] whoToWhere) {
        move(whoToWhere[0], whoToWhere[1]);


    }

    /**
     * 撤销这次移动
     */
    public void unMove() {

        if (endNowPos != -1) {
            now[endNowPos] = temp_x;
        }
        now[beginNowPos] = temp_y;
        board[endBoardPos] = temp_z;
        if (temp_y != -1) {
            board[temp_y] = temp_h;
        }

    }

    /**
     * 显示当时局面可以走的着法
     */
    public void zhao() {
        List<int[]> maybeList = Main.getMaybeList(now, board, protocol.isRed());
        List<String> zhao = zhao(maybeList);
        for (int i = 0; i < zhao.size(); i++) {
            System.out.println(zhao.get(i) + ":" + Arrays.toString(maybeList.get(i)));

        }

    }

    public List<int[]> getMaybeList() {
        return Main.getMaybeList(now, board, protocol.isRed());
    }

    /**
     * 根据 who where获取着法的中文名
     *
     * @param who   哪个子
     * @param where 去哪里
     * @return 着法中文名
     */
    public String zhao(int who, int where) {
        Piece piece = index2Bing.get(who);
        return piece.read(now, board, who, where);

    }

    /**
     * 批量获取着法中文名
     *
     * @param whoAndWhere 谁到哪里数组
     * @return 着法中文数组
     */
    public List<String> zhao(List<int[]> whoAndWhere) {
        return whoAndWhere.stream().map(s -> zhao(s[0], s[1])).collect(Collectors.toList());

    }

    /**
     * @return 游戏是否结束
     */
    public boolean gameIsOver() {
        //当老帅或老将死了，游戏结束
        return now[5] == -1 || now[21] == -1;

    }

    /**
     * 赢还是输
     * 当为正数时为赢
     * 当为负数时为输
     * 当为0 时为平
     *
     * @return 0
     */
    public int winOrLose() {
        return (protocol.isRed() && now[5] == -1) || (!protocol.isRed() && now[21] == -1) ? 1 : -1;
    }


    public String getFen() {
        return Main.boardToFen(board, protocol.isRed(), 0);

    }

}
