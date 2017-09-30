/* 	Hier staan functies die querys uitvoeren in de database.
*	Copy right: Nick Kuijpers
*/

package execute;

import java.sql.*;
import java.util.Calendar;

public class Database {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;
    private int count = 0;
    Calendar calendar = Calendar.getInstance();
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    java.sql.Timestamp time = new java.sql.Timestamp(calendar.getTime().getTime());

    //#######Database details#######
    private String dbHost = "localhost";
    private String dbPort = "3306";
    private String dbName = "beroepsopdracht";
    private String dbUser = "java";
    private String dbPass = "Qazwsx12.";
    //##############################

    public boolean insert (String query) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName, dbUser, dbPass);
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                count++;
            }
            if (count == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
