package jwd.wafepa.web.controller;

import java.util.List;

import jwd.wafepa.model.Activity;
import jwd.wafepa.model.Book;
import jwd.wafepa.service.BookService;
import jwd.wafepa.web.dto.ActivityDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/books")
public class ApiBookController {
	
	@Autowired
	BookService bookService;
	
//	@RequestMapping(method = RequestMethod.GET)
//	ResponseEntity<List<Book>> getBooks(
//			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
//
//		List<Book> books = bookService.findAll(page).getContent();
//
//		return new ResponseEntity<>(books, HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Book>> getBooksByAuthor(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "name", required = false) String name) {
		List<Book> books;
		if(name!=null){
			books = bookService.findByAuthor(name, name, page).getContent();
		}
		else{
			books = bookService.findAll(page).getContent();
		}
		
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<Book> getBook(@PathVariable Long id) {
		Book book = bookService.findOne(id);
		if (book == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Book> add(@RequestBody Book newBook) {

		Book savedBook = bookService.save(newBook);

		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Book> delete(@PathVariable Long id) {
		Book deleted = bookService.delete(id);

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<Book> edit(@RequestBody Book book,
			@PathVariable Long id) {

		if (id != book.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Book persisted = bookService.save(book);

		return new ResponseEntity<>(persisted, HttpStatus.OK);
	}

	
}
