package jwd.wafepa.service.impl;

import javax.transaction.Transactional;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Author;
import jwd.wafepa.model.Book;
import jwd.wafepa.repository.BookRepository;
import jwd.wafepa.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@Transactional
public class JpaBookService implements BookService{

	@Autowired
	BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		book = bookRepository.save(book);
		return book;
	}

	@Override
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}

	@Override
	public Page<Book> findAll(int page) {
		return bookRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Book delete(Long id) {
		Book book = findOne(id);
		if (book==null) {
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant activity");
		}
		System.out.println(book.getAuthors());
		bookRepository.delete(id);
		return book;
	}

	@Override
	public Page<Book> findByAuthor(String firstName, String lastName, int page) {
		return bookRepository.findByAuthorsFirstNameOrAuthorsLastName(firstName, lastName, new PageRequest(page, 10));
	}
	

	
	
}
