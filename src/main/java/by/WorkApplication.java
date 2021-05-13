package by;

import by.controller.ReisController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication

public class WorkApplication {


    public static void main(String[] args) {
        new File(ReisController.uploadDirectory).mkdir();
        SpringApplication.run(WorkApplication.class, args);
    }

}
