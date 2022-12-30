package siersetup.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import siersetup.form.CustomLoginForm;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CustomLoginController {

    private final UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) Boolean error) {
        model.addAttribute("loginForm", new CustomLoginForm());
        model.addAttribute("error", error);
        log.info("error:{}",error);
        return "login";
    }

    @PostMapping("/login")
    public String login(CustomLoginForm loginForm) {

        log.info("accessed post login");
        return "top";
    }
}
