package com.hsinpingweng.library.librarymanagementsystem.controller;

import com.hsinpingweng.library.librarymanagementsystem.entity.Category;
import com.hsinpingweng.library.librarymanagementsystem.repository.CategoryRepository;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping
    public String index(Model model) {

        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);

        return "category/index";
    }


    @GetMapping("/add")
    public String add(Category category) {
        return "category/add";
    }


    @PostMapping("/add")
    public String add(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) return "category/add";

        Category categoryExist = categoryRepo.findByName(category.getName());

        if (categoryExist != null) {

            redirectAttributes.addFlashAttribute("message", "Category exists!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            // add value in session
            redirectAttributes.addFlashAttribute("categoryInfo", category);
        } else {
            redirectAttributes.addFlashAttribute("message", "Category added");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

            categoryRepo.save(category);
        }

        return "redirect:/categories/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Category category = categoryRepo.getOne(id);
        model.addAttribute("category", category);

        return "category/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable int id, @Valid Category category, RedirectAttributes redirectAttributes, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("categoryName", category.getName());
            return "redirect:/categories/edit";
        }

        Category categoryExist = categoryRepo.findByName(category.getName());
        if (categoryExist == null) {

            redirectAttributes.addFlashAttribute("message", "Category edited");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            category.setId(id);
            categoryRepo.save(category);

        } else {
            redirectAttributes.addFlashAttribute("message", "Category exists!");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            // add value in session
            redirectAttributes.addFlashAttribute("categoryInfo", category);
        }
        return "redirect:/categories/edit/" + id;
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes){

        Category category = categoryRepo.getOne(id);
        if (!category.getBooks().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Can not delete this category. Still have books belong to this category.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        } else {
            categoryRepo.deleteById(id);
        }

        return "redirect:/categories";
    }
}
