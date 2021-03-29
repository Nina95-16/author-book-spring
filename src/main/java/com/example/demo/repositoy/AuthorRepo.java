package com.example.demo.repositoy;

import com.example.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    List<Author> findAllByNameStartingWith(String name);
}
