package com.lubian.grupparbete.controller;

import com.lubian.grupparbete.model.Todo;
import com.lubian.grupparbete.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    @PutMapping(path = "/update/{id}")
//    public ResponseEntity<?> update(@Param("newBody") @PathVariable("id") Long id, String newBody) {
//        try {
//            Todo existTodo = todoService.findTodoById(id);
//            existTodo.setBody(newBody);
//            todoService.saveTodo(existTodo);
//            String responseMessage = "Updated todo for " + existTodo.getId() + " to " + existTodo.getBody();
//            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
//        } catch(NoSuchElementException error) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }




        @PutMapping("/todo/json/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody Todo Skittodo) {

            Todo todo = todoService.updateTodo(id, Skittodo);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);

    }

}
