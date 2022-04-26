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

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo findTodoById(Long id) {
        return todoRepository.findById(id).get();
    }

//    public void changeTodoById(Long id, String newBody){
//        Optional<Todo> TodoToChange = todoRepository.findById(id);
//        if(TodoToChange.isPresent()){
//            Todo todo = TodoToChange.get();
//            todo.setBody(newBody);
//            todoRepository.save(todo);
//        }else{
//            System.out.println("not found");
//        }
//    }
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public Todo createTodo(Todo newTodo) {
        return todoRepository.save(newTodo);
    }


    public Todo updateTodo(Long id, Todo todo) {
        Optional<Todo> TodoOptional = todoRepository.findById(id);
        if (!TodoOptional.isPresent()) {
            throw new EntityNotFoundException(todo.getId().toString());
        }
        Todo updatedTodo = TodoOptional.get();
        updatedTodo.setId(id);
        return todoRepository.save(updatedTodo);
    }

    public void deleteTodo(Todo todo) {
        todoRepository.delete(todo);
    }

    public Todo deleteTodoBetter(Long id) {
        Optional<Todo> TodoOptional = todoRepository.findById(id);
        if (!TodoOptional.isPresent()) {
            throw new EntityNotFoundException(id.toString());
        }
        todoRepository.deleteById(id);
        return TodoOptional.get();
    }

}
