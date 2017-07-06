package me.akuo.blog;

import org.apache.catalina.startup.Tomcat;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Akuo on 2017/7/4.
 */

public class App {
    public static void main(String[] args) throws Exception {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
            System.out.println(url.getFile());
        }

        String contextPath = "";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        String port = System.getProperty("blog.server.port");
        tomcat.setPort(port == null ? 8080 : Integer.valueOf(port));
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        tomcat.getServer().await();
    }
}
