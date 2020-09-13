package io.github.q843705423.entity.piece.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReplaceTable {
/*
    private static int count = 0;
    private static Map<Integer, int[]> map = new HashMap<>();

    private static void goodPutMap(int[] now, int who, int where, int score, int depth) {

    }

    public static void putMap(int[] now, int who, int where, int score, int nowDepth, boolean isRedTurn, boolean playerIsRed) {
        int[] old = getMap(now, nowDepth);
        int[] ints = {score, who, where, nowDepth};
        if (old == null) {
            ReplaceTable.map.put(Arrays.hashCode(now), ints);
            return;
        }
        if (nowDepth > old[3]) {
            ReplaceTable.map.put(Arrays.hashCode(now), ints);
            return;
        }
        if (isRedTurn == playerIsRed && score >= old[0]) {
            ReplaceTable.map.put(Arrays.hashCode(now), ints);
            return;
        }
        if (isRedTurn != playerIsRed && score <= old[0]) {
            ReplaceTable.map.put(Arrays.hashCode(now), ints);
            return;
        }
    }

    public static int[] getMapEasy(int[] now, int depth) {
        return map.get(Arrays.hashCode(now));

    }

    public static int[] getMap(int[] now, int nowDepth) {
        int[] ints = map.get(Arrays.hashCode(now));
        if (ints == null || ints[3] <= nowDepth) {
            return null;
        }
        return ints;

    }*/
}
