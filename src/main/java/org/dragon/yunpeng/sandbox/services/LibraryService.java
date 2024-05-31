package org.dragon.yunpeng.sandbox.services;

import java.util.List;

import javax.transaction.Transactional;

import org.dragon.yunpeng.sandbox.entities.Book;
import org.dragon.yunpeng.sandbox.entities.Library;
import org.dragon.yunpeng.sandbox.repositories.BookRepository;
import org.dragon.yunpeng.sandbox.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "libraryService")
public class LibraryService {

	@Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;
    
    @Transactional
    public Library saveLibrary(Library library) {
        return libraryRepository.save(library);
    }
    
    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Library getLibraryById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }
}
