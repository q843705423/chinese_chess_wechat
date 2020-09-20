package io.github.q843705423.util;

import io.github.q843705423.entity.ScoreNode;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.entity.piece.common.Piece;
import io.github.q843705423.entity.piece.common.ReplaceTableV2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static io.github.q843705423.entity.piece.common.PieceFactory.index2Bing;


public class Main {

    static Map<String, Integer> start = new HashMap<>();


    /**
     * @param board 16 * 16
     * @param now   32
     *              卒卒卒卒卒将马马
     *              炮炮车车相相士士
     *              兵兵兵兵兵帅马马
     *              炮炮车车相相士士
     * @param h     10
     * @param w     9
     */
    static {

        start.put("r", 10);
        start.put("n", 6);
        start.put("b", 12);
        start.put("a", 14);
        start.put("k", 5);
        start.put("c", 8);
        start.put("p", 0);

        start.put("R", 26);
        start.put("N", 22);
        start.put("B", 28);
        start.put("A", 30);
        start.put("K", 21);
        start.put("C", 24);
        start.put("P", 16);
    }

/*
    static int[] scope = new int[]{
            10, 10, 10, 10, 10, 10000, 400, 400,
            450, 450, 900, 900, 200, 200, 150, 150,
            10, 10, 10, 10, 10, 10000, 400, 400,
            450, 450, 900, 900, 200, 200, 150, 150,
    };
*/

    //    public static Map<String, String> map = new HashMap<>();
    public static List<String[]> list = new ArrayList<>();

