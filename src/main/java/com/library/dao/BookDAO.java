package com.library.dao;

import com.library.models.Book;
import com.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    private final PersonDAO personDAO;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.personDAO = personDAO;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book",
                new BeanPropertyRowMapper<Book>(Book.class));
    }

    public Book show(int id) {
        Person person = null;

        List<Person> people = new ArrayList<>();
        people = personDAO.index();


        Book book = jdbcTemplate.queryForObject("SELECT * FROM book WHERE book_id=?",
                new BeanPropertyRowMapper<>(Book.class), id);
        
        Integer person_id = jdbcTemplate.queryForObject("SELECT person_id FROM book WHERE book_id=?",
                Integer.class, id);
        assert book != null;
        if(person_id != null) {
            person = personDAO.getById(person_id);
            book.setPerson(person);
        }

        book.setPeople(people);
        return book;
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(bookTitle, bookAuthor, bookYear) VALUES(?,?,?)",
                book.getBookTitle(), book.getBookAuthor(), book.getBookYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET bookTitle=?, bookAuthor=?, bookYear=? WHERE book_id=?",
                updatedBook.getBookTitle(), updatedBook.getBookAuthor(), updatedBook.getBookYear(), id);
    }

    public void updatePersonIdToNull(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?", id);
    }

    public void updatePersonId(int id, int person_id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person_id, id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }
}
