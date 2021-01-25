package com.hsinpingweng.library.librarymanagementsystem.controller;


import com.hsinpingweng.library.librarymanagementsystem.entity.Author;
import com.hsinpingweng.library.librarymanagementsystem.entity.Book;
import com.hsinpingweng.library.librarymanagementsystem.entity.Category;
import com.hsinpingweng.library.librarymanagementsystem.entity.Publisher;
import com.hsinpingweng.library.librarymanagementsystem.repository.AuthorRepository;
import com.hsinpingweng.library.librarymanagementsystem.repository.BookRepository;
import com.hsinpingweng.library.librarymanagementsystem.repository.CategoryRepository;
import com.hsinpingweng.library.librarymanagementsystem.repository.PublisherRepository;
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
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private PublisherRepository publisherRepo;

    @GetMapping
    public String index(Model model){
        List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);

        return "book/index";
    }

    @GetMapping("/add")
    public String add(Book book, Model model) {
        List<Category> categories = categoryRepo.findAll();
        List<Author> authors = authorRepo.findAll();
        List<Publisher> publishers = publisherRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);

        return "book/add";
    }


    @PostMapping("/add")
    public String add(@Valid Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) return "book/add";

        Book bookExist = bookRepo.findByIsbn(book.getIsbn());

        if (bookExist != null) {

            redirectAttributes.addFlashAttribute("message", "ISBN exists!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            // add value in session
            redirectAttributes.addFlashAttribute("bookInfo", book);
        } else {
            redirectAttributes.addFlashAttribute("message", "Book added");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            bookRepo.save(book);
        }

        return "redirect:/books/add";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Book book = bookRepo.getOne(id);
        model.addAttribute("book", book);

        List<Category> categories = categoryRepo.findAll();
        List<Author> authors = authorRepo.findAll();
        List<Publisher> publishers = publisherRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("publishers", publishers);

        return "book/edit";
    }



    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @Valid Book book, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("bookName", book.getName());
            return "redirect:/books/edit";
        }

        Book bookExist = bookRepo.findByIsbn(book.getIsbn());
        if (bookExist == null || (bookExist != null && bookExist.getId() == id)) {

            redirectAttributes.addFlashAttribute("message", "Book edited");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            book.setId(id);
            bookRepo.save(book);

        } else {
            redirectAttributes.addFlashAttribute("message", "Book exists!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            // add value in session
            redirectAttributes.addFlashAttribute("bookInfo", book);
        }
        return "redirect:/books/edit/" + id;
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){

        //TODO - Check foreign key constraint violation before deletion
        bookRepo.deleteById(id);

        return "redirect:/books";
    }
}
