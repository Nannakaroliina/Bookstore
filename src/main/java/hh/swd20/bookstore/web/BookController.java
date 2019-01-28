package hh.swd20.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.Book;

@Controller
public class BookController {

	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String getNewBook(Model model) {
		model.addAttribute("book", new Book());
		return "newbook";
	}
}
