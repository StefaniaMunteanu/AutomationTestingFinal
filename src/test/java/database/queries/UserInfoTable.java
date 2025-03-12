package database.queries;

import HelperClasses.CreateAnAccount;
import ObjectData.CreateAnAccountObjectData;
import ObjectData.EditAccountObjectData;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

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
