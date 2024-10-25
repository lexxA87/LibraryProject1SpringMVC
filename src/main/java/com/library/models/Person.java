package com.library.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int person_id;

    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+",
            message = "Please, enter you whole name")
    private String personName;

    @Min(value = 1900, message = "No less than 1900")
    @Max(value = 2025, message = "No more than this year")
    private int personYearOfBirth;

    private List<Book> books = new ArrayList<>();

    public Person() {}

    public Person(int person_id, String personName, int personYearOfBirth) {
        this.person_id = person_id;
        this.personName = personName;
        this.personYearOfBirth = personYearOfBirth;
    }

    public int getPerson_id() {
        return person_id;
    }
    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public int getPersonYearOfBirth() {
        return personYearOfBirth;
    }
    public void setPersonYearOfBirth(int personYearOfBirth) {
        this.personYearOfBirth = personYearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", personName='" + personName + '\'' +
                ", personYearOfBirth=" + personYearOfBirth +
                ", books=" + books +
                '}';
    }
}
