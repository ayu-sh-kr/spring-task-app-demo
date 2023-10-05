package dev.archimedes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMvcApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(UserRepository repository){
//        return runner -> {
//            User user = new User();
//            user.setName("Ayush Jaiswal");
//            user.setPassword(new BCryptPasswordEncoder().encode("kumarj"));
//            user.setEmail("akjaiswal2003@gmail.com");
//            repository.save(user);
//        };
//    }

}
