package org.dragon.yunpeng.sandbox.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.dragon.yunpeng.sandbox.entities.Library;
import org.dragon.yunpeng.sandbox.pojos.RootElement;
import org.dragon.yunpeng.sandbox.repositories.BookRepository;
import org.dragon.yunpeng.sandbox.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "libraryService")
public class LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private XMLFileService xmlFileService;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void disableConstraints() {
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

		System.out.println("'SET REFERENTIAL_INTEGRITY FALSE' has been executed.");
	}

	@Transactional
	public void enableConstraints() {
		entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
		System.out.println("'SET REFERENTIAL_INTEGRITY TRUE' has been executed.");
	}

	@Transactional
	public void checkDataIntegrity(String[] tables) {

		for (String tableName : tables) {
			String stmt = "ALTER TABLE %s SET REFERENTIAL_INTEGRITY TRUE CHECK;";
			String sql = String.format(stmt, tableName);

			// Use EntityManager instead of JdbcTemplate to execute query, since
			// EntityManager is managed by Hibernate JPA.
			entityManager.createNativeQuery(sql).executeUpdate();
		}

		System.out.println("checkDataIntegrity() has been done.");
	}

	// @Transactional
	public Library saveLibrary(Library library) {
		return libraryRepository.save(library);
	}

	// @Transactional
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	public List<Library> getAllLibraries() {
		return libraryRepository.findAll();
	}

	public Library getLibraryById(Long id) {
		return libraryRepository.findById(id).orElse(null);
	}

	@Transactional
	public void saveLibraryListFromXML(String filePath) {
		libraryRepository.deleteAll();

		RootElement root = xmlFileService.unmarshallXMLToRootElement(filePath);

		for (Library library : root.getLibraryList()) {
			// saveLibrary(library);
			libraryRepository.save(library);
		}
	}

	@Transactional
	public void saveBookListFromXML(String filePath) {

		bookRepository.deleteAll();

		RootElement root = xmlFileService.unmarshallXMLToRootElement(filePath);

		for (Book book : root.getBookList()) {
			// saveBook(book);
			bookRepository.save(book);
		}
	}

	// Caller needs to disable constraints then reenable constraints
	@Transactional(rollbackFor = Exception.class)
	public void saveEntitiesFromXmls(String fileDirectory) {

		String[] tables = { "Book", "Library" };

		saveBookListFromXML(fileDirectory + "BookList.xml");
		saveLibraryListFromXML(fileDirectory + "LibraryList.xml");

		checkDataIntegrity(tables);
	}
}
