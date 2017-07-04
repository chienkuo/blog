package me.akuo.blog.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Order(1)
public class CommonInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext)
            throws ServletException {

        //Log4jConfigListener
       /* servletContext.setAttribute("log4jConfigLocation", "classpath:log4j2.xml");
		servletContext.addListener(Log4j2.class);*/
       // System.setProperty("rootPath", servletContext.getRealPath("/"));

        //OpenSessionInViewFilter
		/*OpenSessionInViewFilter hibernateSessionInViewFilter = new OpenSessionInViewFilter();
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(
				"hibernateFilter", hibernateSessionInViewFilter);
		filterRegistration.addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/");*/


        //DemoServlet
		/*DemoServlet demoServlet = new DemoServlet();
		ServletRegistration.Dynamic dynamic = servletContext.addServlet(
				"demoServlet", demoServlet);
		dynamic.setLoadOnStartup(2);
		dynamic.addMapping("/demo_servlet");*/

    }


}