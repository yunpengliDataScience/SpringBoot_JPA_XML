package org.dragon.yunpeng.sandbox.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.dragon.yunpeng.sandbox.entities.Library;

@XmlRootElement(name = "Root")
public class RootElement {

	private List<Library> libraryList = new ArrayList<Library>();
	private List<Book> bookList = new ArrayList<Book>();

	@XmlElement(name = "Library")
	public List<Library> getLibraryList() {
		return libraryList;
	}

	public void setLibraryList(List<Library> libraryList) {
		this.libraryList = libraryList;
	}

	@XmlElement(name = "Book")
	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		if (!libraryList.isEmpty()) {
			for (Library library : libraryList) {
				stringBuilder.append(library.toString() + "\n");
			}
		}

		if (!bookList.isEmpty()) {
			for (Book book : bookList) {
				stringBuilder.append(book.toString() + "\n");
			}
		}

		return stringBuilder.toString();
	}
}
