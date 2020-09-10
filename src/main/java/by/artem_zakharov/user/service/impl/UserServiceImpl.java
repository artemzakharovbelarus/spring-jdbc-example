package by.artem_zakharov.user.service.impl;

import by.artem_zakharov.user.dao.api.UserDAO;
import by.artem_zakharov.user.domain.User;
import by.artem_zakharov.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public List<User> viewAllUsersWithMappingQuery() {
        return userDAO.getAllUsersWithMappingQuery();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void addUserQuery(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public User viewUserByUsernameMappingQuery(String username) {
        return userDAO.getUserByUsernameMappingQuery(username);
    }

    @Override
    public User getUser(int idUser) {
        User user = new User();
        user = userDAO.getUser(idUser);

        return user;
    }

    @Override
    public Integer getIdUserByUsername(String username) {
        int i = 0;
        i = userDAO.getIdUserByUsername(username);

        return i;
    }

    @Override
    public void addUser(String username) {
        userDAO.addUser(username);
    }

    @Override
    public String getUsernameByIdUser(int idUser) {
        String username = "";
        username = userDAO.getUsernameByIdUser(idUser);

        return username;
    }

    @Override
    public List<User> viewAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User viewUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
