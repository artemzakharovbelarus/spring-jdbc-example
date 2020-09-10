package by.artem_zakharov.user.dao.util.query;

import by.artem_zakharov.user.domain.User;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SelectUserByUsername extends MappingSqlQuery<User> {
    private static final String SELECT_USER_BY_USERNAME = "SELECT idUser, username FROM users WHERE username = :username";

    public SelectUserByUsername(DataSource dataSource){
        super(dataSource, SELECT_USER_BY_USERNAME);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }

    @Override
    protected User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User(resultSet.getInt(1), resultSet.getString(2));

        return user;
    }
}
