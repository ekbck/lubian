package com.lubian.grupparbete.controller;

import com.lubian.grupparbete.model.Todo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myApp")
public class TodoController {

    @GetMapping("/todo")
    private Todo getTodo() {
        return new Todo(0, "Make group project");
    }

}
