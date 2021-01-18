package org.evrim.spring.exam.mvc.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("flash")
public class FlashController {


    @GetMapping("action")
    public String action(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("firstname", "evrim");
        redirectAttributes.addFlashAttribute("lastname", "ozcelik");
        redirectAttributes.addFlashAttribute("city", "istanbul");

        return "redirect:/flash/list";
    }


    @GetMapping("list")
    public String list(String firstname, @ModelAttribute("lastname") String lastname, @ModelAttribute("city") String city, Model model) {
        model.addAttribute("firstname", firstname);

        return "list-attributes";
    }


}
