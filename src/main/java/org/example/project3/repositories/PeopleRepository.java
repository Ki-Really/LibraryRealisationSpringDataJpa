package org.example.project3.repositories;

import org.example.project3.models.Book;
import org.example.project3.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
 Optional<Person> findByName(String name);
}
