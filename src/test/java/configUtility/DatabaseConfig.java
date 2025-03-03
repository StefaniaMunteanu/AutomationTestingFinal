package configUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatabaseConfig {

    private String port;
    private String  database;
    private String username;
    private String password;

}
