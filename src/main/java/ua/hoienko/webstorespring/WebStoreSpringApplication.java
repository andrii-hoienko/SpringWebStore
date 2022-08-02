package ua.hoienko.webstorespring;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.hoienko.webstorespring.repository.UserRepository;
import ua.hoienko.webstorespring.service.ProductService;
import ua.hoienko.webstorespring.service.UserService;

@SpringBootApplication
public class WebStoreSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebStoreSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner runApplication(UserService userService, ProductService productService) {
        return (args -> {
            userService.fillTableWithData();
            productService.fillTableWithData();
        });
    }
}
