package jwd.wafepa.repository;

import jwd.wafepa.model.Author;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository  extends PagingAndSortingRepository<Author, Long> {

}
