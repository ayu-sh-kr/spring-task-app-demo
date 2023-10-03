package dev.archimedes.controller;

import dev.archimedes.dtos.SignupRequest;
import dev.archimedes.models.ToDoList;
import dev.archimedes.models.User;
import dev.archimedes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@Slf4j
public class UserController {
    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        String email = principal.getName();
        User user = repository.findByEmail(email);
        log.info(user.getId().toString());
        model.addAttribute("userId", user.getId());
        return "index";
    }

    @GetMapping("/who")
    public String getUser(Model model, Principal principal){
        model.addAttribute("user", principal.getName());
        return "index";
    }
    @GetMapping("/login")
    public String logInPage(){
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("request") LoginRequest request, Model model){
//        if(repository.existsByEmail(request.getUsername())){
//            User user = repository.findByEmail(request.getUsername());
//            List<ToDoList> lists = repository.findByEmail(request.getUsername()).getList();
//            if(user.getPassword().equals(request.getPassword())){
////                model.addAttribute("user", user);
////                model.addAttribute("list", lists);
//                return "redirect:/todo/" + user.getId();
//            }
//        }
//
//        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()){
//            model.addAttribute("message", "User Name Password Can't be empty");
//            return "login";
//        }
//        if(!repository.existsByEmail(request.getUsername())){
//            model.addAttribute("message", "user does not exist");
//            return "login";
//        }
//        return "login";
//    }

    @GetMapping("/user/create")
    public String createUser(Model model){
        SignupRequest request = new SignupRequest();
        model.addAttribute("request", request);
        return "create";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute("request") SignupRequest signupRequest, Model model){
        if(signupRequest.getUsername().isEmpty() || signupRequest.getEmail().isEmpty()){
            model.addAttribute("message", "details can't be empty");
            return "create";
        }
        if(repository.existsByEmail(signupRequest.getEmail())){
            model.addAttribute("message", "user already exist");
            return "redirect:/login";
        }
        System.out.println(signupRequest);
        User user = new User();
        user.setName(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        repository.save(user);
        User user1 = repository.findByEmail(signupRequest.getEmail());
        model.addAttribute("userId", user1.getId());
        model.addAttribute("todos", new ToDoList());
        model.addAttribute("message", "Create your first task!!");
        return "redirect:/login";
    }
}
