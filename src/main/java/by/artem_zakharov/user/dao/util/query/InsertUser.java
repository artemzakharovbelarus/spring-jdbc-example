package by.artem_zakharov.user.dao.util.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertUser extends SqlUpdate {

    private static final String INSERT_INTO_USER = "INSERT INTO users (username, password, email, idRole) VALUES (:username, :password, :email, :idRole)";

    public InsertUser(DataSource dataSource){
        super(dataSource, INSERT_INTO_USER);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
        super.declareParameter(new SqlParameter("password", Types.VARCHAR));
        super.declareParameter(new SqlParameter("email", Types.VARCHAR));
        super.declareParameter(new SqlParameter("idRole", Types.INTEGER));
    }
}
