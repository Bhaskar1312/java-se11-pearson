package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class NullHandling {
    public static void main(String[] args) throws Throwable {
        try(Connection conn = DriverManager.getConnection("jdbc:derby:simpledb;");
            Statement statement = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE drinks SET drink = ?, cash=? WHERE id= ?")) {

            showAll(statement, "SELECT * from drinks");

            ps.setString(1, "Coffee, cream+sugar");
            ps.setInt(2, 200);
            // ps.setString(2, "200"); // Types must be compatible, here it works
            ps.setInt(3, 1);
            int rCount = ps.executeUpdate();
            System.out.println("Update changed "+ rCount + " rows");
            showAll(statement, "SELECT * FROM drinks");

            // // fails: executing an arbitrary statement is NOT permitted on preparedStatement
            // showAll(ps, "SELECT * FROM drinks");

            // parameters remain in use unless overwritten or "clearParameters()"
            // ps.setString(1, "Coffee, cream+sugar"); // not needed as ps retains
            ps.setNull(2, Types.INTEGER); // causes problems on most dbms, types
            // ps.setObject(2, null, Types.INTEGER);
            // ps.setObject(2, null); // this is not reliable, some dbms will complain
            ps.setInt(3, 2);
            rCount = ps.executeUpdate();
            System.out.println("Update changed "+rCount + " rows");
            showAll(statement, "SELECT * FROM drinks");

        }

    }

    private static void showAll(Statement statement, String query) throws SQLException {
        ResultSet rs = statement.executeQuery(query);
        var rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        String[] columnNames = new String[columnCount];
        for(int i=0;i<columnCount;i++) {
            columnNames[i] = rsmd.getColumnName(i+1);
        }
        while (rs.next()) {
            StringBuilder sb = new StringBuilder();
            for(String cn: columnNames) {
                sb.append(cn).append(": ").append(rs.getString(cn)).append(", ");
            }
            sb.setLength(sb.length()-2);
            System.out.println(sb);
        }
    }
}
