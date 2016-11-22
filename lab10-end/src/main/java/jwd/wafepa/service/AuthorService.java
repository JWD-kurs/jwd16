package jwd.wafepa.service;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Author;

public interface AuthorService {
	Author save(Author author);
	Page<Author> findAll(int page);

}
