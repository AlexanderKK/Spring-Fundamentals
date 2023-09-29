package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.UserRegistrationDTO;
import org.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String index() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDTO userRegistrationDTO, Model model) {
        String registrationMsg = this.userService.register(userRegistrationDTO);

        if(registrationMsg.contains("success")) {
            return "redirect:/";
        }

        if(registrationMsg.endsWith("already registered")) {
            model.addAttribute("isUserPresent", true);
        } else if(registrationMsg.startsWith("Invalid")) {
            model.addAttribute("isUserInvalid", true);
        }

        model.addAttribute("message", registrationMsg);

        return "auth-register";
    }

}
