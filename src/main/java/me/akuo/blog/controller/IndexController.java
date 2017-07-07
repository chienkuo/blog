package me.akuo.blog.controller;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import me.akuo.blog.service.PostService;
import me.akuo.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Akuo on 2017/7/4.
 */
@Controller
public class IndexController {

    @Autowired
    private TagService tagService;

    @Autowired
    private PostService postService;

    @Autowired
    private Parser parser;
    @Autowired
    private HtmlRenderer htmlRenderer;

    @RequestMapping("/")
    public String index(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("tags", tagService.list());
        List postList = postService.list();
        for(Object obj : postList){
            Map map = (Map)obj;
            String md = (String)(map.get("content"));
            map.put("content", htmlRenderer.render(parser.parse(md)));
        }

        model.addAttribute("posts", postList);
        return "index";
    }
}
