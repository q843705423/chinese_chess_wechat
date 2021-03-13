package io.github.q843705423.controller;

import com.alibaba.fastjson.JSON;
import io.github.q843705423.entity.Homework;
import io.github.q843705423.entity.R;
import io.github.q843705423.entity.Student;
import io.github.q843705423.entity.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("hello")
public class HelloController {

    @PostMapping("world")
    @GetMapping("world2")
    @RequestMapping(value = "hello", method = {RequestMethod.DELETE, RequestMethod.GET})
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

    //    @PostMapping("cqqnb")
    @GetMapping("cqqnb")
    public String cqqnb(@RequestBody Student student, Teacher teacher, String code) {
        return "post:" + JSON.toJSONString(student) + " 和 " + JSON.toJSONString(teacher) + " ,code=" + code;
    }

    @GetMapping("testRList")
    public R<List<String>> testRList(@RequestBody Student student, Teacher teacher, String code) {
        ArrayList<String> list = new ArrayList<>();
        list.add("post:" + JSON.toJSONString(student) + " 和 " + JSON.toJSONString(teacher) + " ,code=" + code);
        return R.ok(list);
    }

    @PostMapping("testOnlyList")
    public List<String> testOnlyList(@RequestBody Student student, Teacher teacher, String code) {
        ArrayList<String> list = new ArrayList<>();
        list.add("post:" + JSON.toJSONString(student) + " 和 " + JSON.toJSONString(teacher) + " ,code=" + code);
        return list;
    }
}
