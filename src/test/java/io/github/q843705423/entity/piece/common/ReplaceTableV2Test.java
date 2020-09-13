package io.github.q843705423.entity.piece.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ReplaceTableV2Test {

    @Test
    public void putMap() {
        Map<String, String> map = new HashMap<>();
        int[] a = new int[]{1};
        int i = Arrays.hashCode(a);
        System.out.println(i);
    }
}