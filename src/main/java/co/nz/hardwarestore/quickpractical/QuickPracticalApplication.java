package co.nz.hardwarestore.quickpractical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan({
  "co.nz.hardwarestore.quickpractical.controller",
  "co.nz.hardwarestore.quickpractical.repository",
  "co.nz.hardwarestore.quickpractical.config",
  "co.nz.hardwarestore.quickpractical.exception"
})
public class QuickPracticalApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuickPracticalApplication.class, args);
  }

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE", "OPTIONS", "PATCH")
            .allowedOrigins("http://localhost")
            .allowCredentials(true);
      }
    };
  }
}
