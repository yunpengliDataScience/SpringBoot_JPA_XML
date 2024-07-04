package org.dragon.yunpeng.sandbox.services;

import java.io.File;
import java.util.List;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.dragon.yunpeng.sandbox.entities.Library;
import org.dragon.yunpeng.sandbox.pojos.RootElement;
import org.dragon.yunpeng.sandbox.repositories.BookRepository;
import org.dragon.yunpeng.sandbox.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private H2DatabaseUtil h2DatabaseUtil;

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

	@Transactional(rollbackFor = RuntimeException.class)
	public void saveEntitiesFromXmls() {

		String workingDirectory = System.getProperty("user.dir");
		String fileDirectory = workingDirectory + File.separator + "sampleXMLs" + File.separator;

		String[] tables = { "Book", "Library" };

		// h2DatabaseUtil.disableAllTableConstraints(tables);

		h2DatabaseUtil.disableConstraints();

		saveBookListFromXML(fileDirectory + "BookList.xml");
		// saveBookListFromXML(fileDirectory + "BookListBad.xml");
		saveLibraryListFromXML(fileDirectory + "LibraryListBad.xml");

		// throw new RuntimeException("failure!");
		// TODO: re-enable constraints outside the transaction.
		// h2DatabaseUtil.enableConstraints();

		// h2DatabaseUtil.checkDataIntegrity(tables);
	}
}
