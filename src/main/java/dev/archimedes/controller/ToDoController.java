package dev.archimedes.controller;

import dev.archimedes.models.ToDoList;
import dev.archimedes.models.User;
import dev.archimedes.repository.ToDoRepository;
import dev.archimedes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class ToDoController {

    @Autowired
    private ToDoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/todo/add")
    public String addTODO(ToDoList list, Principal principal){
        User user = userRepository.findByEmail(principal.getName());
//        User user = userRepository.findById(userId).get();
        list.setUser(user);
        repository.save(list);
        return "redirect:/todo";
    }

    @GetMapping("/todo/add")
    public String addMore(Model model){
        model.addAttribute("todos", new ToDoList());
        return "AddToList";
    }

    @GetMapping("/todo")
    public String getTODOList(Model model, Principal principal){
        List<ToDoList> lists = userRepository.findByEmail(principal.getName()).getList();
        model.addAttribute("lists", lists);
        return "todo-list";
    }

    @GetMapping("/todo/{listId}/delete")
    public String deleteTask(@PathVariable("listId") Long listId){
        System.out.println(listId);
        repository.deleteById(listId);
        return "redirect:/todo";
    }

    @GetMapping("/todo/{listId}/edit")
    public String editTask(@PathVariable("listId") Long listId, Model model){
        ToDoList list = repository.findById(listId).get();
        model.addAttribute("list", list);
        return "modify-task";
    }

    @PostMapping("/todo/{listId}/edit")
    public String addModifiedTask(@PathVariable("listId")Long listId,
                                  @ModelAttribute("list") ToDoList list, Principal principal){
        log.info("ListId: " + listId + " UserId: " + principal.getName());
        list.setUser(userRepository.findByEmail(principal.getName()));
        repository.save(list);
        return "redirect:/todo";
    }
}
