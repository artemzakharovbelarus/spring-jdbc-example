package by.artem_zakharov.user.runner;

import by.artem_zakharov.user.config.ApplicationConfig;
import by.artem_zakharov.user.domain.User;
import by.artem_zakharov.user.service.api.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.getUser(1);
        System.out.println("id user: " + user.getIdUser() + " username: " + user.getUsername());
        context.close();
    }
}
