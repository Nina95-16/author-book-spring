package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthorController {

    private final AuthorService authorService;
    @Value("${author.upload.dir}")
    private String uploadDir;

    @GetMapping("/allAuthors")
    public String allAuthors(ModelMap modelMap) {
        List<Author> allAuthors = authorService.findAll();
        modelMap.addAttribute("authors", allAuthors);
        return "allAuthors";
    }

    @GetMapping("/author/save")
    public String saveAuthor(@RequestParam(value = "id", required = false) Integer id, ModelMap modelMap) {
        if (id != null) {
            modelMap.addAttribute("author", authorService.getOne(id));
        } else {
            modelMap.addAttribute("author", new Author());
        }
        return "authorEdit";
    }

    @PostMapping("/author/save")
    public String addAuthor(@ModelAttribute Author author, @RequestParam("image") MultipartFile image) throws IOException {
        if (image != null) {
            String name = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File file = new File(uploadDir + File.separator + name);
            image.transferTo(file);
            author.setImageUrl(name);
        }
        authorService.save(author);
        return "redirect:/allAuthors";
    }

    @GetMapping("/author/image")
    public @ResponseBody
    byte[] getImage(@RequestParam("photoUrl") String photoUrl) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream(uploadDir + File.separator + photoUrl);
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/author/delete")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorService.deleteById(id);
        return "redirect:/";
    }
}
