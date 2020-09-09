package io.github.q843705423.controller;

import io.github.q843705423.autumn.DefaultContextFactory;
import io.github.q843705423.autumn.entity.Configuration;
import io.github.q843705423.entity.Homework;
import io.github.q843705423.entity.Student;
import io.github.util.MyDefaultContextFactory;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloControllerTest {

    @Test
    public void world() {
        DefaultContextFactory defaultContextFactory = new DefaultContextFactory(new Configuration("http://127.0.0.1:8080"));
        HelloController controller = defaultContextFactory.getBean(HelloController.class);
        String world = controller.world();
        System.out.println(world);

    }

    @Test
    public void test2() {
        MyDefaultContextFactory myDefaultContextFactory = new MyDefaultContextFactory();
        HelloController helloController = myDefaultContextFactory.getBean(HelloController.class);
        String world = helloController.world();
        System.out.println(world);
    }

    @Test
    public void test3() {
        MyDefaultContextFactory myDefaultContextFactory = new MyDefaultContextFactory();
        HelloController helloController = myDefaultContextFactory.getBean(HelloController.class);
        Student student = new Student();
        student.setId("1");
        student.setName("lksajdlkajdlk");
        String world = helloController.cqqnb(student);
        System.out.println(world);
    }

    @Test
    public void test4() throws IOException {
        MyDefaultContextFactory myDefaultContextFactory = new MyDefaultContextFactory();
        HelloController controller = myDefaultContextFactory.getBean(HelloController.class);
        Homework homework = new Homework();
        homework.setName("cqqnb");
        homework.setMultipartFile(new MockMultipartFile("a.jpg", new FileInputStream("C:\\Users\\admin\\Desktop\\cqq.jpg")));
        controller.ok(homework);

    }


    @Test
    public void test5() throws IOException {
        MyDefaultContextFactory myDefaultContextFactory = new MyDefaultContextFactory();
        HelloController controller = myDefaultContextFactory.getBean(HelloController.class);
        Homework homework = new Homework();
        homework.setMultipartFile(new MockMultipartFile("a.jpg", "a.jpg", "UTF-8", new FileInputStream("C:\\Users\\admin\\Desktop\\cqq.jpg")));
        homework.setName("123456");
        String ok = controller.ok(homework);
        System.out.println(ok);
    }

    @Test
    public void test6() {
        MyDefaultContextFactory factory = new MyDefaultContextFactory();
        HelloController bean = factory.getBean(HelloController.class);
        Student student = new Student();
        student.setId("1");
        student.setName("cqq");
        String cqqnb = bean.cqqnb(student);
        System.out.println(cqqnb);

    }

}