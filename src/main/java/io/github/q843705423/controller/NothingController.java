package io.github.q843705423.controller;

import com.alibaba.fastjson.JSON;
import io.github.q843705423.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author qiqi.chen
 */
@RestController
@RequestMapping("")
public class NothingController {

    @PostMapping("w")
    public void w() {
        System.out.println("hello world");

    }

    @PostMapping("/")
    public Date k(@RequestBody Student student) {
        System.out.println("hello world");
        System.out.println(JSON.toJSONString(student));
        return new Date();
    }
}
