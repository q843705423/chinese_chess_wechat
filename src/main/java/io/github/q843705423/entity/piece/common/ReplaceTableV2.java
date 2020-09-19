package io.github.q843705423.entity.piece.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReplaceTableV2 {

    public static int count = 0;
    public static Map<Integer, int[]> map = new HashMap<>(500_0000);

    public static void putMap(int[] now, int who, int where, int score, int nowDepth, boolean isRedTurn, boolean playerIsRed) {

        int[] ints = {score, who, where, nowDepth, key2(now, nowDepth, isRedTurn), key3(now, nowDepth, isRedTurn)};
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

    public static int key1(int[] now, int nowDepth, boolean nowIsRedReturn) {
        int key = Arrays.hashCode(now);
        key = 107 * key + nowDepth;
        key = 107 * key + (nowIsRedReturn ? 1 : 2);
        return key;

    }

    public static int key2(int[] now, int nowDepth, boolean nowIsRedReturn) {
        int key = Board.hashCode(now);
        key = 771 * key + nowDepth;
        key = 771 * key + (nowIsRedReturn ? 1 : 2);
        return key;

    }

    public static int key3(int[] now, int nowDepth, boolean nowIsRedReturn) {
        int key = 0;
        for (int i = 0; i < now.length; i++) {
            key += now[i];
        }
        key += nowIsRedReturn ? 1 : 0;
        key += nowDepth;
        return key;

    }

    public static int[] getMap(int[] now, int nowDepth, boolean nowIsRedReturn) {
        int[] ints = map.get(key1(now, nowDepth, nowIsRedReturn));
        if (ints != null) {
            int key2 = key2(now, nowDepth, nowIsRedReturn);
            int key3 = key3(now, nowDepth, nowIsRedReturn);
            if (key2 != ints[4] || key3 != ints[5]) {
                return null;
            }
        }
        c += ints == null ? 0 : 1;
        all++;
        return ints;

    }


    private static void putMap(int[] now, int nowDepth, int[] ints, boolean returnRed) {

        map.put(key1(now, nowDepth, returnRed), ints);
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
