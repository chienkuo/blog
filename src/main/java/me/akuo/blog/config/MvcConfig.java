package me.akuo.blog.config;

import me.akuo.blog.config.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "me.akuo.blog", useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, ControllerAdvice.class})})

public class MvcConfig extends WebMvcConfigurationSupport {


    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * 描述 : <注册试图处理器>. <br>
     * <p>
     * <使用方法说明>
     * </p>
     *
     * @return
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
//        freemarker.template.Configuration configuration = new freemarker.template.Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
//        configurer.setConfiguration(configuration);
        configurer.setTemplateLoaderPath("/WEB-INF/templates/");
        configurer.setDefaultEncoding("UTF-8");
        /*Properties properties = new Properties();
        properties.setProperty("template_update_delay","6000");
        configurer.setFreemarkerSettings(properties);*/
        return configurer;
    }

    @Bean
    public ViewResolver viewResolver() {
        org.springframework.web.servlet.view.freemarker.
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setCache(true);
        viewResolver.setExposeSpringMacroHelpers(true);
        return viewResolver;
    }

    /**
     * 描述 : <注册消息资源处理器>. <br>
     * <p>
     * <使用方法说明>
     * </p>
     *
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("config.messages.messages");

        return messageSource;
    }

    /**
     * 描述 : <注册servlet适配器>. <br>
     * <p>
     * <只需要在自定义的servlet上用@Controller("映射路径")标注即可>
     * </p>
     *
     * @return
     */
    @Bean
    public HandlerAdapter servletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter());
    }


    /**
     * 描述 : <RequestMappingHandlerMapping需要显示声明，否则不能注册自定义的拦截器>. <br>
     * <p>
     * <这个比较奇怪,理论上应该是不需要的>
     * </p>
     *
     * @return
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        return super.requestMappingHandlerMapping();
    }


    /**
     * 描述 : <添加拦截器>. <br>
     * <p>
     * <使用方法说明>
     * </p>
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor)
                .addPathPatterns("/**");
    }

    /**
     * 描述 : <HandlerMapping需要显示声明，否则不能注册资源访问处理器>. <br>
     * <p>
     * <这个比较奇怪,理论上应该是不需要的>
     * </p>
     *
     * @return
     */
    @Bean
    public HandlerMapping resourceHandlerMapping() {
        return super.resourceHandlerMapping();
    }

    /**
     * 描述 : <资源访问处理器>. <br>
     * <p>
     * <可以在jsp中使用/static/**的方式访问/WEB-INF/static/下的内容>
     * </p>
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("WEB-INF/static/");
    }

    /**
     * 描述 : <文件上传处理器>. <br>
     * <p>
     * <使用方法说明>
     * </p>
     *
     * @return
     */
   /* @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        logger.info("CommonsMultipartResolver");
        return new CommonsMultipartResolver();
    }*/
    /*@Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }*/

    /**
     * 描述 : <异常处理器>. <br>
     * <p>
     * <系统运行时遇到指定的异常将会跳转到指定的页面>
     * </p>
     *
     * @return
     */
   /* @Bean(name = "exceptionResolver")
    public CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        logger.info("CP_SimpleMappingExceptionResolver");
        CP_SimpleMappingExceptionResolver simpleMappingExceptionResolver = new CP_SimpleMappingExceptionResolver();
        simpleMappingExceptionResolver.setDefaultErrorView("common_error");
        simpleMappingExceptionResolver.setExceptionAttribute("exception");
        Properties properties = new Properties();
        properties.setProperty("java.lang.RuntimeException", "common_error");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }*/

    /**
     * 描述 : <RequestMappingHandlerAdapter需要显示声明，否则不能注册通用属性编辑器>. <br>
     * <p>
     * <这个比较奇怪,理论上应该是不需要的>
     * </p>
     *
     * @return
     */
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        return super.requestMappingHandlerAdapter();
    }

    /**
     * 描述 : <注册通用属性编辑器>. <br>
     * <p>
     * <这里只增加了字符串转日期和字符串两边去空格的处理>
     * </p>
     *
     * @return
     */
    @Override
    protected ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
        ConfigurableWebBindingInitializer initializer = super.getConfigurableWebBindingInitializer();
        /*CP_PropertyEditorRegistrar register = new CP_PropertyEditorRegistrar();
        register.setFormat("yyyy-MM-dd");
        initializer.setPropertyEditorRegistrar(register);*/
        return initializer;
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>(1);
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        configurer.mediaTypes(mediaTypes);
    }


}