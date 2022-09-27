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
public class CategoryRestController {
	@Autowired
	private BookstoreRepository bookrepository;
	@Autowired
	private CategoryRepository catrepository;

	// RESTful service to get all categories
	@GetMapping(value={"/categories", "/categories/"})
	public @ResponseBody Iterable<Category> catListRest() {	
        return catrepository.findAll();
    }
	
	// RESTful service to get category by id
	@GetMapping(value="/categories/{id}")
	public @ResponseBody Optional<Category> findCatRest(@PathVariable("id") Long catId) {	
    	return catrepository.findById(catId);
    } 
	
	
}
