package by.artem_zakharov.user.dao.api;

import by.artem_zakharov.user.domain.User;

public interface UserDAO {
    public User getUser(int idUser);
    public String getUsernameByIdUser(int idUser);
    public Integer getIdUserByUsername(String username);
    public void addUser(String username);
}
