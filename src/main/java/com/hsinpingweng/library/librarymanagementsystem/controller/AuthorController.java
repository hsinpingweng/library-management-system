package com.hsinpingweng.library.librarymanagementsystem.controller;

import com.hsinpingweng.library.librarymanagementsystem.entity.Author;
import com.hsinpingweng.library.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

        return "author/list";
    }

    @GetMapping("{id}")
    public String index(@PathVariable int id, Model model){
        Author author = authorRepo.getOne(id);
        model.addAttribute("author", author);

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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Author author = authorRepo.getOne(id);
        model.addAttribute("author", author);

        return "author/edit";
    }


    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @Valid Author author, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("authorName", author.getName());
            return "authors/edit";
        }

        Author authorExist = authorRepo.findByName(author.getName());

        if (authorExist != null) {
            // add value in session
            redirectAttributes.addFlashAttribute("message", "Author exists!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        } else {
            redirectAttributes.addFlashAttribute("message", "Author edited");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            author.setId(id);
            authorRepo.save(author);
        }

        return "redirect:/authors/edit/" + id;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {

        Author author = authorRepo.getOne(id);
        if (!author.getBooks().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Can not delete this author. Still have books belong to this author.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        } else {
            authorRepo.deleteById(id);
        }

        return "redirect:/authors";
    }
}
