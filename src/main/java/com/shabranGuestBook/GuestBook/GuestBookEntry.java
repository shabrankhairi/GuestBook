package com.shabranGuestBook.GuestBook;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.Assert;

@Entity
class GuestBookEntry {

    private @Id
    @GeneratedValue
    Long id;
    private final String name, birthofdate, address;
    private final LocalDateTime date;


    public GuestBookEntry(String name, String birthofdate, String address) {

        Assert.hasText(name, "Name must not be null or empty!");
        Assert.hasText(address, "Text must not be null or empty!");

        this.name = name;
        this.birthofdate = birthofdate;
        this.address = address;
        this.date = LocalDateTime.now();
    }

    @SuppressWarnings("unused")
    private GuestBookEntry() {
        this.name = null;
        this.birthofdate = null;
        this.address = null;
        this.date = null;
    }

    public String getName() {
        return name;
    }

    public String getBirthofdate(){
        return birthofdate;
    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }
}