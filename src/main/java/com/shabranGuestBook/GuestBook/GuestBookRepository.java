package com.shabranGuestBook.GuestBook;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

    interface GuestBookRepository extends CrudRepository<GuestBookEntry, Long> {

        Streamable<GuestBookEntry> findByName(String string, Sort sort);
    }
