package com.lubian.grupparbete.service;

import com.lubian.grupparbete.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAllTodos() {return todoRepository.findAll();}

    public Todo findTodoById(Long id) {
        return todoRepository.findById(id).get();
    }

    public Todo createTodo(Todo todo) {
        Todo newTodo = new Todo();
        newTodo.setBody(todo.getBody());
        return todoRepository.save(newTodo);
    }

    public Todo updateTodo(Long id, Todo todo) {
        Optional<Todo> TodoOptional = todoRepository.findById(id);
        if (!TodoOptional.isPresent()) {
            throw new EntityNotFoundException(todo.getId().toString());
        }
        Todo updatedTodo = TodoOptional.get();
        updatedTodo.setBody(todo.getBody());
        return todoRepository.save(updatedTodo);
    }

    public Todo deleteTodo(Long id) {
        Optional<Todo> TodoOptional = todoRepository.findById(id);
        if (!TodoOptional.isPresent()) {
            throw new EntityNotFoundException(id.toString());
        }
        todoRepository.deleteById(id);
        return TodoOptional.get();
    }

}
