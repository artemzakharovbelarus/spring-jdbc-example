package by.artem_zakharov.user.dao.api;

import by.artem_zakharov.user.domain.User;

import java.util.List;

public interface UserDAO {
    public User getUser(int idUser);
    public String getUsernameByIdUser(int idUser);
    public User getUserByUsername(String username);
    public Integer getIdUserByUsername(String username);
    public void addUser(String username);
    public List<User> getAllUsers();
    public List<User> getAllUsersWithMappingQuery();
    public User getUserByUsernameMappingQuery(String username);
    public void updateUser(User user);
    public void insertUser(User user);
}
