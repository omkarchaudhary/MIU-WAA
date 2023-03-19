package com.miu.lab6webflux;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Message {
    @Id
    private int id;
    private String sender;
    private String message;
    private LocalDate deliveredDate;

    public Message() {
    }

    public Message(int id, String sender, String message, LocalDate deliveredDate) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.deliveredDate = deliveredDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDate deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", deliveredDate=" + deliveredDate +
                '}';
    }
}
