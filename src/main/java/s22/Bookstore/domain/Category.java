package s22.Bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long categoryid;
	private String name;
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "category" )
	private List<Book> books;
	
	// Luodaan constructorit
	
	public Category() {}

	public Category(String name) {
		super();
		this.name = name;
	}

	// Luodaan Gettersit ja Settersit
	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	// Luodaan toString. HUOM. Ilman books-listaa, sillä sen toString sisältää jo categoryn.
	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}

}