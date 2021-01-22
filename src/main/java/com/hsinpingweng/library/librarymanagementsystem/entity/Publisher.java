package com.hsinpingweng.library.librarymanagementsystem.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=5, message="Name should have at least 2 characters")
    private String name;

    @OneToMany(mappedBy="publisher")
    private Set<Book> books;

    public Publisher(String name) {
        this.name = name;
    }


}
