package com.project.task.manager.services;

import com.project.task.manager.models.Todo;
import com.project.task.manager.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> finAllSorted(){
        return todoRepository.findAll(Sort.by( "deadLine") );
    }

    public void save( Todo todo ){
        todo.setCreatedAt( LocalDateTime.now() );
        todoRepository.save( todo );
    }

    public Optional<Todo> findById(Long id) {
        return todoRepository.findById( id );
    }

    public void delete( Todo todo ){
        todoRepository.delete( todo );
    }
    public void finishedTask( Todo todo ){
        todo.setFinishedAt( LocalDate.now() );
        todoRepository.save( todo );
    }
}
