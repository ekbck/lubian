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

    public Iterable<Todo> getAllTodos() {
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
//    public void saveTodo(Todo todo) {
//        todoRepository.save(todo);
//    }



        public Todo updateTodo(Long id, Todo todo) {
        Optional<Todo> TodoOptional = todoRepository.findById(id);

        if (!TodoOptional.isPresent()) {
            throw new EntityNotFoundException(todo.getId().toString());
        }
        return todoRepository.save(todo);
    }

}
