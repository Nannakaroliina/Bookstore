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

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository cgrepository) {
		return (args) -> {
			log.info("Save couple of categories");
			cgrepository.save(new Category("Thriller"));
			cgrepository.save(new Category("Non-fiction"));
			cgrepository.save(new Category("Biography"));
			
			log.info("Save some books");
			repository.save(new Book("How Google works", "Eric Schmidt & Jonathan Rosenberg", 2017, 12345, 10.99, cgrepository.findByName("Non-fiction").get(0)));
			repository.save(new Book("Sweet Nothing", "Nicole Mowbray", 2014, 23456, 7.99, cgrepository.findByName("Non-fiction").get(0)));
			
			log.info("Get all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}

