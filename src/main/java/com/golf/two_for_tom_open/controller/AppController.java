package com.golf.two_for_tom_open.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AppController {

    @GetMapping({"/"})
    public String loadUi() {
        log.info("Loading UI");
        return "forward:/index.html";
    }
}
