package io.github;

import io.github.q843705423.DemoApplication;
import io.github.q843705423.autumn.DefaultContextFactory;
import io.github.q843705423.autumn.entity.Configuration;
import io.github.q843705423.entity.Student;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

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


}
