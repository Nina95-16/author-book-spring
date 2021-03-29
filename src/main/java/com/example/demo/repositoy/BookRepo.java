package com.example.demo.repositoy;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book,Integer> {
List<Book> findAllByTitleStartingWith(String name);
}
