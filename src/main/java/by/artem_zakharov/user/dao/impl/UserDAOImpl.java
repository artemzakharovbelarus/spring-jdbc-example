package by.artem_zakharov.user.dao.impl;

import by.artem_zakharov.user.dao.api.UserDAO;
import by.artem_zakharov.user.dao.util.UserRowMapper;
import by.artem_zakharov.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("userDAOImpl")
@Primary
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(int idUser) {
        String sql = "SELECT idUser, username FROM users WHERE idUser = ?";

        User user = new User();
        user = jdbcTemplate.queryForObject(sql, new Object[]{idUser}, new UserRowMapper());

        return user;
    }
}
