package gift.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.stereotype.Component;

@Component
public class DBConnection {

    public static Connection getConnection() throws Exception {
        var url = "jdbc:h2:mem:product";
        var user = "sa";
        var password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
