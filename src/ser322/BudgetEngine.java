package ser322;

/**
 * Entry point for application.  Controller between database and ui
 * @author : Matthew Gutierrez
 * @version : 1.0
 **/
public class BudgetEngine {
    public static void main(String[] args) {
        new BudgetEngine().run(args);
    }

    private void run(String[] args) {
        SQLEngine db = new SQLEngine(args);
        BudgetTextConsole console = new BudgetTextConsole(db);

        try {
            db.connect();
            // loops through menu until quit and asking the same sequence of questions
            while (true) {
                //returns action, table.  Ex: {insert, user}
                String[] inputs = console.displayMainMenu();
                if (inputs[0].equals("quit"))
                    break;
                console.askValues(inputs);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            // cleans resources
            db.close();
            console.close();
        }

    }
}
