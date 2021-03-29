package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private  final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/book/save")
    public String saveBook(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap) {
        if (id != null) {
            modelMap.addAttribute("book", bookService.getOne(id));
            modelMap.addAttribute("author",authorService.findAll());
        } else {
            modelMap.addAttribute("book", new Book());
        }
        return "editBook";
    }

    @PostMapping("/book/save")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/";
    }

    @GetMapping("/allBooks")
    public String allBooks(ModelMap modelMap) {
        List<Book> allBooks = bookService.findAll();
        modelMap.addAttribute("books", allBooks);
        return "allBooks";
    }

    @GetMapping("/book/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookService.deleteById(id);
        return "redirect:/allBooks";
    }
}
