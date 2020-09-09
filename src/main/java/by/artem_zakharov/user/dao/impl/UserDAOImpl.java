package by.artem_zakharov.user.dao.impl;

import by.artem_zakharov.user.dao.api.UserDAO;
import by.artem_zakharov.user.dao.util.UserRowMapper;
import by.artem_zakharov.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository("userDAOImpl")
@Primary
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public UserDAOImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void addUser(String username) {
        String sql = "INSERT INTO users (username) VALUES (?)";
        jdbcTemplate.update(sql, username);
    }

    @Override
    public User getUser(int idUser) {
        String sql = "SELECT idUser, username FROM users WHERE idUser = ?";

        User user = new User();
        user = jdbcTemplate.queryForObject(sql, new Object[]{idUser}, new UserRowMapper());

        return user;
    }

    @Override
    public String getUsernameByIdUser(int idUser) {
        String sql = "SELECT username FROM users WHERE idUser = :idUser";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("idUser", idUser);

        return namedJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public Integer getIdUserByUsername(String username) {
        String sql = "SELECT idUser FROM users WHERE idUser = :username";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("username", username);

        return namedJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }
}
