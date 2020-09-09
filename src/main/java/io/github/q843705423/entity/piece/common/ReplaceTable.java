package io.github.q843705423.entity.piece.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReplaceTable {

    public static int count = 0;
    public static Map<Integer, int[]> map = new HashMap<>();

    public static void putMap(int[] now, int who, int where, int score) {
        int[] ints = {score, who, where};
        map.put(Arrays.hashCode(now), ints);
    }

    public static int[] getMap(int[] now) {
        return map.get(Arrays.hashCode(now));

    }
}
