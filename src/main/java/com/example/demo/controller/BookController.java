package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repositoy.AuthorRepo;
import com.example.demo.repositoy.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
//     @GetMapping("/book/save")
//     public String saveBook(ModelMap modelMap){
//         modelMap.addAttribute("authors",authorRepo.findAll());
//         return "home";
//     }
    @PostMapping("/book/save")
    public String addBook(@ModelAttribute Book book,ModelMap modelMap) {
        modelMap.addAttribute("authors",authorRepo.findAll());
         bookRepo.save(book);
        return "home";
    }

    @GetMapping("/allBooks")
    public String allBooks(ModelMap modelMap) {
        List<Book> allBooks = bookRepo.findAll();
        modelMap.addAttribute("books", allBooks);
        return "allBooks";
    }
    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepo.deleteById(id);
        return "redirect:/";
    }
}
