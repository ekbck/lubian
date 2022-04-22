package com.lubian.grupparbete.controller;

import com.lubian.grupparbete.model.Todo;
import com.lubian.grupparbete.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/myApp", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping(path="todo/json/{id}")
    public ResponseEntity<Todo> getTodoBydId(@PathVariable Long id) {
        try {
            Todo todo = todoService.findTodoById(id);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }
    }

}
