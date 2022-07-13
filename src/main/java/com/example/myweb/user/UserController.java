package com.example.myweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers" ,listUsers);
        return "users";
    }
    @GetMapping("/users/new")
    public String addForm(Model model){
    model.addAttribute("user" , new User());
    model.addAttribute("pageT","add");
        return "user_form";
    }
    @PostMapping("/users/save")
    public String saveUser(User user , RedirectAttributes ra){
    service.save(user);
    ra.addFlashAttribute("message", "The use has ok");
    return "redirect:/users";
    }
    @GetMapping("/users/edit/{id}")
    public String showEdit(@PathVariable("id") Integer id , Model model ,  RedirectAttributes ra){

        try {
           User user = service.get(id);
           model.addAttribute("user",user);
           model.addAttribute("pageT","Edit ok"+id+"");

            return "user_form";
        } catch (UserNotFound e) {
           ra.addFlashAttribute("message" , "aaa");
            return "redirect:/users";
        }
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id ,RedirectAttributes ra){
        try {
            service.delete(id);

        } catch (UserNotFound e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return  "redirect:/users";
    }
}