    /**
     * dfs返回值到底是什么
     * now表示当前局面，每个子对应棋盘上的位置
     * board表示当前棋盘
     * depth表示当前深度
     * maxDepth表示最大深度
     * isRedTurn 表示现在是谁的回合
     * playerIsRed 表示最开始是谁 ？ 这个有用吗？
     * whos表示每部棋是谁走的数组
     * wheres 表示走到哪里数组
     * whoLength 表示数组长度
     * dfs返回的结果到底是什么?
     * dfs[0]当前局面的最高得分
     * dfs[1]当前局面的最好走子子力,第一步走的是啥
     * dfs[2]当前局面的最好走子位置,第一步走到了哪
     *
     * @param now       32
     * @param board     10*9
     * @param depth     当前深度
     * @param maxDepth  最大深度
     * @param isRedTurn 是红方
     * @return [0->得分] [1->谁] [2->走到哪里]
     */
    public static int[] dfs(int[] now, int[] board, int baseDepth, int depth, int maxDepth, boolean isRedTurn, boolean playerIsRed, int[] whos, int[] wheres, String[] chinese, int whoLength, ScoreNode root) {

        if (now[5] == -1) {
            return new int[]{50000 - depth, whos[0], wheres[0], depth};
        }
        if (now[21] == -1) {
            return new int[]{-50000 + depth, whos[0], wheres[0], depth};
        }

        if (depth >= maxDepth) {
            int score = calculate(now, board, depth);
            int[] ints = {score, whos[0], wheres[0], depth};
            ReplaceTableV2.putMap(now, ints[1], ints[2], ints[0], depth, isRedTurn, isRedTurn);
            return ints;
        } else {
            int[] maxOrMin = new int[]{(isRedTurn ? -500_0000 : 500_0000), -1, -1, depth};
            List<int[]> maybeList = getMaybeList(now, board, isRedTurn);

            //如果是第一着,且有着法被禁止
            if (baseDepth == depth && Board.protocol.getBanEn() != null) {
                int[] banInt = Board.moveZhao(Board.protocol.getBanEn());
                maybeList.removeIf(ints -> ints[0] == banInt[0] && ints[1] == banInt[1]);
            }

            for (int j = 0; j < maybeList.size(); j++) {
                int[] whoToWhere = maybeList.get(j);
                int beginNowPos = whoToWhere[0];//某个子
                int endBoardPos = whoToWhere[1];//移动到哪里去

                String zhao = zhao(beginNowPos, endBoardPos);
                chinese[whoLength] = zhao;

                //辅助用于判断捉子拓展
//                int originZhuoCount = getZhuoZiCount(now, board, isRedTurn, beginNowPos);

                int endNowPos = 0;
                try {
                    endNowPos = board[endBoardPos];
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int temp_x = -1;
                int temp_y;
                int temp_z;
                int temp_h = 0;

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


                whos[whoLength] = beginNowPos;
                wheres[whoLength] = endBoardPos;
                whoLength++;

                int[] map = ReplaceTableV2.getMap(now, depth, isRedTurn);//先去找历史表
                ScoreNode maybeNode = new ScoreNode(zhao);
                root.addChild(maybeNode);

                int[] dfs;

                if (map == null) {
                    int newMaxDepth = maxDepth;
                    if (maxDepth + 1 - baseDepth <= 10) {

                        //进行兑子扩展
                        Piece piece = index2Bing.get(endNowPos);
                        if (endNowPos != -1 && piece.exchangeExpansion() && depth == maxDepth - 1) {
//                            System.out.println((piece.isRed() ? "红" : "黑") + piece.chinaName() + "被吃,进行兑子拓展");
                            newMaxDepth = maxDepth + 1;
                        } /*else {
                            //进行捉子拓展
                            int count = getZhuoZiCount(now, board, isRedTurn, beginNowPos);
                            if (count > originZhuoCount &&  depth == maxDepth - 1) {
                                System.out.println("多子被捉,进行捉子拓展");
                                newMaxDepth = maxDepth + 1;
                            }

                        }*/
                    }

                    dfs = dfs(now, board, baseDepth, depth + 1, newMaxDepth, !isRedTurn, playerIsRed, whos, wheres, chinese, whoLength, maybeNode);

                } else {
                    dfs = new int[]{map[0], whos[0], wheres[0], map[3] + 1};


                }
                maybeNode.setScore(dfs[0]);


                if (isRedTurn) {
                    if ((maxOrMin[0] < dfs[0]) || (maxOrMin[0] == dfs[0] && maxOrMin[3] > dfs[3])) {
                        maxOrMin = dfs;


//                            maxOrMin = new int[]{dfs[0],whos[0],wheres[0]};
                    }
                } else {
                    if (maxOrMin[0] > dfs[0] || (maxOrMin[0] == dfs[0] && maxOrMin[3] > dfs[3])) {
                        maxOrMin = dfs;
//                            maxOrMin = new int[]{dfs[0],whos[0],wheres[0]};
                    }

                }
//                root.setScore(maxOrMin[0]);

                ReplaceTableV2.putMap(now, dfs[1], dfs[2], dfs[0], depth, isRedTurn, playerIsRed);

                whoLength--;
                wheres[whoLength] = -1;
                whos[whoLength] = -1;
                chinese[whoLength] = "";

                if (endNowPos != -1) {
                    now[endNowPos] = temp_x;
                }
                now[beginNowPos] = temp_y;
                board[endBoardPos] = temp_z;
                if (temp_y != -1) {
                    board[temp_y] = temp_h;
                }

                //判断是否可以进行a-b裁剪
                if (root.getFather() != null && root.getFather() != null) {
                    List<ScoreNode> brotherList = root.getFather().getChildList();
                    if (brotherList != null && brotherList.size() > 1) {
                        if (!isRedTurn) {
                            int maxScore = -500_0000;
                            for (int i = 0; i < brotherList.size(); i++) {
                                if (brotherList.get(i) != root) {
                                    maxScore = Math.max(maxScore, brotherList.get(i).getScore());
                                }

                            }
                            if (maxOrMin[0] < maxScore) {
//                                System.out.println("发生a-b裁剪");
                                return maxOrMin;
                            }

                        } else {
                            int minScore = 500_0000;
                            for (int i = 0; i < brotherList.size(); i++) {
                                if (brotherList.get(i) != root) {
                                    minScore = Math.min(minScore, brotherList.get(i).getScore());
                                }
                            }
                            if (maxOrMin[0] > minScore) {
//                                System.out.println("发生a-b裁剪");
                                return maxOrMin;
                            }


                        }

                    }

                }


            }
            return maxOrMin;
//            return maxOrMin;

        }


    }

    private static int getZhuoZiCount(int[] now, int[] board, boolean isRedTurn, int beginNowPos) {
        CanMove canMove = index2Bing.get(beginNowPos);
        ArrayList<Integer> list = new ArrayList<>();
        canMove.moveList(now, board, isRedTurn, beginNowPos, now[beginNowPos] / Board.W, now[beginNowPos] % Board.W, list);
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (board[list.get(i)] != -1 && index2Bing.get(board[list.get(i)]).exchangeExpansion()) {
                count++;
                break;
            }

        }
        return count;
    }

    public static FileWriter fileWriter;

