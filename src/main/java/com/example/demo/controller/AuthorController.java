package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.repositoy.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthorController {

    private final AuthorRepo authorRepo;

    @GetMapping("/allAuthors")
    public String allAuthors(ModelMap modelMap) {
        List<Author> allAuthors = authorRepo.findAll();
        modelMap.addAttribute("authors", allAuthors);
        return "allAuthors";
    }

    @GetMapping("/author/save")
    public String saveAuthor(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap) {
        if (id != null) {
            modelMap.addAttribute("author", authorRepo.getOne(id));
        } else {
            modelMap.addAttribute("author", new Author());
        }
        return "authorEdit";
    }

    @PostMapping("/author/save")
    public String addAuthor(@ModelAttribute Author author) {
        authorRepo.save(author);
        return "redirect:/allAuthors";
    }

    @GetMapping("/author/delete")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepo.deleteById(id);
        return "redirect:/";
    }
}
