package br.com.azdev.restwithspringbootudemy.services;

import br.com.azdev.restwithspringbootudemy.converter.DozerConverter;
import br.com.azdev.restwithspringbootudemy.data.model.Book;
import br.com.azdev.restwithspringbootudemy.data.vo.v1.BookVO;
import br.com.azdev.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.azdev.restwithspringbootudemy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    BookRepository repository;

    public BookVO findById(Integer id){
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public List<BookVO> findAll(){
         return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
    }

    public BookVO create(BookVO book){
        Book entity = DozerConverter.parseObject(book, Book.class);
        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }

    public BookVO update(BookVO book){
        Book entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setId(book.getKey());
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }

    public void delete(Integer id){
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(book);
    }
}
