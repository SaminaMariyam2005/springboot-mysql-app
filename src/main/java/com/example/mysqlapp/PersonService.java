package com.example.mysqlapp;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public Optional<Person> getById(Long id) {
        return repository.findById(id);
    }

    public Person save(Person person) {
        person.setId(null);

        person.setName(person.getName().trim());
        person.setEmail(person.getEmail().trim());
        person.setCity(person.getCity().trim());

        return repository.save(person);
    }
}

