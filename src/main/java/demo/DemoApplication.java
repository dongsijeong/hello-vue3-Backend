/*
 * マインクラス
 */
package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.oas.annotations.EnableOpenApi;


@SpringBootApplication
@EnableOpenApi
@EnableAsync
public class DemoApplication extends SpringBootServletInitializer {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
            SpringApplication.run(DemoApplication.class, args);
            System.out.println("program is running...");
    }

    /**
     * configure
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
