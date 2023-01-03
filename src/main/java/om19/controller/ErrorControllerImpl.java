package om19.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@Slf4j
public class ErrorControllerImpl implements org.springframework.boot.web.servlet.error.ErrorController {
    @GetMapping("/error")
    public String error(HttpServletRequest req, Model model) {

        var attributeNames = toAttributeNames(req.getAttributeNames());
        log.error("get error page,{}", attributeNames);

        model.addAttribute("error", req);
        return "error";
    }

    private List<String> toAttributeNames(Enumeration<String> attributeNames) {
        var list = new ArrayList<String>();
        attributeNames.asIterator().forEachRemaining(list::add);
        return list;
    }

    @PostMapping("/error")
    public String errorPost(HttpServletRequest req, Model model) {
        var attributeNames = toAttributeNames(req.getAttributeNames());
        log.error("post error page,{}", attributeNames);
        /*
          javax.servlet.error.message,
          javax.servlet.error.request_uri,
          javax.servlet.error.servlet_name,
          javax.servlet.error.status_code,
         */
        model.addAttribute("message", req.getAttribute("javax.servlet.error.message"));
        model.addAttribute("request_uri", req.getAttribute("javax.servlet.error.request_uri"));
        model.addAttribute("servlet_name", req.getAttribute("javax.servlet.error.servlet_name"));
        model.addAttribute("status_code", req.getAttribute("javax.servlet.error.status_code"));
        return "error";
    }

}
