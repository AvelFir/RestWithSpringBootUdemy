package br.com.azdev.restwithspringbootudemy.controller;

import br.com.azdev.restwithspringbootudemy.dto.PersonDTO;
import br.com.azdev.restwithspringbootudemy.model.Person;
import br.com.azdev.restwithspringbootudemy.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonServices personServices;

    @GetMapping(value= "{id}")
    public PersonDTO findById (@PathVariable("id") Long id){
        return new PersonDTO(personServices.findById(id));
    }

    @GetMapping()
    public List<PersonDTO> findAll (){
        List<PersonDTO> personsDTO = new ArrayList<>();
        List<Person> persons = personServices.findAll();
        for(Person p : persons){
            personsDTO.add(new PersonDTO(p));
        }
        return personsDTO;
    }

    @PostMapping()
    public PersonDTO create(@RequestBody PersonDTO person){
        return new PersonDTO(personServices.create(person.parsePerson()));
    }

    @PutMapping()
    public PersonDTO update(@RequestBody PersonDTO person){

        return new PersonDTO( personServices.create(person.parsePerson()));
    }

    @DeleteMapping(value= "{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        personServices.delete(id);
        return ResponseEntity.ok().build();
    }
}
