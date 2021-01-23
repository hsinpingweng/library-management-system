package com.hsinpingweng.library.librarymanagementsystem.repository;

import com.hsinpingweng.library.librarymanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);

}
