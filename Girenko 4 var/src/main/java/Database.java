import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static void create(ArrayList<Grant> grants) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        var connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        var s = connection.createStatement();

        s.execute("DROP TABLE IF EXISTS 'Grants';");
        s.execute("CREATE TABLE IF NOT EXISTS 'Grants' " +
                "('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'companyName' varchar(100), " +
                "'streetName' varchar(100)," +
                "'money' real, " +
                "'year' int, " +
                "'businessType' varchar(100), " +
                "'jobsCount' int);");


        var insertPreparedStatement = connection.prepareStatement("INSERT INTO 'Grants' " +
                "('companyName','streetName','money','year','businessType','jobsCount') " +
                "VALUES (?,?,?,?,?,?) ;");
        for (var g : grants)
            g.safedb(insertPreparedStatement);

        connection.close();
    }

    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        var connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        return connection.createStatement();
    }

    public static ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException {
        return getStatement().executeQuery(query);
    }

}
