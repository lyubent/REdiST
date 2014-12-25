package uk.co.lyubentodorov.redist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class RESTApplication
{
    // Using Spring's ability to embed Tomcat instead of packaging the app
    // as a war to be deployed to a web-server. Much simpler for building
    // a standalone web service.
    public static void main(String[] args)
    {
        SpringApplication.run(RESTApplication.class, args);
    }
}
