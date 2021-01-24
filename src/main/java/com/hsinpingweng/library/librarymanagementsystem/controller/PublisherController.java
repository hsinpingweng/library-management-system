package com.hsinpingweng.library.librarymanagementsystem.controller;

import com.hsinpingweng.library.librarymanagementsystem.entity.Publisher;
import com.hsinpingweng.library.librarymanagementsystem.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
