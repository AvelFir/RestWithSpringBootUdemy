package br.com.azdev.restwithspringbootudemy.controller;

import br.com.azdev.restwithspringbootudemy.data.model.Book;
import br.com.azdev.restwithspringbootudemy.data.vo.v1.BookVO;
import br.com.azdev.restwithspringbootudemy.repository.BookRepository;
import br.com.azdev.restwithspringbootudemy.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    BookServices service;

    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public List<BookVO> findAll(){
        List<BookVO> booksVO = service.findAll();
        booksVO.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
        return booksVO;
    }

    @GetMapping(value = "{id}",produces = {"application/json","application/xml","application/x-yaml"})
    public BookVO findById(@PathVariable("id") Integer id){
        BookVO bookVO = service.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }

    @PostMapping(consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public BookVO create(@RequestBody BookVO book){
        BookVO bookVO =service.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @PutMapping(consumes = {"application/json","application/xml","application/x-yaml"},
    produces = {"application/json","application/xml","application/x-yaml"})
    public BookVO update(@RequestBody BookVO book){
        BookVO bookVO = service.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
