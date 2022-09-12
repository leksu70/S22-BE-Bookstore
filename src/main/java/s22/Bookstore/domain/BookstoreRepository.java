package s22.Bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookstoreRepository extends CrudRepository<Book, Long> {

    //List<Book> findByBookAuthor(String author);
	//List<Book> findByBookAuthorAndTitle(String author, String title);
	
	// Enable ignoring case
	//List<Book> findByBookTitleIgnoreCase(String title);
	
	// Ebanling ORDER BY for a query
	//List<Book> findByBookAuthorOrderByTitle(String author);
    
}