    private static void show(String[] chinese, int a, int b) {
        for (int i = 0; i < chinese.length; i++) {
            if (chinese[i] == null || chinese[i].equals("")) {
                break;
            }
//            System.out.print(chinese[i] + " ");
            try {
                fileWriter.write(chinese[i] + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        System.out.println(" " + a + "->" + b);
        try {
            fileWriter.write(" " + "->" + b + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tip(int depth, String[] chinese, int[] dfs, String[] k, int z) {
        if (k.length == depth) {
            boolean x = true;
            for (int i = 0; i < k.length; i++) {
                if (!chinese[i].equals(k[i])) {
                    x = false;
                    break;
                }

            }
            if (x) {
                System.out.println("===============================");
                System.out.println(chinese[k.length] + ":" + dfs[0] + " " + (z == 1 ? "置换表" : ""));
            }

        }
    }

    public static List<int[]> getMaybeList(int[] now, int[] board, boolean isRed) {
        int begin = isRed ? 16 : 0;
        int end = isRed ? 32 : 16;
        List<int[]> moveOp = new ArrayList<>();

        for (int i = begin; i < end; i++) {
            int who = i;
            int y = now[who] / 9;
            int x = now[who] - y * 9;
            int originWhere = now[i];
            if (originWhere != -1) {//没死
                CanMove canMove = index2Bing.get(i);
                ArrayList<Integer> positions = new ArrayList<>();
                canMove.moveList(now, board, isRed, who, y, x, positions);
                for (Integer position : positions) {
                    if (position >= 90) {
                        System.out.println("");
                    } else {

                        moveOp.add(new int[]{i, position});
                    }
                }
            }
        }
        moveOp.sort((o1, o2) -> {
            int score1 = index2Bing.get(o1[0]).getScore();
            int score2 = index2Bing.get(o2[0]).getScore();
            int board1 = board[o1[1]] == -1 ? 0 : index2Bing.get(board[o1[1]]).getScore();
            int board2 = board[o2[1]] == -1 ? 0 : index2Bing.get(board[o2[1]]).getScore();
            return Integer.compare(board2 << 4 + score2, board1 << 4 + score1);
        });

        return moveOp;

    }

    private static Random random = new Random();

    /**
     * 计算双方得分
     *
     * @param now 当前双方子力
     * @return 得分
     */
    public static int calculate(int[] now, int[] board, int depth) {
        int upSum = 0;
        for (int i = 0; i < 16; i++) {
            upSum = getSum(now, board, depth, upSum, i);
        }
        int downSum = 0;
        for (int i = 16; i < 32; i++) {
            downSum = getSum(now, board, depth, downSum, i);
        }
        return downSum - upSum;
    }

    private static int getSum(int[] now, int[] board, int depth, int downSum, int i) {
        if (now[i] != -1) {
            Piece piece = index2Bing.get(i);
            downSum += (now[i] == -1 ? 0 : 1) * piece.getScore();
            downSum += piece.extraScore(now, board, depth, i);
        }
        return downSum;
    }


    /**
     * 下标表示不同的子力
     * 值表示每个子力的位置
     * -1表示子力已经不在了
     *
     * @param fen fen
     * @return int[]
     */
    public static int[] init(String fen) {
        int[] pos = new int[32];
        Arrays.fill(pos, -1);
        int x = 0;
        int y = 0;
        for (int i = 0; i < fen.length(); i++) {
            char c = fen.charAt(i);
            if (c == ' ') {
                break;
            }
            if (c == '/') {
                y++;
                x = 0;
                continue;
            }
            Integer integer = start.get(c + "");
            if (integer != null) {
                for (int k = 0; k < 5; k++) {
                    if (pos[integer + k] == -1) {
                        pos[integer + k] = (y * 9) + (x++);
                        break;
                    }

                }
            } else {
                int num0 = c - '0';
                x += num0;
            }
        }


        return pos;
    }


    public static int[] initToBoard(int[] init) {
        int[] board = new int[90];
        for (int i = 0; i < 90; i++) {
            board[i] = -1;
        }
        for (int i = 0; i < 32; i++) {
            if (init[i] != -1) {
                board[init[i]] = i;
            }
        }
        return board;
    }

    public static String boardToFen(int[] board, boolean isRed, int turnNum) {
        StringBuilder res = new StringBuilder();
        int blackNum = 0;
        for (int i = 0; i < board.length; i++) {
            if (i % 9 == 0 && (res.length() > 0)) {
                if (blackNum != 0) {
                    res.append(blackNum);
                    blackNum = 0;
                }
                res.append("/");


            }
            int pos = board[i];
            if (pos == -1) {
                blackNum++;

            } else {
                if (blackNum != 0) {
                    res.append(blackNum);
                    blackNum = 0;
                }
                Piece piece = index2Bing.get(pos);
                String name = piece.englishName();
                res.append(name);
                blackNum = 0;
            }


        }
        return res.toString() + " " + (isRed ? "b" : "w") + " - - " + " 0 " + (turnNum);
    }


    public static void show(int[] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                int k = board[i * 9 + j];
                System.out.print(k);
            }
            System.out.println();
        }
    }

    public static List<String> zhao(List<int[]> whoAndWhere) {
        return whoAndWhere.stream().map(s -> zhao(s[0], s[1])).collect(Collectors.toList());

    }


    public static String zhao(int who, int where) {
        Piece piece = index2Bing.get(who);
        return piece.read(Board.now, Board.board, who, where);

    }

    public static void showChinese(int[] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                int k = board[i * 9 + j];
                if (k == -1) {
                    System.out.print("无");
                } else {

                    String s = index2Bing.get(k).chinaName();
                    System.out.print(s);
                }
            }
            System.out.println();
        }
        System.out.println("====================");

    }


}
