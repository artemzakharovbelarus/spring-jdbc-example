package by.artem_zakharov.user.service.api;

import by.artem_zakharov.user.domain.User;

import java.util.List;

public interface UserService {
    public User getUser(int idUser);
    public Integer getIdUserByUsername(String username);
    public String getUsernameByIdUser(int idUser);
    public void addUser(String username);
    public List<User> viewAllUsers();
    public User viewUserByUsername(String username);
    public List<User> viewAllUsersWithMappingQuery();
    public User viewUserByUsernameMappingQuery(String username);
    public void updateUser(User user);
    public void addUserQuery(User user);
}
