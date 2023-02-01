package com.example.demo.controllers;

import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.Message;
import com.example.demo.services.Person;
import com.example.demo.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MainController {

    private PersonRepository personRepository;
    PersonService service;

    public MainController(PersonRepository personRepository, PersonService service) {
        this.personRepository = personRepository;
        this.service = service;
    }

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return personRepository.findById(id);
    }

    @PostMapping("/persons")
    public Person addPerson(@RequestBody Person person) {
        personRepository.save(person);
        return person;
    }

    @PostMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        return service.updatePerson(id,person);
    }

    @DeleteMapping("/persons/{id}")
    public void deletePerson(@PathVariable int id) {
        personRepository.deleteById(id);
    }

    @PostMapping("/persons/{id}/messages")
    public ResponseEntity<Person> addMessage(@PathVariable int id, @RequestBody Message message){
        return service.addMeesageToPerson(id, message);
    }

    @PostMapping("persons/{personId}/messages/{messageId}")
    public ResponseEntity<Person> deleteMessageById(@PathVariable int personId, @PathVariable int messageId){
        return service.deleteMessageById(personId,messageId);
    }

    @GetMapping("persons/{id}/messages")
    public List<Message> messagesFromPerson(@PathVariable int id){
        return service.messagesFromPerson(id);
    }
}
