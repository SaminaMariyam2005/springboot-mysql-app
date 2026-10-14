package com.example.mysqlapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    // GET /api/people
    @GetMapping
    public List<Person> getAll() {
        return service.getAll();
    }

    // GET /api/people/1
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        return service.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Map.of(
                                        "error",
                                        "Person not found"
                                ))
                );
    }

    // POST /api/people
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Person person) {

        if (person.getName() == null
                || person.getName().isBlank()
                || person.getEmail() == null
                || person.getEmail().isBlank()
                || person.getCity() == null
                || person.getCity().isBlank()) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "error",
                            "Name, email, and city are required"
                    ));
        }

        if (person.getName().trim().length() > 100
                || person.getEmail().trim().length() > 150
                || person.getCity().trim().length() > 100) {

            return ResponseEntity.badRequest()
                    .body(Map.of(
                            "error",
                            "One or more fields exceed the allowed length"
                    ));
        }

        Person saved = service.save(person);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saved);
    }
}

