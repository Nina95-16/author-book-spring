package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repositoy.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

        public void save(Author author){
            authorRepo.save(author);
        }
        public Author getOne(int id){
            return authorRepo.getOne(id);
        }
        public List<Author> findAll(){
            return authorRepo.findAll();
        }

        public void deleteById(int id){
           authorRepo.deleteById(id);
        }
    }

