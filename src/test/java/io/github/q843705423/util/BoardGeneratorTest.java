package io.github.q843705423.util;

import org.junit.Test;

public class BoardGeneratorTest {

    @Test
    public void log() {
        int value = 0;
        int base = 0;
        double v = Math.log(1_0000_0000) / Math.log(50);
        System.out.println(v);

    }

    @Test
    public void fenTest() {
        BoardGenerator boardGenerator = new BoardGenerator();
        String[][] fen = boardGenerator.fenEnglish("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
    }
}