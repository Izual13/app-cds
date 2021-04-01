package com.template.hello;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

public class HelloApplication {

    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();
        System.out.println("http://localhost:8080");
        Server server = new Server(8080);

        server.setHandler(getServletContextHandler());

        server.start();
        server.join();
        System.out.println(String.format("startup: %s ms", (System.nanoTime() - start) / 1_000_000));
    }


    private static ServletContextHandler getServletContextHandler() throws IOException {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SECURITY);
        contextHandler.setErrorHandler(null);

        // Spring
        WebApplicationContext webAppContext = getWebApplicationContext();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppContext);
        ServletHolder springServletHolder = new ServletHolder("mvc-dispatcher", dispatcherServlet);
        contextHandler.addServlet(springServletHolder, "/");

        return contextHandler;
    }

    private static WebApplicationContext getWebApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.template.hello");
        return context;
    }

    @RestController
    @RequestMapping("/")
    public static class HelloController {

        @GetMapping
        public String hello() {
            return "Ok!";
        }

    }

    @Configuration
    @EnableWebMvc
    @ComponentScan(basePackages = "com.template.hello")
    public class WebMvcConfig implements WebMvcConfigurer {

    }

}
