package by.artem_zakharov.user.runner;

import by.artem_zakharov.user.config.ApplicationConfig;
import by.artem_zakharov.user.domain.User;
import by.artem_zakharov.user.service.api.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.getUser(2);
        System.out.println("id user: " + user.getIdUser() + " username: " + user.getUsername());

        String username = userService.getUsernameByIdUser(2);
        System.out.println("username: " + username);

        //userService.addUser("QWE123qw");
        //System.out.println(userService.getUsernameByIdUser(5));

//        int idUser = userService.getIdUserByUsername("ArtemZakharovBY");
//        System.out.println("id user: " + idUser);

        context.close();
    }
}
