package by.artem_zakharov.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private int idUser;
    private String username;
    private String password;
    private String email;
    private int idRole;

    public User(String username){
        this.username = username;
    }

    public User(int idUser, String password){
        this.idUser = idUser;
        this.password = password;
    }

    public User(String username, String password, String email, int idRole){
        this.username = username;
        this.password = password;
        this.email = email;
        this.idRole = idRole;
    }
}
