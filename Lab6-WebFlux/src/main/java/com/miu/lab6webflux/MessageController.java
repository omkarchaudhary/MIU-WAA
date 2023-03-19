package com.miu.lab6webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    private int x = 1;

    @GetMapping(value="/message", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> getChatMessages(){
        return messageRepository.findAllByMessage("Hello World");
    }

    @Scheduled(fixedRate = 3000)
    private void saveMessage() {
        messageRepository.save(new Message(x+1,"Sender"+x, "Hello World", LocalDate.now())).block();
        x++;
    }
}
