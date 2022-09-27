package s22.Bookstore.web;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookstoreRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;


@RestController
public class BookRestController {
	@Autowired
	private BookstoreRepository bookrepository;
	@Autowired
	private CategoryRepository catrepository;

	// RESTful service to get all books
	@GetMapping(value={"/books", "/books/"})
	public @ResponseBody Iterable<Book> bookListRest() {	
        return bookrepository.findAll();
    }
	
	@GetMapping(value="/books/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookrepository.findById(bookId);
    } 
	
	
}
