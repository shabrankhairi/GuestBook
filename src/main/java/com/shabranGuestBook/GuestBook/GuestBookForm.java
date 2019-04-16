package com.shabranGuestBook.GuestBook;

import javax.validation.constraints.NotBlank;

public class GuestBookForm {
    private final @NotBlank String name;
    private final @NotBlank String birthofdate;
    private final @NotBlank String address;


    public GuestBookForm(String name, String birthofdate, String address) {

        this.name = name;
        this.birthofdate = birthofdate;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getBirthofdate() { return birthofdate;}

    public String getAddress() {
        return address;
    }

    GuestBookEntry toNewEntry() {
        return new GuestBookEntry(getName(), getBirthofdate(), getAddress());
    }
}
