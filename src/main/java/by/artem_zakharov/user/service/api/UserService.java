package by.artem_zakharov.user.service.api;

import by.artem_zakharov.user.domain.User;

public interface UserService {
    public User getUser(int idUser);
    public Integer getIdUserByUsername(String username);
    public String getUsernameByIdUser(int idUser);
    public void addUser(String username);
}
