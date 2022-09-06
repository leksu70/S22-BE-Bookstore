package s22.Bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookstoreRepository extends CrudRepository<Book, Long> {

    // List<Book> findByLastName(String lastName);
    
}