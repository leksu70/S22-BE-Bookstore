package s22.Bookstore.web;

import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// RESTful service to get all books - toimii
	@GetMapping(value={"/books", "/books/"})
	// public @ResponseBody Iterable<Book> bookListRest() {	 ResponseBody:ä ei tarvita, kun on oma RestController käytössä
	public Iterable<Book> bookListRest() {
        return bookrepository.findAll();
    }
	
	// RESTful service to save a book - Ei toimi
	@PostMapping(value="/books")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public Iterable<Book> addBookRest(Book book, Model model) {
		System.out.println("REST:Book:" + book.getTitle() + " " + book.getId() + " book:" + book);
		bookrepository.save(book);
		return bookrepository.findAll();
	}
	
	// RESTful service to get book by id - toimii
	@GetMapping(value="/books/{id}")
	public Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookrepository.findById(bookId);
    }
	
	// RESTful service to delete id book - toimii
	@DeleteMapping("/books/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Iterable<Book> deleteBookRest(@PathVariable("id") Long bookId, Model model) {
		System.out.println("RESTful: bookId:" + bookId);
		bookrepository.deleteById(bookId);
		return bookrepository.findAll();
	}
	
}
