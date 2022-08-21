package siersetup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import siersetup.form.TopPageForm;

import java.time.LocalDate;

@Controller
public class TopController {

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("toDay", LocalDate.now().toString());
        model.addAttribute("modelValue", "Model Value!!");
        model.addAttribute("topPageForm", new TopPageForm());
        return "top";
    }
    @GetMapping("/top")
    public String top(Model model) {
        model.addAttribute("toDay", LocalDate.now().toString());
        model.addAttribute("modelValue", "Model Value!!");
        model.addAttribute("topPageForm", new TopPageForm());
        return "top";
    }

    @PostMapping("/top/sendMessage")
    public String topSendMessage(Model model, TopPageForm topPageForm) {
        model.addAttribute("sentMessage", topPageForm.getMessage());
        return "sentMessage";
    }
}
