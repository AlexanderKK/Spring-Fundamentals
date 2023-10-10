package org.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @ModelAttribute
    public Student addStudent() {
        return new Student("1", "mike");
    }

    // Automatic binding of request params with ModelAttribute object Student

    @GetMapping("/test")
    public String test(/*@RequestParam("id") String id,
                       @RequestParam("name") String name,
                       Model model,*/
                       @ModelAttribute Student student) {
//        model.addAttribute("student", new Student(id, name));

        return "test";
    }

}

record Student(String id, String name) {}

