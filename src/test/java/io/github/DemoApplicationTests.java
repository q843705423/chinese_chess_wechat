package io.github;

import io.github.q843705423.DemoApplication;
import io.github.q843705423.autumn.DefaultContextFactory;
import io.github.q843705423.autumn.entity.Configuration;
import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.Student;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class DemoApplicationTests {

    @Test
    public void test1() {
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.postForObject("http://localhost:8000/demo/hello?id=1&name=Tom", new HttpEntity<>(null), String.class);
        assert "hello,Tom".equals(s);
    }

    @Test
    public void test2() {
        DefaultContextFactory defaultContextFactory = new DefaultContextFactory(new Configuration("http://127.0.0.1:8000"));
        DemoApplication controller = defaultContextFactory.getBean(DemoApplication.class);
        String tom = controller.hello(new Student("1", "Tom"));
        assert "hello,Tom".equals(tom);
    }

    @Test
    public void ts() {
        hello("9/9/3k5/9/9/3N5/9/9/9/4K4 w - - 0 1");

    }

    private void hello(final String s1) {
//        String s = "position fen " + s1;
        Protocol protocol = new Protocol(s1);
        Board.init(protocol);
        Board.init();
        String go = Board.go();

        System.out.println(Arrays.toString(Board.now));
    }

    @Test
    public void test3() {
        String s = "position fen 9/9/3k5/9/9/3N5/9/9/9/4K4 w - - 0 1";
        Protocol protocol = new Protocol(s);
        Board.init(protocol);
        Board.init();
        String zhao = Main.zhao(22, 37);
        System.out.println(zhao);

    }

    @Test
    public void test4() {
        String s = "position fen 9/9/3k5/9/9/9/9/2N6/9/4K4 w - - 0 1";
        Protocol protocol = new Protocol(s);
        Board.init(protocol);
        Board.init();
        String zhao = Main.zhao(22, 55);
        System.out.println(zhao);

    }

    @Test
    public void test5() {
        String s = "position fen 9/9/3k5/9/9/3N5/9/9/4K4/9 w - - 0 1";
        Protocol protocol = new Protocol(s);
        Board.init(protocol);
        Board.init();
        String zhao = Main.zhao(22, 59);
        System.out.println(zhao);

    }

    @Test
    public void test6() {
        String s1 = "position startpos moves h0g2 e6e5 i0i1 e5e4 i1e1 g6g5 e3e4 g5g4 e4e5 e9e8 e5f5";
        hello(s1);
    }

    @Test
    public void test7() {
        hello("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
    }

    @Test
    public void hello() {
        hello("r4k3/9/9/9/9/4R4/9/9/9/4K4 w - - 0 1");
    }

    @Test
    public void test8() {
        hello("1nbakabnr/r8/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/5R3/RNBAKABN1 b - - 0 1");
        hello("1nbakabnr/r8/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/5R3/RNBAKABN1 b - - 0 1");

    }
}
