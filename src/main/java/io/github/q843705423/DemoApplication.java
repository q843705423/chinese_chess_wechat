package io.github.q843705423;

import io.github.q843705423.entity.ContentDTO;
import io.github.q843705423.entity.Protocol;
import io.github.q843705423.entity.Student;
import io.github.q843705423.entity.piece.common.Board;
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
        if (contentDTO.getContent() == null) {
            return "scope 0\nnnobestmove";
        }
        System.out.println(contentDTO.getContent());
        if (content.startsWith("setoption")) {
            return "";

        } else if (content.startsWith("stop")) {
            return "scope 0\nnobestmove";
        } else if (content.contains("/")) {
            Board.init(new Protocol(content));
            return "";
        } else if (content.contains("position startpos")) {
            Board.init(new Protocol(content));
            return "";
        } else if (content.startsWith("go depth")) {
            Board.init();
            return Board.go();
        } else if (content.startsWith("go node")) {
            Board.init();
            return Board.go();

        }
        System.out.println(content);
        return "";
    }


}
