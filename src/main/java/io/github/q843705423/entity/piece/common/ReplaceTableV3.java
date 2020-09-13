package io.github.q843705423.entity.piece.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReplaceTableV3 {
/*
    public static Map<Integer, int[]> map = new HashMap<>(500_0000);
    private static int allCount;
    private static int hitCount;

    public static int[] getMap(int[] now, int nowDepth) {
        int[] ints = map.get(Arrays.hashCode(now));
        allCount++;
        if (ints == null || ints[3] <= nowDepth) {
            return null;
        }
        hitCount++;
        return ints;
    }

    public static void show() {
        System.out.println("总次数:" + allCount);
        System.out.println("命中次数" + hitCount);
        System.out.printf("命中率:%.2f\n", (hitCount * 100.0) / allCount);
        hitCount = 0;
        allCount = 0;

    }

    public static void putMap(int[] now, int who, int where, int score, int nowDepth, boolean isRedTurn, boolean playerIsRed) {
        int[] old = getMap(now, nowDepth);
        int[] ints = {score, who, where, nowDepth};
        if (old == null) {
            map.put(Arrays.hashCode(now), ints);
            return;
        }
        if (nowDepth > old[3]) {
            map.put(Arrays.hashCode(now), ints);
            return;
        }
        if (isRedTurn == playerIsRed && nowDepth == old[3]&& score >= old[0]) {
            map.put(Arrays.hashCode(now), ints);
            return;
        }
        if (isRedTurn != playerIsRed && nowDepth == old[3] && score <= old[0]) {
            map.put(Arrays.hashCode(now), ints);
            return;
        }
    }

    public static void clear() {
        map = new HashMap<>();
    }*/
}
