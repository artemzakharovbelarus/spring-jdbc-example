package by.artem_zakharov.user.dao.util;

import by.artem_zakharov.user.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getInt(1),
                             resultSet.getString(2));
        return user;
    }
}
