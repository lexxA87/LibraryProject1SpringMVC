package com.library.dao;

import com.library.models.Book;
import com.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
                new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> getBooksByPersonId(int personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?",
                new BeanPropertyRowMapper<>(Book.class), personId);
    }

    public Book show(int id) {

        return jdbcTemplate.queryForObject("SELECT * FROM book WHERE book_id=?",
                new BeanPropertyRowMapper<>(Book.class), id);

    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person" +
                        " ON book.person_id = person.person_id WHERE book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
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
