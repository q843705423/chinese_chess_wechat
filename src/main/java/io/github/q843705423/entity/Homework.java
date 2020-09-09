package io.github.q843705423.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Homework {
    private MultipartFile multipartFile;
    private String name;

}
