package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.BookstoreApplication;
import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository cgrepository, UserRepository urepository) {
		return (args) -> {
			log.info("Save couple of categories");
			cgrepository.save(new Category("Thriller"));
			cgrepository.save(new Category("Non-fiction"));
			cgrepository.save(new Category("Health"));
			cgrepository.save(new Category("Biography"));
			cgrepository.save(new Category("Romance"));
			cgrepository.save(new Category("Fantasy"));
			cgrepository.save(new Category("Comic"));
			cgrepository.save(new Category("Fiction"));
			
			log.info("Save some books");
			repository.save(new Book("How Google works", "Eric Schmidt & Jonathan Rosenberg", 2017, 12345, 10.99, cgrepository.findByName("Non-fiction").get(0)));
			repository.save(new Book("Sweet Nothing", "Nicole Mowbray", 2014, 23456, 7.99, cgrepository.findByName("Health").get(0)));
			repository.save(new Book("Happiness for Humans", "P.Z. Reizin", 2018, 38467, 27.95, cgrepository.findByName("Romance").get(0)));
			repository.save(new Book("Becoming", "Michelle Obama", 2018, 48756, 22.60, cgrepository.findByName("Biography").get(0)));
			repository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", 2016, 58947, 14.95, cgrepository.findByName("Non-fiction").get(0)));
			repository.save(new Book("Lazarus", "Lars Kepler", 2018, 78394, 24.95, cgrepository.findByName("Thriller").get(0)));
			repository.save(new Book("Kuolemanlaakso", "Tommi Kovanen & Jenny Rostain", 2017, 83746, 24.95, cgrepository.findByName("Biography").get(0)));
			
			// Creating user and admin
			User user1 = new User("user", "$2a$04$4huA86.ZAl/8Us37tOfDDekrFFIZfkb6ZPRixFYgy702HE9hNHQCC", "user1@bookstore.com", "USER");
			User user2 = new User("admin", "$2a$06$2lArAqPgSxgIiVIxs4E5su0GWBnHcBxiXaZufO7vLLGroqpzklXeG", "admin@bookstore.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("Get all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}

