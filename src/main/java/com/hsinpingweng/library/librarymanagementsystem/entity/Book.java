package com.hsinpingweng.library.librarymanagementsystem.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=10, max=13, message="Name should have at least 10 characters")
    private String isbn;

    @Size(max=100, message="Name should have at least 2 characters")
    private String name;

    @Size(max=100, message="Description should have at most 1000 characters")
    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="publisher_id")
    private Publisher publisher;

    protected Book() { }

    public Book(String isbn,
                String name,
                String description) {

        this.isbn = isbn;
        this.name = name;
        this.description = description;
    }


}
