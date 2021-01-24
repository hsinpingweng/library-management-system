package com.hsinpingweng.library.librarymanagementsystem.controller;

import com.hsinpingweng.library.librarymanagementsystem.entity.Author;
import com.hsinpingweng.library.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepo;

    @GetMapping
    public String index (Model model){

        List<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);

        return "author/index";
    }

    @GetMapping("/add")
    public String add (Author author){
        return "author/add";
    }

    @PostMapping("/add")
    public String add (Author author, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) return "author/add";

        Author authorExist = authorRepo.findByName(author.getName());
        if (authorExist != null) {

            redirectAttributes.addFlashAttribute("message", "Author exists!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            // add value in session
            redirectAttributes.addFlashAttribute("authorInfo", author);
        } else {
            redirectAttributes.addFlashAttribute("message", "Author added");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            authorRepo.save(author);
        }

        return "redirect:/authors/add";
    }

}
