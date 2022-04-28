package com.lubian.grupparbete.controller;

import com.lubian.grupparbete.model.Todo;
import com.lubian.grupparbete.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value="json/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping(path= "/all")
    public List<Todo> getAll() {
        return todoService.getAllTodos();
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Todo> getTodoBydId(@PathVariable Long id) {
        try {
            Todo todo = todoService.findTodoById(id);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Todo> createNewTodo(@RequestBody Todo newTodo) {
        Todo todo = todoService.createTodo(newTodo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody Todo newTodo) {
        Todo todo = todoService.updateTodo(id, newTodo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable Long id) {
            Todo todo = todoService.deleteTodo(id);
            return new ResponseEntity<>(todo, HttpStatus.OK);
    }
}
