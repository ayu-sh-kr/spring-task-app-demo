package dev.archimedes.controller;

import dev.archimedes.models.ToDoList;
import dev.archimedes.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ToDoController {

    @Autowired
    private ToDoRepository repository;

    @PostMapping("/todo/add")
    public String addTODO(ToDoList list){
        repository.save(list);
        return "todo-list";
    }

    @GetMapping("/todo/add")
    public String addMore(Model model){
        model.addAttribute("todolist", repository);
        model.addAttribute("todos", new ToDoList());
        return "AddToList";
    }

    @GetMapping("/todo")
    public String getTODOList(Model model){
        List<ToDoList> toDoLists = repository.findAll();
        model.addAttribute("lists", toDoLists);
        return "todo-list";
    }
}
