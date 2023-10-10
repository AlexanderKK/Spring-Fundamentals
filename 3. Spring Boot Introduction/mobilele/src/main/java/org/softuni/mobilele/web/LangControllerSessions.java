package org.softuni.mobilele.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LangControllerSessions {

    @GetMapping("/sessions")
    public String langSessions(
            HttpSession session,
            Model model) {

        var lang = session.getAttribute("lang");
        if(lang == null) {
            lang = "bg";
        }

        model.addAttribute("lang", lang);
        model.addAttribute("sess", session.getId());

        return "lang";
    }

    @PostMapping("/sessions")
    public String langSessions(@RequestParam("lang") String lang,
                              HttpSession session) {
        session.setAttribute("lang", lang);

        return "redirect:/sessions";
    }

}
