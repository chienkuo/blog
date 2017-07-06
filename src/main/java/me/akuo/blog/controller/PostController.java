package me.akuo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Akuo on 2017/7/4.
 */
@Controller
public class PostController {

    @RequestMapping("posts")
    public Model index() {

        return null;
    }
}
