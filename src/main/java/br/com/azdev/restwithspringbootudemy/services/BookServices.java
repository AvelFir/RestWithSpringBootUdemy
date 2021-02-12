package br.com.azdev.restwithspringbootudemy.services;

import br.com.azdev.restwithspringbootudemy.data.model.Book;
import br.com.azdev.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.azdev.restwithspringbootudemy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    public Book findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public List<Book> findAll(){
        return repository.findAll();
    }

    public Book create(Book book){
        return repository.save(book);
    }

    public Book update(Book book){
        Book book1 = repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return repository.save(book1);
    }

    public void delete(Integer id){
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(book);
    }
}
