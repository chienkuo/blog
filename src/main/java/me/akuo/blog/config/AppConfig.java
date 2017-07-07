package me.akuo.blog.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Akuo on 2017/6/5.
 */
@Configuration
@ComponentScan(basePackages = "me.akuo.blog", excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, ControllerAdvice.class})})
@PropertySource(value = "classpath:app.properties")
public class AppConfig {

    @Value("${blog.db.name}")
    private String dbName;
    @Value("${blog.http.port}")
    private String port;

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource createDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:sqlite:" + this.getClass().getResource("/").getPath() + this.dbName);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(10);
        druidDataSource.setMaxWait(60000L);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000L);
        druidDataSource.setMinEvictableIdleTimeMillis(300000L);
        druidDataSource.setValidationQuery("SELECT 1");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        //druidDataSource.setFilters("stat");
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DruidDataSource druidDataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(druidDataSource);
        return jdbcTemplate;
    }

    @Bean
    public Parser mdParser(MutableDataSet options) {
        Parser parser = Parser.builder(options).build();
        return parser;
    }

    @Bean
    public MutableDataSet mdDataSet() {
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create(), StrikethroughExtension.create()));
        options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");
        return options;
    }

    @Bean
    public HtmlRenderer mdHtmlRedderer(MutableDataSet options) {
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        return renderer;
    }


}
