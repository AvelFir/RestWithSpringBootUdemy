package br.com.azdev.restwithspringbootudemy.controller;

import br.com.azdev.restwithspringbootudemy.data.model.Book;
import br.com.azdev.restwithspringbootudemy.data.vo.v1.BookVO;
import br.com.azdev.restwithspringbootudemy.repository.BookRepository;
import br.com.azdev.restwithspringbootudemy.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    BookServices service;

    @GetMapping
    public List<Book> findAll(){
       return service.findAll();
    }

    @GetMapping({"{id}"})
    public Book findById(@PathVariable("id") Integer id){
        return service.findById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book){
        return service.update(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book){
        return service.create(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
