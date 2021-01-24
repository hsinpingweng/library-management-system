package com.hsinpingweng.library.librarymanagementsystem.repository;

import com.hsinpingweng.library.librarymanagementsystem.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Publisher findByName(String name);
}
