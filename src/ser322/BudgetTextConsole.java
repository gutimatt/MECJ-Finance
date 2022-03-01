package ser322;

import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Responsible for taking input from the user and displaying correct menus and results
 * @author : Matthew Gutierrez
 * @version : 1.0
 **/
public class BudgetTextConsole {
    private Scanner scanner;

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

    //todo: write function - similar to menu.  Can call different functions to ask appropriate questions and
    // values.  askInsertValues is an example
    /**
     * Should ask the values that the user wants to insert, update, delete, select for table depending
     * on what is selected in the menu
     * @return string array of the values
     */
    public String[] askValues(String[] inputs) {
        if (inputs[0].equals(dbProp.INSERT))
            askInsertValues(inputs[1]);

        //todo: return actual value
        return new String[0];
    }

    //todo: implement function
    /**
     * gets the values that should be inserted
     * @param table
     */
    private void askInsertValues(String table) {
        // should get metadata from particular table and ask each column what they would like to insert
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
