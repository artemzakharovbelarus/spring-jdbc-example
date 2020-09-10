package by.artem_zakharov.user.dao.util.query;

import by.artem_zakharov.user.domain.User;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectAllUsers extends MappingSqlQuery<User> {

    private static final String SELECT_ALL_USERS = "SELECT idUser, username FROM users";

    public SelectAllUsers(DataSource dataSource){
        super(dataSource, SELECT_ALL_USERS);
    }

    @Override
    protected User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getInt(1), resultSet.getString(2));

        return user;
    }
}
