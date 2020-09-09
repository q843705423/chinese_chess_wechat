package io.github.q843705423.util;

import io.github.q843705423.entity.piece.common.Piece;
import io.github.q843705423.entity.piece.common.ReplaceTable;

import java.util.*;

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

    static int[] scope = new int[]{
            10, 10, 10, 10, 10, 10000, 400, 400,
            450, 450, 900, 900, 200, 200, 150, 150,
            10, 10, 10, 10, 10, 10000, 400, 400,
            450, 450, 900, 900, 200, 200, 150, 150,
    };


    /**
     * @param now       32
     * @param board     10*9
     * @param depth     当前深度
     * @param maxDepth  最大深度
     * @param isRedTurn 是红方
     * @return [0->得分] [1->谁] [2->走到哪里]
     */
    public static int[] dfs(int[] now, int[] board, int depth, int maxDepth, boolean isRedTurn, boolean playerIsRed, int[] whos, int[] wheres, int whoLength) {
        if (depth == maxDepth) {
/*
            System.out.println("---------------------------------");
            Main.showChinese(board);
*/
            int calculate = calculate(now, playerIsRed);
//            System.out.println("now score is:" + calculate);
//            System.out.println("---------------------------------");
            return new int[]{calculate, whos[0], wheres[0]};
        } else {
//            int[] max = new int[]{-10000, -1, -1};
            int[] maxOrMin = new int[]{(playerIsRed == isRedTurn ? -500_0000 : 500_0000), -1, -1};
            List<int[]> maybeList = getMaybeList(now, board, isRedTurn);
            for (int j = 0; j < maybeList.size(); j++) {
                int[] whoTowhere = maybeList.get(j);
                int beginNowPos = whoTowhere[0];//某个子
                int endBoardPos = whoTowhere[1];//移动到哪里去


                int endNowPos = 0;
                try {
                    endNowPos = board[endBoardPos];
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                int temp_x = now[endNowPos];
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

                int[] dfs;
                dfs = dfs(now, board, depth + 1, maxDepth, !isRedTurn, playerIsRed, whos, wheres, whoLength);
                if (dfs[1] != -1) {
                    if (isRedTurn == playerIsRed) {
                        if (maxOrMin[0] <= dfs[0]) {
                            maxOrMin = dfs;
                        }
                    } else {
                        if (maxOrMin[0] >= dfs[0]) {
                            maxOrMin = dfs;
                        }

                    }
                }


                whoLength--;
                wheres[whoLength] = -1;
                whos[whoLength] = -1;

                if (endNowPos != -1) {
                    now[endNowPos] = temp_x;
                }
                now[beginNowPos] = temp_y;
                board[endBoardPos] = temp_z;
                if (temp_y != -1) {
                    board[temp_y] = temp_h;
                }


            }
            return maxOrMin;

        }


    }

    private static List<int[]> getMaybeList(int[] now, int[] board, boolean isRed) {
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
        return moveOp;

    }


    /**
     * 计算双方得分
     *
     * @param now 当前双方子力
     * @param red 是否是红发
     * @return 得分
     */
    public static int calculate(int[] now, boolean red) {
        int upSum = 0;
        for (int i = 0; i < 16; i++) {
            upSum += (now[i] == -1 ? 0 : 1) * scope[i];
        }
        int downSum = 0;
        for (int i = 16; i < 32; i++) {
            downSum += (now[i] == -1 ? 0 : 1) * scope[i];
        }
        return (red ? 1 : -1) * (downSum - upSum);
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
