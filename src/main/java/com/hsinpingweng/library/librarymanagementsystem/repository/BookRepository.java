package com.hsinpingweng.library.librarymanagementsystem.repository;

import com.hsinpingweng.library.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByIsbn(String isbn);
}
