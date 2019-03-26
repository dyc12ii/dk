package com.dyc12ii.dk.controller;

import com.dyc12ii.dk.beans.DKSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * author: dingyanchao
 * date: 2019/3/4 20:26
 * desc:
 */
@RestController
public class DKController {

    @PostMapping("dk")
    public String dk(@RequestParam("cookie") String cookie) {
        DKSender dkSender = new DKSender();
        String dk = dkSender.dk(cookie);
        return dk;
    }

    @GetMapping({"/", "/home"})
    public ModelAndView home() {
        return new ModelAndView("index.html");
    }
}
