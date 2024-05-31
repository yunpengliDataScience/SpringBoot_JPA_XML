package org.dragon.yunpeng.sandbox.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "AUTHOR")
    private String author;
    
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "LIBRARY_ID")
    private Long libraryID;
    
    //Set insertable=false, updatable=false when need to map a field more than once in an entity.
    //It avoids "org.hibernate.MappingException: Repeated column in mapping for entity", since libraryID is explicitly mapped to LIBRARY_ID.
    //But when Book is persisted, the libray_id needs to be manually set. JPA will not automatically manage it.
    //This way still enables JQL query on association, but maintaining the association should be more manually and explicitly.
    //See: https://stackoverflow.com/questions/15076463/another-repeated-column-in-mapping-for-entity-error
    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID", insertable=false, updatable=false)
    private Library library;

    @XmlElement
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @XmlTransient
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @XmlElement
	public Long getLibraryID() {
		return libraryID;
	}

	public void setLibraryID(Long library_id) {
		this.libraryID = library_id;
	}

}
