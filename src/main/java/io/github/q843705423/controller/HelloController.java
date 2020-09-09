package io.github.q843705423.controller;

import io.github.q843705423.entity.Homework;
import io.github.q843705423.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@AllArgsConstructor
@RequestMapping("hello")
public class HelloController {

    @PostMapping("world")
    public String world() {
        return "hello world";
    }

    @PostMapping("ok")
    public String ok(Homework homework) throws IOException {
        InputStream inputStream = homework.getMultipartFile().getInputStream();
        String name = homework.getName();
        System.out.println(name);
        String originalFilename = homework.getMultipartFile().getOriginalFilename();
        System.out.println(originalFilename);

        OutputStream outputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\cqq_copy.jpg");
        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);
        outputStream.write(b);
        outputStream.flush();
        outputStream.close();
        return originalFilename + ":" + name + ":" + inputStream.available();

    }

    @PostMapping("cqqnb")
    public String cqqnb(@RequestBody Student student) {
        return student.getId() + ":" + student.getName();
    }

}
