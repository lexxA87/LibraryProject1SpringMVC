package com.library.dao;

import com.library.models.Book;
import com.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE person_id=?",
                new BeanPropertyRowMapper<>(Person.class), id);
    }

    public Person getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE person_id=?",
                new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(personName, personYearOfBirth) VALUES(?, ?)",
                person.getPersonName(), person.getPersonYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET personName=?, personYearOfBirth=? WHERE person_id=?",
                updatedPerson.getPersonName(), updatedPerson.getPersonYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }
}
