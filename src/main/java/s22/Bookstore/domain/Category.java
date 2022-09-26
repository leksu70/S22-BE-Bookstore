package s22.Bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Long catid;
	
	@Size(min=2, max=30)
	private String name;
	
	@JsonIgnore	// this prevents the infinate loop in our books REST Service, Lisätty 26.9.
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "category" )
	private List<Book> books;
	
	// Luodaan constructorit
	
	public Category() {}

	public Category(String name) {
		super();
		this.name = name;
	}

	// Luodaan Gettersit ja Settersit
	public Long getCatid() {
		return catid;
	}

	public void setCatid(Long catid) {
		this.catid = catid;
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
		return "Category [catid=" + catid + ", name=" + name + "]";
	}

}