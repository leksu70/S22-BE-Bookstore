package s22.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import s22.Bookstore.domain.Book;
import s22.Bookstore.domain.BookstoreRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookstoreRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			// Book(String title, String author, int year, String isbn, double price)
			// Book(String title, String author)
			repository.save(new Book("Pimeät kuut", "Tommi Kinnunen", 2022, "9789510480991", 23.90));
			repository.save(new Book("Matkani somen huipulle", "Roni Back", 2002, "9789511451938", 24.95));
			repository.save(new Book("Uljas uusi maailma", "Aldous Huxley", 1932, "9789513199135", 17.95));
			repository.save(new Book("Vuonna 1984", "George Orwell", 1949, "9789510404478", 17.95));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
