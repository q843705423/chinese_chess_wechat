package io.github.q843705423.entity.piece.common;

import java.util.HashMap;
import java.util.Map;

public class ReplaceTableV2 {

    public static int count = 0;
    public static Map<Integer, int[]> map = new HashMap<>(500_0000);

    public static void putMap(int[] now, int who, int where, int score, int nowDepth, boolean isRedTurn, boolean playerIsRed) {

        int[] ints = {score, who, where, nowDepth};
        int[] old = getMap(now, nowDepth, isRedTurn);

        if (old == null) {
            putMap(now, nowDepth, ints, isRedTurn);
            return;
        }
        if ((playerIsRed == isRedTurn) && score >= old[0]) {
            putMap(now, nowDepth, ints, isRedTurn);
            return;
        }
        if ((playerIsRed != isRedTurn) && score <= old[0]) {
            putMap(now, nowDepth, ints, isRedTurn);
            return;
        }

    }


    public static int[] getMap(int[] now, int nowDepth, boolean nowIsRedReturn) {
        int key = Board.hashCode(now);
//        key ^= nowIsRedReturn ? 0x0000FFFF : 0xFFFF0000;
        key = 107 * key + nowDepth;
        key = 107 * key + (nowIsRedReturn ? 1 : 2);
        int[] ints = map.get(key);
        c += ints == null ? 0 : 1;
        all++;
        return ints;

    }


    private static void putMap(int[] now, int nowDepth, int[] ints, boolean returnRed) {
        int key = Board.hashCode(now);
        key = 107 * key + nowDepth;
        key = 107 * key + (returnRed ? 1 : 2);
        map.put(key, ints);
    }

    public static int c = 0;
    public static int all = 0;

    public static void add() {
        c++;
    }

    public static void show() {
        System.out.println("总次数:" + all);
        System.out.println("命中次数" + c);
        System.out.printf("命中率:%.2f\n", (c * 100.0) / all);
        c = 0;
        all = 0;
        clear();
    }

    public static void clear() {
        map = new HashMap<>();
    }
}
