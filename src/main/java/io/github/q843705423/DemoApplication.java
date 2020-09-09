package io.github.q843705423;

import io.github.q843705423.entity.ContentDTO;
import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.Student;
import io.github.q843705423.entity.piece.common.Board;
import io.github.q843705423.util.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("demo")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostMapping("hello")
    public String hello(Student student) {
        return "hello," + student.getName();
    }


    @PostMapping("world")
    public String world(ContentDTO contentDTO) {
        String content = contentDTO.getContent();
        if (content.startsWith("setoption")) {
            System.out.println(content);
            return "";

        } else if (content.contains("/")) {
            Protocol protocol = new Protocol(content);
            int[] init = Main.init(protocol.getPiecePlacementData());
            int[] board = Main.initToBoard(init);
            int[] dfs = Main.dfs(init, board, 0, 4, protocol.isRed(), protocol.isRed(), new int[30], new int[30], 0);
            init[dfs[1]] = dfs[2];
            int[] newBoard = Main.initToBoard(init);
            String s = Main.boardToFen(newBoard, protocol.isRed(), protocol.getAllTurn() + 1);
            System.out.println(s);
            return s;
        } else if (content.contains("position startpos")) {
            Board.init();
            String[] split = content.split(" ");
            for (int i = 3; i < split.length; i++) {
                Board.move(split[i]);
            }

        } else if (content.startsWith("go depth")) {
            return Board.go();

        } else if (content.startsWith("stop")) {
            Board.init();
            return "scope 0";
        }
        System.out.println(content);
        return "";
    }


}
