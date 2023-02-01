package com.example.demo.repositories;

import com.example.demo.services.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Integer> {

}
