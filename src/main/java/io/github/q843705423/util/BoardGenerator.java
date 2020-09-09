package io.github.q843705423.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BoardGenerator {
    static Map<String, String> map = new HashMap<>();

    static {
        map.put("r", "车");
        map.put("n", "马");
        map.put("b", "象");
        map.put("a", "士");
        map.put("k", "将");
        map.put("c", "砲");
        map.put("p", "卒");

        map.put("R", "車");
        map.put("N", "馬");
        map.put("B", "相");
        map.put("A", "仕");
        map.put("K", "帅");
        map.put("C", "炮");
        map.put("P", "兵");
    }

    public String[][] fenEnglish(String str) {
        int y = 0;
        int x = 0;
        String[][] strings = new String[10][9];
        for (int i = 0; i < str.length(); i++) {
            String c = str.charAt(i) + "";
            //如果是空，表示第一部分结束了
            if (c.equals(" ")) {
                break;
            }
            //如果是/表示下一行了
            if (c.equals("/")) {
                y++;
                x = 0;
                continue;
            }
            String s = map.get(c);
            if (s == null) {
                int spaceNum = Integer.parseInt(c);
                for (int j = 0; j < spaceNum; j++) {
                    strings[y][x++] = "o";
                }
            } else {
                strings[y][x++] = c;
            }
        }


        show(strings);
        return strings;

    }

    public String[][] fenChinese(String str) {
        int y = 0;
        int x = 0;
        String[][] strings = new String[10][9];
        for (int i = 0; i < str.length(); i++) {
            String c = str.charAt(i) + "";
            //如果是空，表示第一部分结束了
            if (c.equals(" ")) {
                break;
            }
            //如果是/表示下一行了
            if (c.equals("/")) {
                y++;
                x = 0;
                continue;
            }
            String s = map.get(c);
            if (s == null) {
                int spaceNum = Integer.parseInt(c);
                for (int j = 0; j < spaceNum; j++) {
                    strings[y][x++] = "无";
                }
            } else {
                strings[y][x++] = s;
            }
        }


        show(strings);
        return strings;

    }

    public int calculateGoal(String[][] english, boolean red) {
        Set<String> strings = map.keySet();


        return 100;
    }

    public void show(String[][] board) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }
}
