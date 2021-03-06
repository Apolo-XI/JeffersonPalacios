package co.com.microservices.persona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class MicroservicePersona {

    public static void main(String[] args) throws Exception {
        new SpringApplication(MicroservicePersona.class).run(args);
    }

}
