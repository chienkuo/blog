package me.akuo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Akuo on 2017/7/4.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {

        return "hi";
    }
}
