package br.com.azdev.restwithspringbootudemy.services;

import br.com.azdev.restwithspringbootudemy.converter.DozerConverter;
import br.com.azdev.restwithspringbootudemy.converter.custom.PersonConverter;
import br.com.azdev.restwithspringbootudemy.exception.ResourceNotFoundException;
import br.com.azdev.restwithspringbootudemy.model.Person;
import br.com.azdev.restwithspringbootudemy.repository.PersonRepository;
import br.com.azdev.restwithspringbootudemy.vo.PersonVO;
import br.com.azdev.restwithspringbootudemy.vo.v2.PersonVOV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;
    @Autowired
    PersonConverter converter;

    public PersonVO create(PersonVO person){
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person){
        var entity = converter.convertVoToEntity(person);
        var vo = converter.convertEntityToVO(repository.save(entity) );
        return vo;
    }

    public List<PersonVO> findAll(){
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }


    public PersonVO findById(Long id){
        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        return DozerConverter.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id){
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        repository.delete(person);
    }
}
