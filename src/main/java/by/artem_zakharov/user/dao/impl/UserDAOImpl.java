package by.artem_zakharov.user.dao.impl;

import by.artem_zakharov.user.dao.api.UserDAO;
import by.artem_zakharov.user.dao.util.query.InsertUser;
import by.artem_zakharov.user.dao.util.query.SelectAllUsers;
import by.artem_zakharov.user.dao.util.query.SelectUserByUsername;
import by.artem_zakharov.user.dao.util.query.UpdateUser;
import by.artem_zakharov.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDAOImpl")
@Primary
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    private final SelectAllUsers selectAllUsers;
    private final SelectUserByUsername selectUserByUsername;
    private final UpdateUser updateUser;
    private final InsertUser insertUser;

    @Autowired
    public UserDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        selectAllUsers = new SelectAllUsers(dataSource);
        selectUserByUsername = new SelectUserByUsername(dataSource);
        updateUser = new UpdateUser(dataSource);
        insertUser = new InsertUser(dataSource);
    }

    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(resultSet.getInt(1),
                    resultSet.getString(2));
            return user;
        }
    }

    @Override
    public void updateUser(User user) {
        Map<String, Object> parameters = new HashMap<String, Object>(){{
            put("username", user.getUsername());
            put("idUser", user.getIdUser());
        }};

        updateUser.updateByNamedParam(parameters);
    }

    @Override
    public void insertUser(User user) {
        Map<String, Object> parameters = new HashMap<String, Object>(){{
           put("username", user.getUsername());
           put("password", user.getPassword());
           put("email", user.getEmail());
           put("idRole", user.getIdRole());
        }};

        insertUser.updateByNamedParam(parameters);
    }

    @Override
    public User getUserByUsernameMappingQuery(String username) {
        Map<String, Object> parameters = new HashMap<String, Object>(){{
           put("username", username);
        }};

        return selectUserByUsername.executeByNamedParam(parameters).get(0);
    }

    @Override
    public List<User> getAllUsersWithMappingQuery() {
        return selectAllUsers.execute();
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT idUser, username FROM users WHERE username = :username";

        Map<String, Object> namedParameters = new HashMap<String, Object>(){{
            put("username", username);
        }};

        List<User> users = new ArrayList<>();
        users = namedJdbcTemplate.query(sql, namedParameters, (resultSet, rowNum) -> {
            User result = new User(resultSet.getInt(1),
                    resultSet.getString(2));
            return result;
        });

        return users.get(0);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT idUser, username FROM users";

        List<User> users = new ArrayList<>();

        users = namedJdbcTemplate.query(sql, (resultSet, rowNum) -> {
            User user = new User(resultSet.getInt(1),
                                 resultSet.getString(2));
            return user;
        });

        return users;
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
