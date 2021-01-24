package com.hsinpingweng.library.librarymanagementsystem.controller;

import com.hsinpingweng.library.librarymanagementsystem.entity.Publisher;
import com.hsinpingweng.library.librarymanagementsystem.repository.PublisherRepository;
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
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepo;

    @GetMapping
    public String index(Model model){
        List<Publisher> publishers = publisherRepo.findAll();
        model.addAttribute("publishers", publishers);

        return "publisher/index";
    }

    @GetMapping("/add")
    public String add(Publisher publisher){

        return "publisher/add";
    }

    @PostMapping("/add")
    public String add(Publisher publisher, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) return "publisher/add";

        Publisher publisherExist = publisherRepo.findByName(publisher.getName());
        if (publisherExist != null) {

            redirectAttributes.addFlashAttribute("message", "Publisher exists!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            // add value in session
            redirectAttributes.addFlashAttribute("publisherInfo", publisher);

        } else {
            redirectAttributes.addFlashAttribute("message", "publisher added");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            publisherRepo.save(publisher);
        }

        return "redirect:/publishers/add";
    }

}
