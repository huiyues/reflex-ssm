package com.heiye.reflex.controller;

import com.heiye.reflex.service.ReflexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: heiye
 * @Date: 2020/5/2
 * @Time: 11:04
 * Description: No Description
 */
@Controller
public class IndexController {

    @Autowired
    private ReflexService reflexService;

    @GetMapping("/index")
    public ModelAndView testReflex(int code, int nextCode) {
        String name = reflexService.reflex(code, nextCode);
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("name", name);
        return modelAndView;
    }
}
