package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.UserLoginDTO;
import org.softuni.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String index() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(Model model, UserLoginDTO userLoginDTO) {
        String loginMsg = this.userService.login(userLoginDTO);

        if(loginMsg.contains("error")) {
            model.addAttribute("isUserLogged", false);
            model.addAttribute("message", loginMsg);

            return "auth-login";
        }

        return "redirect:/";
    }

}
