package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tblBook")
public class Book {

	public Book() {
		super();
	}

	public Book(String title) {
		super();
		this.title = title;
	}

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@Column(name="title")
	private String title;

	@ManyToMany
	private List<Author> authors = new ArrayList<Author>();

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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}
