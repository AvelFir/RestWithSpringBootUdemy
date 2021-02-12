package br.com.azdev.restwithspringbootudemy.repository;

import br.com.azdev.restwithspringbootudemy.data.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
