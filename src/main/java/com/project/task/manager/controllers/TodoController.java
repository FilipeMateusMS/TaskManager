package com.project.task.manager.controllers;

import com.project.task.manager.models.Todo;
import com.project.task.manager.repositories.TodoRepository;
import com.project.task.manager.services.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import java.util.Optional;
import java.util.OptionalInt;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @GetMapping( "/" )
    public ModelAndView home() {
        ModelAndView page = new ModelAndView( "todo/list" );
        page.addObject( "todos", service.finAllSorted() );
        return page;
    }
    @GetMapping( "/create" )
    public ModelAndView create(){
        ModelAndView pageCreate = new ModelAndView( "todo/form" );
        pageCreate.addObject( "todo", new Todo() );
        return pageCreate;
    }

    @PostMapping( "/create" )
    public String create(@Valid Todo todo, BindingResult result ) // O spring irá injetar o objeto do form
    {
        if( result.hasErrors() ) return "todo/form";

        service.save( todo );
        return "redirect:/";
    }

    @GetMapping( "/edit/{id}" )
    public ModelAndView edit(@PathVariable Long id ){
        Optional<Todo> todo = service.findById( id );
        if( todo.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND );
        }
        ModelAndView lpageEdit = new ModelAndView("todo/form" );
        lpageEdit.addObject("todo", todo.get() );
        return lpageEdit;
    }

    @PostMapping( "/edit/{id}" )
    public String edit( @Valid Todo todo, BindingResult result ){
        if( result.hasErrors() ) return "todo/form";

        service.save( todo );
        return "redirect:/";
    }

    @GetMapping( "/delete/{id}" )
    public ModelAndView delete( @PathVariable Long id )
    {
        Optional<Todo> todo = service.findById( id );
        if( todo.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND );
        }
        ModelAndView lPageDelete = new ModelAndView("todo/delete" );
        lPageDelete.addObject("todo", todo.get() );
        return lPageDelete;
    }

    @PostMapping( "/delete/{id}" )
    public String confirmDelete( Todo todo )
    {
        service.delete( todo );
        return "redirect:/";
    }

    @PostMapping( "/finish/{id}" )
    public String finish( @PathVariable Long id ){
        Optional<Todo> todo = service.findById( id );
        if( todo.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND );
        }
        service.finishedTask( todo.get() );
        return "redirect:/";
    }
}
