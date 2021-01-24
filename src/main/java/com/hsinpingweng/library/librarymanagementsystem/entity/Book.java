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

    @Size(min=1, max=100, message="Name should have at least 1 characters and at most 100 characters")
    private String name;

    @Size(max=1000, message="Description should have at most 1000 characters")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
