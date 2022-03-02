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
//        if (args.length < 4) {
//            throw new IllegalArgumentException("USAGE: java ser322.BudgetEngine <url> <user> <passwd> <driver>");
//        }

        String _url = "jdbc:mysql://localhost:3306/mecj_finance?autoreconnect=true&useSSL=false";
//                args[0];
        String username = "root";
//                args[1];
        String password = "mysqlroot24pass";
//            args[2];
        String driver = "com.mysql.cj.jdbc.Driver";
//            args[3];

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

    //todo: Matthew working on
    /**
     * inserts into the database.  Is generic by getting metadata
     */
    public void insert(String table) throws SQLException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        ResultSetMetaData meta = getRsMeta(table);

        String s = "";
        s += "Insert into ? (";

        List<String> dataList = new LinkedList<>();
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.print("What is the " + meta.getColumnLabel(i) + "?\n" +
                    ">>");
            dataList.add(scanner.nextLine());
            if (i < meta.getColumnCount())
                s += meta.getColumnLabel(i) + ", ";
            else s += meta.getColumnLabel(i) + ") ";
        }

        s += "values (";

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            if (i < meta.getColumnCount())
                s += "?, ";
            else s += "?);";
        }

        prepStmt = conn.prepareStatement(s);
        prepStmt.setString(1, table);

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            prepStmt.setObject(i+1, dataList.get(i-1));
        }

        prepStmt.executeUpdate();
    }

    //todo: write function
    public ResultSet update() {
        //todo: return actual value
        return null;

    }

    //todo: write function
    public ResultSet select() {
        //todo: return actual value
        return null;

    }

    //todo: write function
    public ResultSet delete() {
        //todo: return actual value
        return null;
    }

    //todo: implement function - insert has example for execute
    /**
     * filters and executes the correct sql script
     * @param inputs takes in action, table
     * @param values takes in the values needed
     */
    public ResultSet execute(String[] inputs, String[] values) throws SQLException {
        if (inputs[0].equals(dbProp.INSERT))
            insert(inputs[1]);
        //todo: return actual value
        return null;
    }
}
