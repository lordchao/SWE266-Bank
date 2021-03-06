package com.swe266.bank.controller;

import com.swe266.bank.service.BankI.LoginServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceI loginServiceI;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        boolean loginStatus = loginServiceI.login(username, password);
        if (loginStatus) return "main";
        else {
            model.addAttribute("msg", "login failed");
            return "error";
        }
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping(value="/success")
    public String success() {
        return "forward:main";
    }
}
