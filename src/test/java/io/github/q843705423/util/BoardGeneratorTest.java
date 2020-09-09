package io.github.q843705423.util;

import org.junit.Test;

public class BoardGeneratorTest {

    @Test
    public void fenTest() {
        BoardGenerator boardGenerator = new BoardGenerator();
        String[][] fen = boardGenerator.fenEnglish("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
    }
}