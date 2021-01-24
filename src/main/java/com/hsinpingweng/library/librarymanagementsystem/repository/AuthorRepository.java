package com.hsinpingweng.library.librarymanagementsystem.repository;

import com.hsinpingweng.library.librarymanagementsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByName(String name);

}
