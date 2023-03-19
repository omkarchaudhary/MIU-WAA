package com.miu.lab6webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@EnableReactiveMongoRepositories
public class Lab6WebFluxApplication implements CommandLineRunner {

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Lab6WebFluxApplication.class, args);
    }

    @PostConstruct
    public void init(){
        reactiveMongoTemplate.dropCollection("message").
                then(reactiveMongoTemplate.createCollection("message", CollectionOptions.empty()
                        .capped().size(2048).maxDocuments(10000))).subscribe();
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<Message> result = WebClient.create("http://localhost:8080/message")
                .get()
                .retrieve()
                .bodyToFlux(Message.class);
        result.subscribe(message -> {
            System.out.println(message);
        });
        Thread.sleep(1000);
    }
}
