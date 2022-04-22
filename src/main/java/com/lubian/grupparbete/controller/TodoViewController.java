package com.lubian.grupparbete.controller;

import com.lubian.grupparbete.service.TodoRepository;
import com.lubian.grupparbete.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoViewController {

    @Autowired
    TodoRepository todoService;

    @GetMapping("/getTodo")
    private String getTodoPage(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "todoPage";
    }

}
