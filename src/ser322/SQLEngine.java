package ser322;

import java.sql.*;

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

    //todo: Matthew working on
    /**
     * inserts into the database.  Is generic by getting metadata
     */
    public ResultSet insert(String table, String[] values) {
        //todo: return actual value
        return null;
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
    public ResultSet execute(String[] inputs, String[] values) {
        if (inputs[0].equals(dbProp.INSERT))
            return insert(inputs[1], values);
        //todo: return actual value
        return null;
    }
}
