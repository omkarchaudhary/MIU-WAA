package com.miu.lab5mongodemoparttwo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class Lab5MongoDemoPartTwoApplication{

    public static void main(String[] args) {
        SpringApplication.run(Lab5MongoDemoPartTwoApplication.class, args);
    }

}
