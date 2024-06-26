package org.dragon.yunpeng.sandbox.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Library")
@Entity
@Table(name = "LIBRARY")
public class Library {

	// Disable auto-generate Id; otherwise, ID is managed by JPA.
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval =
	// true)
	@OneToMany(mappedBy = "library")
	private List<Book> books;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Library [id=" + id + ", name=" + name + "]";
	}

}
