package com.example.demo.services;

import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public ResponseEntity<Person> addMeesageToPerson(int personId, Message message) {
        if(!(repository.existsById(personId)))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Person person = repository.findById(personId).get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);
        return new ResponseEntity<Person>(repository.save(person),HttpStatus.OK);
    }

    public ResponseEntity<Person> deleteMessageById(int personId, int messageId){
        if(repository.existsById(personId)){
            Person person = repository.findById(personId).get();
            List<Message> messageList = person.messages;
            for (Message message:
                 messageList) {
                if(message.getId() == messageId)
                    person.deleteMessage(message);
            }
            return new ResponseEntity<Person>(repository.save(person),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Message> messagesFromPerson(int personId){
        Person person = repository.findById(personId).get();
        return person.messages;
    }

    public ResponseEntity<Person> updatePerson(int id, Person person){
        if(repository.existsById(id)){
            Person updatePerson = repository.findById(id).get();
            updatePerson.setFirstname(person.getFirstname());
            updatePerson.setSurname(person.getSurname());
            updatePerson.setLastname(person.getLastname());
            updatePerson.setBirthday(person.getBirthday());
            return new ResponseEntity(repository.save(updatePerson), HttpStatus.OK);
        }else{
            return new ResponseEntity(repository.save(person), HttpStatus.CREATED);
        }
    }
}