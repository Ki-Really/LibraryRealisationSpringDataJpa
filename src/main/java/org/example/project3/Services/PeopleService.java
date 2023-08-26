package org.example.project3.Services;

import org.example.project3.models.Book;
import org.example.project3.models.Person;
import org.example.project3.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }
    @Transactional
    public void save(Person person){
    peopleRepository.save(person);
    }
    @Transactional
    public void update(int id,Person updatedPerson){
     updatedPerson.setId(id);
     peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id){
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            // Мы внизу итерируемся по книгам, поэтому они точно будут загружены, но на всякий случай
            // не мешает всегда вызывать Hibernate.initialize()
            // (на случай, например, если код в дальнейшем поменяется и итерация по книгам удалится)

            return person.get().getBooks();
        }
        else {
            return null;
        }
       /* return peopleRepository.getBooksByPersonId(id);*/
    }
    /*public Optional<Person> findByPersonName(String name){
        Person person = peopleRepository.findByPersonName(name);
        Optional<Person> opt = Optional.ofNullable(person);
        return opt;
    }*/
}
