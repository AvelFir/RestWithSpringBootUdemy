package br.com.azdev.restwithspringbootudemy.controller;

import br.com.azdev.restwithspringbootudemy.data.model.Book;
import br.com.azdev.restwithspringbootudemy.data.vo.v1.BookVO;
import br.com.azdev.restwithspringbootudemy.repository.BookRepository;
import br.com.azdev.restwithspringbootudemy.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Book Endpoint")
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookServices service;
    @Operation(summary = "Find all books recorded")
    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public List<BookVO> findAll(){
        List<BookVO> booksVO = service.findAll();
        booksVO.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
        return booksVO;
    }
    @Operation(summary = "Find a specific book by id")
    @GetMapping(value = "{id}",produces = {"application/json","application/xml","application/x-yaml"})
    public BookVO findById(@PathVariable("id") Integer id){
        BookVO bookVO = service.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;
    }
    @Operation(summary = "Create book")
    @PostMapping(consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public BookVO create(@RequestBody BookVO book){
        BookVO bookVO =service.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }
    @Operation(summary = "Edit book")
    @PutMapping(consumes = {"application/json","application/xml","application/x-yaml"},
    produces = {"application/json","application/xml","application/x-yaml"})
    public BookVO update(@RequestBody BookVO book){
        BookVO bookVO = service.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
        return bookVO;
    }
    @Operation(summary = "Delete book")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
