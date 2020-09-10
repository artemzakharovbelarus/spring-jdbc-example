package by.artem_zakharov.user.runner;

import by.artem_zakharov.user.config.ApplicationConfig;
import by.artem_zakharov.user.domain.User;
import by.artem_zakharov.user.service.api.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ApplicationRunner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.getUser(2);
        System.out.println("id user: " + user.getIdUser() + " username: " + user.getUsername());

        String username = userService.getUsernameByIdUser(2);
        System.out.println("username: " + username);
        List<User> users = userService.viewAllUsers();
        for (User user1: users) {
            System.out.println(user1 + "\n");
        }

        //User user3 = new User(1, "VANIA123QWE");
        //userService.updateUser(user3);
        System.out.println(userService.getUsernameByIdUser(1));

        User user4 = new User("Nastya2234", "qwe123qwe", "qeqwe", 1);
        userService.addUserQuery(user4);

        List<User> users2 = userService.viewAllUsersWithMappingQuery();

        for (User user1: users2) {
            System.out.println(user1 + "\n");
        }
        //userService.addUser("QWE123qw");
        //System.out.println(userService.getUsernameByIdUser(5));

//        int idUser = userService.getIdUserByUsername("ArtemZakharovBY");
//        System.out.println("id user: " + idUser);

        context.close();
    }
}
