package org.dragon.yunpeng.sandbox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.dragon.yunpeng.sandbox.entities.Library;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibraryServiceTest {

	private String workingDirectory;

	@Autowired
	private LibraryService libraryService;

	@Autowired
	private H2DatabaseUtil h2DatabaseUtil;

	@BeforeEach
	public void setUp() {
		workingDirectory = System.getProperty("user.dir");
		System.out.println("workingDirectory=" + workingDirectory);
	}

	@AfterEach
	public void tearDown() {
		
	}

	// @Test
	public void testBackupDatabase() {
		String backupFilePath = workingDirectory + File.separator + "databaseBackup" + File.separator + "backupDB.zip";
		h2DatabaseUtil.backupDatabase(backupFilePath);
	}

	// @Test
	public void testSaveBook() {

		// Save Library first
		Library library = new Library();
		library.setName("My Library 1");
		Library savedLibrary = libraryService.saveLibrary(library);

		Book book1 = new Book();
		book1.setTitle("My Sand Box 1");
		book1.setAuthor("Yunpeng Li");
		book1.setIsbn("978-0-316-76948-1");

		long libraryId = savedLibrary.getId();

		// You can explicitly set libraryId to a book.
		book1.setLibraryID(libraryId);

		Book newBook1 = libraryService.saveBook(book1);

		// Get Library by saved library id
		Library lib = libraryService.getLibraryById(libraryId);

		// Get associated books from library
		List<Book> books = lib.getBooks();
		System.out.println("Number of associated books: " + books.size());
	}

	// @Test
	public void testSaveBook2() {

		// Save Library first
		Library library = new Library();
		library.setName("My Library 2");
		Library savedLibrary = libraryService.saveLibrary(library);

		Book book2 = new Book();
		book2.setTitle("My Sand Box 2");
		book2.setAuthor("Yunpeng Li");
		book2.setIsbn("978-0-316-76948-2");

		long libraryId = savedLibrary.getId();

		// You can also set saved Library object to Book, but the foreign key column
		// LIBRARY_ID is not automatically set by JPA, so LIBRARY_ID is NULL when use
		// use @JoinColumn(name = "LIBRARY_ID", insertable=false, updatable=false).
		book2.setLibrary(savedLibrary);

		Book newBook2 = libraryService.saveBook(book2);

		// Get Library by saved library id
		Library lib = libraryService.getLibraryById(libraryId);

		// Get associated books from library
		List<Book> books = lib.getBooks();
		System.out.println("Number of associated books: " + books.size());
	}

	// @Test
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

		// Can save library and books, but LIBRARY_ID of each book is NULL since JPA
		// does not automatically manage setting value in LIBRARY_ID when use
		// @JoinColumn(name = "LIBRARY_ID", insertable=false, updatable=false)
		Library savedLibrary = libraryService.saveLibrary(library);

		assertNotNull(savedLibrary);
		assertNotNull(savedLibrary.getId());
		assertEquals(2, savedLibrary.getBooks().size());
	}

}
