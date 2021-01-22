package com.hsinpingweng.library.librarymanagementsystem.entity;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2, message="Name should have at least 2 characters")
    private String name;

    @Size(max=1000, message="Description should have at most 1000 characters")
    private String description;


    @OneToMany(mappedBy="author")
    private Set<Book> books;

    public Author(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
