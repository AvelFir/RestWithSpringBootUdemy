package br.com.azdev.restwithspringbootudemy.controller;

import br.com.azdev.restwithspringbootudemy.services.PersonServices;
import br.com.azdev.restwithspringbootudemy.data.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    PersonServices services;

    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public List<PersonVO> findAll (){
        List<PersonVO> persons = services.findAll();
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    @GetMapping(value= "{id}",produces = {"application/json","application/xml","application/x-yaml"})
    public PersonVO findById (@PathVariable("id") Long id){

        PersonVO personVO = services.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

    @PostMapping(consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person){
         PersonVO personVO = services.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @PutMapping(consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person){
        PersonVO personVO = services.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    @DeleteMapping(value= "{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}