package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repositoy.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public void save(Book book){
        bookRepo.save(book);
    }
    public Book getOne(int id){
        return bookRepo.getOne(id);
    }
   public List<Book> findAll(){
       return bookRepo.findAll();
   }

   public void deleteById(int id){
        bookRepo.deleteById(id);
   }
}
