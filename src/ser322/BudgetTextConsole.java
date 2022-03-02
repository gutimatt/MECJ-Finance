package ser322;

import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Responsible for taking input from the user and displaying correct menus and results
 * @author : Matthew Gutierrez
 * @version : 1.0
 **/
public class BudgetTextConsole {
    private Scanner scanner;
    private SQLEngine db;

    public BudgetTextConsole(SQLEngine db) {
        this.db = db;
    }

    /**
     * displays the main menu for actions users will do
     * @return
     */
    public String[] displayMainMenu() {
        scanner = new Scanner(new InputStreamReader(System.in));

        System.out.print("What would you like to do? \n" +
                "Insert \n" +
                "View \n" +
                "Update \n" +
                "Delete \n" +
                "Quit\n" +
                ">>");

        String action = scanner.nextLine().toLowerCase();

        if (action.equals("quit")) {
            scanner.close();
            return new String[]{action};
        }

        System.out.print("What entity would you like to do that for? \n" +
                "User \n" +
                "Budget\n" +
                "Transaction\n" +
                "Bill\n" +
                "Goal\n" +
                "Category\n" +
                ">>");

        String table = scanner.nextLine().toLowerCase();

        return new String[]{action, table};
    }
    /**
     * Should ask the values that the user wants to insert, update, delete, select for table depending
     * on what is selected in the menu
     * @return string array of the values
     */
    public void askValues(String[] inputs) throws SQLException {
        if (inputs[0].equals(dbProp.INSERT)) {
            db.insert(inputs[1]);
        }
        else if (inputs[0].equals(dbProp.DELETE)) {
            System.out.println("Enter id to delete from " + inputs[1]);
            String id = scanner.nextLine();
            db.delete(inputs[1], id);
        }
        else if (inputs[0].equals(dbProp.SELECT)) {
            db.select();
        }
        else if(inputs[0].equals(dbProp.UPDATE)) {
            db.update();
        }
    }


    /**
     * todo: write function - should be generic that we can use for any table
     * prints the database table is selected or confirmation of action
     */
    public void printResult(String[] inputs, ResultSet rs) {
        if (inputs[0].equals(dbProp.INSERT)) {
            System.out.println("Insert complete: Successfully inserted into " + inputs[1]);
            return;
        }
    }

    /**
     * closes the scanner object
     */
    public void close() {
        scanner.close();
    }
}
