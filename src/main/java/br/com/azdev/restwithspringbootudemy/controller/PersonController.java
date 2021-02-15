package br.com.azdev.restwithspringbootudemy.controller;

import br.com.azdev.restwithspringbootudemy.services.PersonServices;
import br.com.azdev.restwithspringbootudemy.data.vo.v1.PersonVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name= "Person endpoint",description = "Description for person")
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    PersonServices services;

    //@CrossOrigin(origins = "http://localhost:8080")
    @Operation(summary = "Find all people Recorded")
    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public List<PersonVO> findAll (){
        List<PersonVO> persons = services.findAll();
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    //@CrossOrigin(origins = {"http://localhost:8080","http://www.google.com.br"})
    @Operation(summary = "Find a specific person per ID")
    @GetMapping(value= "{id}",produces = {"application/json","application/xml","application/x-yaml"})
    public PersonVO findById (@PathVariable("id") Long id){

        PersonVO personVO = services.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }
    @Operation(summary = "Create people")
    @PostMapping(consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person){
         PersonVO personVO = services.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }
    @Operation(summary = "Edit people")
    @PutMapping(consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public PersonVO update(@RequestBody PersonVO person){
        PersonVO personVO = create(person);
        return personVO;
    }
    @Operation(summary = "Delete people")
    @DeleteMapping(value= "{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        services.delete(id);
        return ResponseEntity.ok().build();
    }
}