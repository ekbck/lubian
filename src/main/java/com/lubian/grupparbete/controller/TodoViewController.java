package com.lubian.grupparbete.controller;

import com.lubian.grupparbete.model.Todo;
import com.lubian.grupparbete.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoViewController {

    @Autowired
    TodoService todoService;

    @GetMapping("/start")
    public String getAllTodos(Model model) {
        List<Todo> todoList = todoService.getAllTodos();
        model.addAttribute("todoList", todoList);
        return "start";
    }

    @GetMapping("/{id}")
    public String getTodo(@PathVariable("id") Long id, Model model) {
        Todo todo = todoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "getTodo";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTodo() {
        return "addTodo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Todo todo) {
        todo.setBody(todo.getBody());
        todoService.createTodo(todo);
        return "redirect:/todo/start";
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateTodo(@Param("todo")@PathVariable("id") Long id, Todo todo) {
        todoService.updateTodo(id, todo);
        return "redirect:/todo/start";
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todo/start";
    }

}
