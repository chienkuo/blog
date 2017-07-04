package me.akuo.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Akuo on 2017/7/4.
 */
@Service
public class PostService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List get(){
        return jdbcTemplate.queryForList("SELECT * FROM posts");
    }
}
