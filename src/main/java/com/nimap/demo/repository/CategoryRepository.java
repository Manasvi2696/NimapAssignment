package com.nimap.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimap.demo.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
