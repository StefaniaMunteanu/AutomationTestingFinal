package database.queries;

import data.CreateAnAccountObjectData;
import data.EditAccountObjectData;

import java.sql.SQLException;
import java.sql.Statement;

public class UserInfoTable extends CommonTable{

    public UserInfoTable() throws SQLException {
    }

    public synchronized void insertTableObject (CreateAnAccountObjectData data) throws SQLException {
        Statement stm = dbConnection.getConnection().createStatement();
        String query = "insert into userInfo(firstName, lastName, email, password) " +
                "values('" + data.getFirstName() +
                "', '" + data.getLastName() +
                "', '" + data.getEmail()  +
                "', '" + data.getPassword() + "');";

        stm.execute(query);
    }

    public synchronized void updateEmailAfterEdit(CreateAnAccountObjectData data, EditAccountObjectData data2 ) throws SQLException{
        Statement stm = dbConnection.getConnection().createStatement();
        String query = "update userInfo set email = '" + data2.getEmail() +
                "' where email = '" + data.getEmail() + "';";

        stm.execute(query);
    }

}
