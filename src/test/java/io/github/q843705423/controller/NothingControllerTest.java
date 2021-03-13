package io.github.q843705423.controller;

import io.github.q843705423.entity.Student;
import io.github.util.MyDefaultContextFactory;
import org.junit.Test;

import java.util.Date;

public class NothingControllerTest {

    @Test
    public void w() {
        new MyDefaultContextFactory().getBean(NothingController.class).w();
    }

    @Test
    public void k() {
        Student hello = new Student();
        hello.setId("12313");
        hello.setName("张三");
        Date k = new MyDefaultContextFactory().getBean(NothingController.class).k(hello);

    }
}