package com.example.lesson_5_1.controllers;

import com.example.lesson_5_1.domain.SimpleClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class SimpleRestController {

    @GetMapping("/getStringRest")
    public String getStringRest(){
       return "!";
    }

    @GetMapping("/getSimpleClassRest")
    public SimpleClass getSimpleClassRest(){
        return new SimpleClass("Str");
    }

    @PostMapping("/postSimpleClassRest")
    public SimpleClass postSimpleClassRest(@RequestBody SimpleClass simpleClass){
        return simpleClass;
    }

    //Перенаправление без префикса - информация о перенаправлении уходит пользователю
    @GetMapping("/redirectWithUsingRedirectViewSimpleClassRest")
    public RedirectView redirectWithUsingRedirectViewSimpleClassRest(RedirectAttributes attributes){
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("getSimpleClassRest");
    }

    //Перенаправление на стороне клиента - используется в частности для переход на внешние сайты. и повторная отправка ответов.
    @GetMapping("/redirectWithRedirectPrefixSimpleClassRest")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/getSimpleClassRest", model);
    }

    //Быстрее. перенаправление на стороне сервера - пользователь не знает о редиректе
    @GetMapping("/forwardWithForwardPrefixSimpleClassRest")
    public ModelAndView redirectWithUsingForwardPrefix(ModelMap model) {
        model.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("forward:/getSimpleClassRest", model);
    }

}
