package s22.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	////// Eli voidaan käyttää myös tätä rakennetta:
	//// we need repository interfaces to put demo data to db, jos käytetään:
	// public void run(String... args) throws Exception {
	//@Autowired
	//BookstoreRepository bookrepository;
	//@Autowired
	//CategoryRepository catRepository;
	//@Override
	//public void run(String... args) throws Exception {
	//
	//log.info("LUODAAN DEMODATAA");
	//log.info("Luo muutama omistaja");
	//Owner owner1 = new Owner("Macy", "Minnie", "Tampere", "150578-223L", 1970);
	//ownerRepository.save(owner1);
	//ownerRepository.save(new Owner("John", "Johnson", "Helsinki", "111177-134M", 1977));
	/// Add car object and link to owners and save these to db
	//carRepository.save(new Car("Volkswagen", "Golf", "White", "Abc-123", 2021, 59000, owner1));
	//carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-112", 2021, 59000,
	//		ownerRepository.findBySsn("111177-134M").get(0)));
		
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
				log.info("Kirja:kategoria on " + book.getTitle() + ":" + book.getCategory().getName());
			}

		};
	}
}
