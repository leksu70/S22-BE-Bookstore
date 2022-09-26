package s22.Bookstore.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min=1, max=50)
	private String title, author;
	
	//// Jos halutaan käyttää varattu muuttujaa year, silloin se voidaan tehdä näin:
	// @Column(name = "book_year")
	// private int year;
	
	@Min(value=1800, message="min value is 1800")
	@Max(value=2022, message="max value is 2022")
	private int bookYear;
	@NotEmpty
	private String isbn;
	private double price;
	
	@ManyToOne(fetch = FetchType.EAGER) // Lisätty FetchType 26.9.
    @JoinColumn(name = "catid")
    private Category category;
	
	public Book() {
		super();
	}

	// Normaalit lisäykset.
	public Book(String title, String author, int bookYear, String isbn, double price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.bookYear = bookYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}

	// Manuaalisiin lisäyksiin.
	public Book(Long id, String title, String author, int bookYear, String isbn, double price, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.bookYear = bookYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}
	
	public Book(String title, String author, int bookYear, String isbn, double price) {
		super();
		this.title = title;
		this.author = author;
		this.bookYear = bookYear;
		this.isbn = isbn;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getBookYear() {
		return bookYear;
	}

	public void setBookYear(int bookYear) {
		this.bookYear = bookYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", bookYear=" + bookYear + ", isbn="
					+ isbn + ", price=" + price + ", category=" +  this.getCategory() + "]";
		else
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", bookYear=" + bookYear + ", isbn="
					+ isbn + ", price=" + price + "]";
	}

}
