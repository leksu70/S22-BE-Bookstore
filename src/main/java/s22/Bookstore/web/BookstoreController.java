package s22.Bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookstoreRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;
import s22.Bookstore.domain.User;
import s22.Bookstore.domain.UserRepository;

@Controller
public class BookstoreController {
	@Autowired
	private BookstoreRepository bookrepository;
	@Autowired
	private CategoryRepository catrepository;
	@Autowired
	private UserRepository userrepository;

	@GetMapping({ "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", bookrepository.findAll());
		return "booklist";
	}
	
	@GetMapping({ "/main" })
	public String runMain() {
		return "/main";
	}

	@GetMapping("/addbook")
	@PreAuthorize("hasAuthority('ADMIN')")
	// Toimii, jos ei ole @Valid!
	// public String addBook(@Valid Book book, BindingResult bindingResult, Model model) {
	public String addBook(Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened while saving category");
			return "addbook";
		}
		model.addAttribute("book", new Book());
		model.addAttribute("categories", catrepository.findAll());
		return "addbook";
	}

	@GetMapping("/edit/book/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookrepository.findById(bookId));
		model.addAttribute("categories", catrepository.findAll());
		return "/editbook";
	}
	
	@PostMapping("/savebook")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened. book.id:" + book.getId());
			// Virhe! Luo uuden entryn
			model.addAttribute("book", bookrepository.findById(book.getId()));
			model.addAttribute("categories", catrepository.findAll());
			return "editbook";
		}
		bookrepository.save(book);
		return "redirect:booklist";
	}
	
	@PostMapping("/savecat")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveCat(@Valid Category cat, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened while saving category");
			return "addcat";
		}
		catrepository.save(cat);
		return "redirect:/catlist";
	}
	
	@GetMapping("/delete/book/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addCat(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("categories", catrepository.findAll());
		return "addcat";
	}
	
	@GetMapping("/edit/cat/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editCat(@PathVariable("id") Long catId, Model model) {
		System.out.println("catID(id):"+catId);
		model.addAttribute("category", catrepository.findById(catId));
		return "/editcat";
	}
	
	@GetMapping("/delete/cat/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteCat(@PathVariable("id") Long id, Model model) {
		catrepository.deleteById(id);
		return "redirect:/catlist";
	}
	
	// User part
	
	@GetMapping("/userlist")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String userList(Model model) {
		model.addAttribute("users", userrepository.findAll());
		return "userlist";
	}
	
	@GetMapping("/adduser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addUser(User user, BindingResult bindingResult, Model model) {
		model.addAttribute("user", new User());
		// Leo 25.10.2022: Role: user tai admin ovat käyttöliittymässä.
		return "adduser";
	}
	
	@PostMapping("/saveuser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened. user.id:" + user.getId());
			// Virhe! Luo uuden entryn
			model.addAttribute("user", userrepository.findById(user.getId()));
			//model.addAttribute("usercategories", userrepository.findAll());
			return "edituser";
		}
		userrepository.save(user);
		return "redirect:userlist";
	}
	
	@GetMapping("/edit/user/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editUser(@PathVariable("id") Long userId, Model model) {
		System.out.println("userId(id):"+userId);
		model.addAttribute("user", userrepository.findById(userId));
		return "/edituser";
	}
	
	@GetMapping("/delete/user/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		userrepository.deleteById(id);
		return "redirect:/userlist";
	}
}
