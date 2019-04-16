package com.shabranGuestBook.GuestBook;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner init(GuestBookRepository GuestBook)
	{
		return args -> {
			Stream.of( //
					new GuestBookEntry("Shabran","test", "Bogor" )) //
					.forEach(GuestBook::save);
		};
	}
}
