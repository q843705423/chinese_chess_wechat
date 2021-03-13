package io.github.q843705423.controller;

import io.github.q843705423.autumn.DefaultContextFactory;
import io.github.q843705423.autumn.entity.Configuration;
import io.github.q843705423.entity.Homework;
import io.github.q843705423.entity.R;
import io.github.q843705423.entity.Student;
import io.github.q843705423.entity.Teacher;
import io.github.util.MyDefaultContextFactory;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class HelloControllerTest {

    @Test
    public void world() {
        DefaultContextFactory defaultContextFactory = new DefaultContextFactory(new Configuration("http://127.0.0.1:8000"));
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
        student.setId("张三");
        student.setName("李四");
        String world = helloController.cqqnb(student, null, "1234");
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
        Teacher teacher = new Teacher();
        teacher.setCode("ts0001");
        teacher.setName("张hp");
        String cqqnb = bean.cqqnb(new Student(), teacher, null);
        System.out.println(cqqnb);

    }

    @Test
    public void test12() {
        MyDefaultContextFactory myDefaultContextFactory = new MyDefaultContextFactory();
        R<List<String>> listR = myDefaultContextFactory.getBean(HelloController.class).testRList(new Student(), null, "1234");
        System.out.println(listR);
        List<String> data = listR.getData();
        System.out.println(data);
        int size = data.size();
        List<String> list = data.subList(0, 1);
        System.out.println(list);


    }

    @Test
    public void test13() {
        MyDefaultContextFactory myDefaultContextFactory = new MyDefaultContextFactory();
        List<String> list1 = myDefaultContextFactory.getBean(HelloController.class).testOnlyList(new Student(), null, "1234");
        System.out.println(list1.size());
        System.out.println(list1.get(0));
    }
}
