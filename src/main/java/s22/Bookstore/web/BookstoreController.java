package s22.Bookstore.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import s22.Bookstore.BookstoreApplication;
import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookstoreRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;

@Controller
public class BookstoreController {
	@Autowired
	private BookstoreRepository bookrepository;
	@Autowired
	private CategoryRepository catrepository;

	//@RequestMapping(value = { "/", "/booklist" })
	@GetMapping({ "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", bookrepository.findAll());
		return "booklist";
	}

	//@RequestMapping(value = "/add")
	@GetMapping("/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catrepository.findAll());
		return "addbook";
	}

	//@RequestMapping(value = "/edit/{id}")
	@GetMapping("/edit/book/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookrepository.findById(bookId));
		model.addAttribute("categories", catrepository.findAll());
		return "/editbook";
	}

	//@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PostMapping("/savebook")
	public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "addbook";
		}
		bookrepository.save(book);
		return "redirect:booklist";
	}
	@PostMapping("/savecat")
	public String saveCat(@Valid Category cat, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "addcat";
		}
		catrepository.save(cat);
		return "redirect:/catlist";
	}
	//@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@GetMapping("/delete/book/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		System.out.println("bookId:" + bookId);
		bookrepository.deleteById(bookId);
		return "redirect:/booklist";
	}
	
	// Categories part
	@GetMapping("/catlist")
	public String catList(Model model) {
		model.addAttribute("categories", catrepository.findAll());
		return "catlist";
	}
	
	@GetMapping("/addcat")
	public String addCat(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", catrepository.findAll());
		return "addcat";
	}
	
	
	
	@GetMapping("/delete/cat/{id}")
	public String deleteCat(@PathVariable("id") Long id, Model model) {
		catrepository.deleteById(id);
		return "redirect:/catlist";
	}
	
	@GetMapping("/edit/cat/{id}")
	public String editCat(@PathVariable("id") Long catid, Model model) {
		model.addAttribute("category", catrepository.findById(catid));
		model.addAttribute("categories", catrepository.findAll());
		return "/editcat";
	}
}
