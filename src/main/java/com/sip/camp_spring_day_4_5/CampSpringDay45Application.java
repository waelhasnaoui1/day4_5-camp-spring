package com.sip.camp_spring_day_4_5;

import com.sip.camp_spring_day_4_5.controllers.ArticleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class CampSpringDay45Application {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(ArticleController.uploadDirectory);
        try {
            Files.createDirectory(path);
        }catch(IOException ex){

        }
        SpringApplication.run(CampSpringDay45Application.class, args);
    }

}
