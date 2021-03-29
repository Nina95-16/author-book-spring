package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repositoy.AuthorRepo;
import com.example.demo.repositoy.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {
    public final AuthorRepo authorRepo;
    public final BookRepo bookRepo;

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal Principal principal, ModelMap modelMap) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        modelMap.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, ModelMap modelMap) {
        if (keyword == null || keyword.length() == 0) {
            return "home";
        }
        List<Author> allByName = authorRepo.findAllByNameStartingWith(keyword);
        modelMap.addAttribute("authors", allByName);
        List<Book> allByTitle = bookRepo.findAllByTitleStartingWith(keyword);
        modelMap.addAttribute("books", allByTitle);
        return "home";
    }

}
