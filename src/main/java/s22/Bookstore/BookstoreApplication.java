package s22.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookstoreRepository;
import s22.Bookstore.domain.Category;
import s22.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookstoreRepository bookrepository, CategoryRepository catrepository) {
		return (args) -> {
			log.info("Luodaan kirjakategoriat:");
			catrepository.save(new Category("Scifi"));
			catrepository.save(new Category("Kaunokirjallisuus"));
			catrepository.save(new Category("Tietokirjat"));
			
			log.info("save a couple of books");
			// Book(String title, String author, int bookYear, String isbn, double price, Category category)
			// Book(String title, String author)
			
			// Lisää kirjoja kirjakantaan
			
			bookrepository.save(new Book("Pimeät kuut", "Tommi Kinnunen", 2022, "9789510480991", 23.90, catrepository.findByName("Kaunokirjallisuus").get(0)));
			bookrepository.save(new Book("LOIRI.", "Jari Tervo", 2020, "9789511370840", 14.95, catrepository.findByName("Tietokirjat").get(0)));
			bookrepository.save(new Book("Uljas uusi maailma", "Aldous Huxley", 1932, "9789513199135", 17.95, catrepository.findByName("Scifi").get(0)));
			bookrepository.save(new Book("Vuonna 1984", "George Orwell", 1949, "9789510404478", 17.95, catrepository.findByName("Scifi").get(0)));
			
			log.info("fetch all books");
			for (Book book : bookrepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
