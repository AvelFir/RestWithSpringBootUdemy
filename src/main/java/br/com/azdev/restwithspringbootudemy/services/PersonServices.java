package br.com.azdev.restwithspringbootudemy.services;

import br.com.azdev.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.azdev.restwithspringbootudemy.model.Person;
import br.com.azdev.restwithspringbootudemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public Person findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person create(Person person){
        return repository.save(person);
    }

    public Person update(Person person){
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(person);
    }

    public void delete(Long id){
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(person);
    }
}
