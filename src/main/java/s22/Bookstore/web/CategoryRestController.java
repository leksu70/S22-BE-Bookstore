package s22.Bookstore.web;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	// RESTful service to get all categories - toimii
	@GetMapping(value={"/categories", "/categories/"})
	// public @ResponseBody Iterable<Category> catListRest() {	ResponseBodyä ei tarvita, kun käytössä on RestController.
	public Iterable<Category> catListRest() {	
        return catrepository.findAll();
    }
	
	// RESTful service to save a category - Ei toimi
		@PostMapping(value="/categories")
		public Iterable<Category> addBookRest(Category cat, Model model) {
			System.out.println("REST:Category:" + cat.toString());
			catrepository.save(cat);
			return catrepository.findAll();
		}
		
	// RESTful service to get category by id - toimii
	@GetMapping(value="/categories/{id}")
	public Optional<Category> findCatRest(@PathVariable("id") Long catId) {	
    	return catrepository.findById(catId);
    } 
	
	// RESTful service to delete id category - toimii
	@DeleteMapping("/categories/{id}")
	public Iterable<Category> deleteCatRest(@PathVariable("id") Long catId, Model model) {
		System.out.println("RESTful: catId:" + catId);
		catrepository.deleteById(catId);
		return catrepository.findAll();
	}
	
}
