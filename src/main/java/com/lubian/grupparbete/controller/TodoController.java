package com.lubian.grupparbete.controller;

import com.lubian.grupparbete.model.Todo;
import com.lubian.grupparbete.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping(path= "todo/json/all")
    public List<Todo> getAll() {
        return todoService.getAllTodos();
    }

    @GetMapping(path="todo/json/{id}")
    public ResponseEntity<Todo> getTodoBydId(@PathVariable Long id) {
        try {
            Todo todo = todoService.findTodoById(id);
            return new ResponseEntity<Todo>(todo, HttpStatus.OK);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<Todo>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping(path = "todo/json/create")
    public ResponseEntity<?> register(@Param("body") String body) {
        try {
            Todo newTodo = new Todo();
            newTodo.setBody(body);
            todoService.saveTodo(newTodo);
            String responseMessage = "New todo " + newTodo.getId() + " \"" + newTodo.getBody() + "\" created";
            return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
        } catch(NoSuchElementException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // TODO - FIX THIS LUDDE
    @PostMapping(path = "/todo/json/create")
    public ResponseEntity<Todo> createNewTodo(@RequestBody Todo newTodo) {
        Todo todo = todoService.createTodo(newTodo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }
    

    // TODO - FÅ SKITEN ATT FUNKA
//   @PutMapping(path = "/update/{id}")
//   public ResponseEntity<?> update(@Param("newBody") @PathVariable("id") Long id, String newBody) {
//       try {
//           Todo existTodo = todoService.findTodoById(id);
//           existTodo.setBody(newBody);
//           todoService.saveTodo(existTodo);
//           String responseMessage = "Updated todo for " + existTodo.getId() + " to " + existTodo.getBody();
//           return new ResponseEntity<>(responseMessage, HttpStatus.OK);
//       } catch(NoSuchElementException error) {
//           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//       }
//   }

    @PutMapping(path = "/todo/json/{id}")
    @PreAuthorize("hasAuthority('todo:write')")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody Todo newTodo) {
        Todo todo = todoService.updateTodo(id, newTodo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/todo/json/{id}")
    @PreAuthorize("hasAuthority('todo:write')")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable Long id) {
        Todo todo = todoService.deleteTodoBetter(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }


//    @DeleteMapping(path = "todo/json/delete/{id}")
//    @PreAuthorize("hasAuthority('todo:write')")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        try {
//            Todo existTodo = todoService.findTodoById(id);
//            todoService.deleteTodo(existTodo);
//            String responseMessage = existTodo.getId() + " " + existTodo.getBody() + " has been deleted";
//            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
//        } catch(NoSuchElementException error) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

}
