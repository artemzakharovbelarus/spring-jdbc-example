package by.artem_zakharov.user.service.impl;

import by.artem_zakharov.user.dao.api.UserDAO;
import by.artem_zakharov.user.domain.User;
import by.artem_zakharov.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
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
}
