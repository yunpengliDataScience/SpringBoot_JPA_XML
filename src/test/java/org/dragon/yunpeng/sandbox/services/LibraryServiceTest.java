package org.dragon.yunpeng.sandbox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.dragon.yunpeng.sandbox.entities.Library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibraryServiceTest {

	@Autowired
	private LibraryService libraryService;

	@Test
	public void testSaveBook() {

		Library library = new Library();
		library.setName("Central Library");
		Library savedLibrary = libraryService.saveLibrary(library);

		Book book1 = new Book();
		book1.setTitle("My Sand Box");
		book1.setAuthor("Yunpeng Li");
		book1.setIsbn("978-0-316-76948-0");

		long libraryId = savedLibrary.getId();
		book1.setLibraryID(libraryId);

		Book newBook1 = libraryService.saveBook(book1);

		libraryService.saveBook(newBook1);

		Library lib = libraryService.getLibraryById(libraryId);
		List<Book> books = lib.getBooks();
		System.out.println(books.size());
	}
	
	//@Test
	public void testSaveLibraryWithBooks() {
		Library library = new Library();
		library.setName("Central Library");

		Book book1 = new Book();
		book1.setTitle("The Catcher in the Rye");
		book1.setAuthor("J.D. Salinger");
		book1.setIsbn("978-0-316-76948-0");
		book1.setLibrary(library);

		Book book2 = new Book();
		book2.setTitle("To Kill a Mockingbird");
		book2.setAuthor("Harper Lee");
		book2.setIsbn("978-0-06-112008-4");
		book2.setLibrary(library);

		library.setBooks(Arrays.asList(book1, book2));

		Library savedLibrary = libraryService.saveLibrary(library);

		assertNotNull(savedLibrary);
		assertNotNull(savedLibrary.getId());
		assertEquals(2, savedLibrary.getBooks().size());
	}

	/*
	@Test
	public void testGetAllLibraries() {
		Library library = new Library();
		library.setName("Central Library");

		Book book1 = new Book();
		book1.setTitle("The Catcher in the Rye");
		book1.setAuthor("J.D. Salinger");
		book1.setIsbn("978-0-316-76948-0");
		book1.setLibrary(library);

		Book book2 = new Book();
		book2.setTitle("To Kill a Mockingbird");
		book2.setAuthor("Harper Lee");
		book2.setIsbn("978-0-06-112008-4");
		book2.setLibrary(library);

		library.setBooks(Arrays.asList(book1, book2));

		libraryService.saveLibrary(library);

		List<Library> libraries = libraryService.getAllLibraries();

		assertNotNull(libraries);
		assertEquals(1, libraries.size());
	}

	@Test
	public void testGetLibraryById() {
		Library library = new Library();
		library.setName("Central Library");

		Book book1 = new Book();
		book1.setTitle("The Catcher in the Rye");
		book1.setAuthor("J.D. Salinger");
		book1.setIsbn("978-0-316-76948-0");
		book1.setLibrary(library);

		Book book2 = new Book();
		book2.setTitle("To Kill a Mockingbird");
		book2.setAuthor("Harper Lee");
		book2.setIsbn("978-0-06-112008-4");
		book2.setLibrary(library);

		library.setBooks(Arrays.asList(book1, book2));

		Library savedLibrary = libraryService.saveLibrary(library);

		Library foundLibrary = libraryService.getLibraryById(savedLibrary.getId());

		assertNotNull(foundLibrary);
		assertEquals("Central Library", foundLibrary.getName());
		assertEquals(2, foundLibrary.getBooks().size());
	}
	*/
}
