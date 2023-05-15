package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class UseJDBC {
    public static void main(String[] args) throws SQLException{
        try (Connection conn = DriverManager.getConnection("jdbc:derby:simpledb;create=true");
             Statement statement = conn.createStatement();){
            try {
                statement.execute("drop table drinks");
                System.out.println("Table 'drinks' existed, deleted");
            } catch (SQLException e) {
                System.out.println("Table 'drinks' didn't already exist");
            }
            boolean createRes = statement.execute(
                "create table drinks ( " +
                " id INTEGER PRIMARY KEY GENERATED ALWAYS " +
                "AS IDENTITY(START WITH 1, INCREMENT BY 1), " +
                "name VARCHAR(12) NOT NULL, DRINK VARCHAR(20), " +
                "cash INT, currency CHAR(1))");
            // execute returns "true" to indicate a ResultSet is available
            // false if there's just an update count or no results
            System.out.println("execute create table returns "+createRes); // no rows so false

            boolean insertRes = statement.execute(
                "insert into drinks(name, drink, cash, currency) " +
                "values('Fred', 'Coffee, with cream', 90, '$')");
            System.out.println("execute insert returns "+insertRes);
            int insertRowCount = statement.getUpdateCount();
            System.out.println("insert modified "+ insertRowCount + " rows");

            statement.executeUpdate( // more appropriate for insert and update, returns no. of rows effected directly
                "insert into drinks(name, drink, cash, currency) " +
                    "values('Marie', 'Coffee Milk, Badam', 50, '$')");
            statement.executeUpdate(
                "insert into drinks(name, drink, cash, currency) " +
                    "values('Jim', 'Tea, English style', 100, '$')");

            statement.addBatch(
                "insert into drinks(name, drink, cash, currency) " +
                    "values('Sheila', 'Water', 75, '$')");
            statement.addBatch(
                "insert into drinks(name, drink, cash, currency) " +
                    "values('Alice', 'Wine Red', 150, '$')");
            int[] batchRes = statement.executeBatch(); // 2 elements [1, 1] rows
            System.out.println("batch results are "+ Arrays.toString(batchRes));

            // ResultSet res = statement.executeQuery(
            //     "select * from drinks where drink like '%Coffee%'"
            // );
            boolean selectRes = statement.execute(
                "select * from drinks where drink like '%Coffee%'");
            System.out.println("select returns "+ selectRes);
            ResultSet res = statement.getResultSet();

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                String drink = res.getString("drink");
                String cash = res.getString("cash");
                String currency = res.getString("currency");
                System.out.printf("%3d : %12s, likes %s%s\n", id, name, drink,(cash==null)? "":", and is carrying "+ currency + cash);
            }

        } // end of try connection + statement
        // closing the statement closes any result set,
        // closing the connection closes statement
    }
}
