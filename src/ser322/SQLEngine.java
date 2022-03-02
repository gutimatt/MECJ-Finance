package ser322;

import java.io.InputStreamReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Responsible for connecting to database and executing queries
 * @author : Matthew Gutierrez
 * @version : 1.0
 **/
public class SQLEngine {
    private final String[] args;
    private ResultSet rs = null;
    private ResultSetMetaData rsMeta = null;
    private Statement stmt = null;
    private PreparedStatement prepStmt = null;
    private Connection conn = null;

    public SQLEngine(String[] args) {
        this.args = args;
    }

    /**
     * Connects to the database
     */
    public void connect() throws Exception{
        if (args.length < 4) {
            throw new IllegalArgumentException("USAGE: java ser322.BudgetEngine <url> <user> <passwd> <driver>");
        }

        String _url = args[0];
        String username = args[1];
        String password = args[2];
        String driver = args[3];

        Class.forName(driver);

        conn = DriverManager.getConnection(_url, username, password);
        conn.setAutoCommit(false);
    }

    /**
     * always make sure to close the connection when finished with db connection to prevent leak
     */
    public void close() {
        try {
            if (rs != null)
                rs.close();
            if (prepStmt != null)
                prepStmt.close();
            if (stmt != null)
                stmt.close();
            if (conn != null) {
                conn.rollback();
                conn.close();
            }
            System.out.println("SUCCESS: resources closed");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ResultSetMetaData getRsMeta(String table) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + table);
        return rs.getMetaData();
    }

    /**
     * inserts into the database.  Is generic by getting metadata
     */
    public void insert(String table) throws SQLException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        ResultSetMetaData meta = getRsMeta(table);

        String s = "INSERT INTO ? (";

        List<String> dataList = new LinkedList<>();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.print("What is the " + meta.getColumnLabel(i) + "?\n" +
                    ">>");
            dataList.add(scanner.nextLine());
            if (i < meta.getColumnCount())
                s += meta.getColumnLabel(i) + ", ";
            else s += meta.getColumnLabel(i) + ") ";
        }

        s += "VALUES (";

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            if (i < meta.getColumnCount())
                s += "?, ";
            else s += "?);";
        }

        prepStmt = conn.prepareStatement(s);
        prepStmt.setString(1, table);

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            prepStmt.setObject(i+1, dataList.get(i-1), meta.getColumnType(i));
        }

        prepStmt.executeUpdate();
        conn.commit();
        scanner.close();
    }

    public void update() throws SQLException {
        String depositStmt = "UPDATE EXPENSE SET Description = 'Gas' WHERE Title = 'Shell'";
        stmt.executeUpdate(depositStmt);
    }

    public void select() throws SQLException {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Title FROM BILL b, EXPENSE e WHERE Duedate = '2022-01-01 12:00:00'AND b.Userid=e.Userid");
        while(rs.next()){
            System.out.print(rs.getString(1) + "\t");
        }
        rs = stmt.executeQuery("SELECT Totalamount, Currentamount, Description FROM GOAL g, EXPENSE e WHERE Category = 1 AND g.UserId = e.UserId");
        while(rs.next()){
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getInt(2) + "\t");
        }
        rs = stmt.executeQuery("SELECT Description FROM EXPENSE WHERE Title = 'Shell' OR Title = 'Grocery Store'");
        while(rs.next()){
            System.out.print(rs.getInt(1) + "\t");
            System.out.print(rs.getInt(2) + "\t");
        }
        rs = stmt.executeQuery("SELECT Description FROM EXPENSE WHERE Amount BETWEEN 100 AND 200");
        while(rs.next()){
            System.out.print(rs.getString(1) + "\t");
        }
    }

    /**
     * Delete a row from a table using the appropriate table ID for the row
     *
     * @param table String the table to delete the row from
     * @param id String the ID of the row to delete
     */
    public void delete(String table, String id) throws SQLException{
        String s = null;
        String attribute = null;

        switch (table) {
            case "user":
                attribute = "Userid";
                break;
            case "transaction":
                attribute = "Transactionid";
                break;
            case "bill":
                attribute = "Billid";
                break;
            case "expense":
                attribute = "Expenseid";
                break;
            case "budget":
                attribute = "BudgetId";
                break;
            case "goal":
                attribute = "Goalid";
                break;
            case "category":
                attribute = "Categoryid";
                break;
        }

        s = "DELETE FROM " + table.toUpperCase() + " WHERE " + attribute + " = ?";

        prepStmt = conn.prepareStatement(s);
        prepStmt.setString(1, id);
        prepStmt.executeUpdate();
        conn.commit();
    }
}
