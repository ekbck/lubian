package com.lubian.grupparbete.service;

import com.lubian.grupparbete.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo findTodoById(Long id) {
        return todoRepository.findById(id).get();
    }

}
