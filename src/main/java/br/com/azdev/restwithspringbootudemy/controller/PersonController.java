package br.com.azdev.restwithspringbootudemy.controller;

import br.com.azdev.restwithspringbootudemy.services.PersonServices;
import br.com.azdev.restwithspringbootudemy.data.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    PersonServices services;

    @GetMapping(produces = {"application/json","application/xml"})
    public List<PersonVO> findAll (){
        return services.findAll();
    }

    @GetMapping(value= "{id}",produces = {"application/json","application/xml"})
    public PersonVO findById (@PathVariable("id") Long id){
        return services.findById(id);
    }

    @PostMapping(consumes = {"application/json","application/xml"},
            produces = {"application/json","application/xml"})
    public PersonVO create(@RequestBody PersonVO person){
        return services.create(person);
    }

    @PutMapping(consumes = {"application/json","application/xml"},
            produces = {"application/json","application/xml"})
    public PersonVO update(@RequestBody PersonVO person){
        return services.create(person);
    }

    @DeleteMapping(value= "{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}