package io.github.q843705423.entity.piece.kind;

import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;

import java.util.List;

public class KindTest {

    /**
     * 鸿门宴杀
     */

    @Test
    public void moveList() {
        Protocol protocol = new Protocol("4k4/9/9/9/9/9/9/9/9/4K4 w - - 0 1");
        Board.init(protocol);
        String go = Board.go();
        System.out.println(go);
    }

    /**
     * 鸿门宴杀
     */
    @Test
    public void moveList2() {
        show("4k4/9/9/9/9/9/9/9/9/4K4 b - - 0 1");
    }

    /**
     * 鸿门宴阻止
     */
    @Test
    public void moveList3() {
        show("4k4/9/9/9/9/9/4P4/9/9/4K4 w - - 0 1");
    }


    /**
     * 車馬象士无士象馬車
     * 无无无无将无无无无
     * 无砲无无无无无砲无
     * 卒无卒无无无无无卒
     * 无无无无无兵无无无
     * 无无无无无无卒无无
     * 兵无兵无无无兵无兵
     * 无炮无无无无马炮无
     * 无无无无车无无无无
     * 车马相仕帅仕相无无
     */
    @Test
    public void test3() {
        go("rnba1abnr/4k4/1c5c1/p1p5p/5P3/6p2/P1P3P1P/1C4NC1/4R4/RNBAKAB2 b - - 0 6");
    }

    private void show(String fen) {
        Protocol protocol = new Protocol(fen);
        Board.init(protocol);
        Main.showChinese(Board.board);
        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
        Main.zhao(maybeList).forEach(System.out::println);
    }


    private void go(String fen) {
        Protocol protocol = new Protocol(fen);
        Board.init(protocol);
        Board.go();
//        Main.showChinese(Board.board);
//        List<int[]> maybeList = Main.getMaybeList(Board.now, Board.board, protocol.isRed());
//        Main.zhao(maybeList).forEach(System.out::println);
    }
}