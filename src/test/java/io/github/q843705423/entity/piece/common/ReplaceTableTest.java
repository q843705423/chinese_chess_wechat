package io.github.q843705423.entity.piece.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplaceTableTest {

    @Test
    public void putMap() {
        int z = (123 << 16) + (41 << 8) + 22;
        int who = z & ((1 << 8) - 1);
        int where = (z >> 8) & ((1 << 8) - 1);
        int score = z >> 16;
        System.out.println(who);
        System.out.println(where);
        System.out.println(score);
    }

    @Test
    public void test2() {
        int[]a = new int[]{2,1};
        System.out.println(a.hashCode());

    }
}