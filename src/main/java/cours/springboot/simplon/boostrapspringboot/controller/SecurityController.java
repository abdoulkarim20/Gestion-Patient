package cours.springboot.simplon.boostrapspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/403")
    public String errorPage(){
        return "403";
    }
}
