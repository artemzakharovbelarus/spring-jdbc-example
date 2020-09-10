package by.artem_zakharov.user.dao.util.query;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateUser extends SqlUpdate {
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET username=:username WHERE idUser=:idUser";

    public UpdateUser(DataSource dataSource){
        super(dataSource, UPDATE_USER_BY_ID);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
        super.declareParameter(new SqlParameter("idUser", Types.INTEGER));
    }
}
