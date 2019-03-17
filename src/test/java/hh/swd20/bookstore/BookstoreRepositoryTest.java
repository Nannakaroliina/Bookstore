package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;
import hh.swd20.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = brepository.findByTitle("How Google works");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("How Google works");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Onnen Algoritmi", "P.Z. Reizin", 2018, 298765, 24.99, new Category("Romantic fiction"));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = brepository.findByTitle("Kuolemanlaakso");
		Book book = books.get(0);
		books.remove(book);
		assertThat(books).hasSize(0);
	}
	
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = crepository.findByName("Non-fiction");
		
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Non-fiction");
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Mystery");
		crepository.save(category);
		assertThat(category.getId()).isNotNull();
	}
	
	@Test
	public void findByUsernameShouldReturnUser() {
		User user = urepository.findByUsername("user");
		
		assertThat(user.getUsername()).isEqualTo("user");
	}
	
	@Test
	public void createNewUser() {
		User user = new User("user2", "$2a$08$kdWk6JiCSGDQ0KnwNAS/NufheMD9YmjkyD0Z0bTR21MUsDIT9MMm.", "user2@bookstore.com", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
}
