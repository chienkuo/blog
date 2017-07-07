package me.akuo.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Akuo on 2017/7/4.
 */
@Service
public class TagService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List list() {
        return jdbcTemplate.queryForList("SELECT id,tagName,tagEnName FROM tags");
    }
}
