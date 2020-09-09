package io.github.q843705423.entity;

import io.github.q843705423.Eval;

import java.util.HashMap;
import java.util.Map;

public class StaticQizi {
    static Map<Integer, Eval> map = new HashMap<>();


    public static int getQizi(int now, BaseInfo baseInfo) {
        int i = now & 0x700;
        i = i >> 8;
        Eval eval = map.get(i);
        if (eval == null) {
            return 0;
        }
        return eval.basePrice(baseInfo);


    }
}
