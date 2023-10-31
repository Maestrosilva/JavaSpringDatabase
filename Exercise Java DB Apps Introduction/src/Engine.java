import Tasks.*;
import resources.Connector;
import java.sql.SQLException;
import java.util.Scanner;

import static resources.Colors.*;

public class Engine implements Runnable {
    private static final Scanner scanner =
            new Scanner(System.in);

    public void run() {
        try {
            Connector.connect();
        } catch (SQLException e) {
            System.out.println(RED + e.getMessage());
            return;
        }
        System.out.flush();
        System.out.println("Enter 'stop' to stop the program");
        System.out.println("Enter task number (from 2 to 9): ");
        String taskNumber;
        System.console().flush();
        while (!(taskNumber = scanner.nextLine()).equals("stop")) {
            switch (taskNumber) {
                case "2" -> new GetVillainsNames();
                /*
                Write a program that prints on the console all villains’ names and their number of minions.
                Get only the villains who have more than 15 minions. Order them by a number of
                minions in descending order.
                */
                case "3" -> new GetMinionNames();
                /*
                Write a program that prints on the console all minion names and their
                age for a given villain id. For the output, use
                the formats given in the examples
                 */
                case "4" -> new AddMinion();
                /*
                Write a program that reads information about a minion and its villain and
                adds it to the database. In case the town of the minion is not in the database,
                insert it as well. In case the villain is not present in the database, add him too
                with the default evilness factor of “evil”. Finally, set the new minion to be a servant
                 of the villain. Print appropriate messages after each operation – see the examples.
                 */
                case "5" -> new ChangeTownNamesCasing();
                /*
                Write a program that changes all town names to uppercase for a given country.
                Print the number of towns that were changed in the format provided in the examples.
                On the next line print the names that were changed, separated by a coma and a space.
                 */
                case "6" -> new RemoveVillain();
                /*
                Write a program that receives an ID of a villain, deletes him from the database
                and releases his minions from serving him. As an output print the name of the villain and
                the number of minions released. Make sure all operations go as planned, otherwise do not
                make any changes to the database. For the output use the format given in the examples.
                 */
                case "7" -> new PrintAllMinionNames();
                /*
                Write a program that prints all minion names from the minion’s table in order first record,
                last record, first + 1, last – 1, first + 2, last – 2… first + n, last – n.
                 */
                case "8" -> new IncreaseMinionsAge();
                /*
                Read from the console minion IDs, separated by space. Increment the age of those minions
                by 1 and make their names titles lower case. Finally, print the names and the ages of all
                minions that are in the database. See the examples below.
                 */
                case "9" -> new IncreaseAgeStoredProcedure();
                /*
                Create a stored procedure usp_get_older (directly in the database using MySQL Workbench
                or any other similar tool) that receives a minion_id and increases the minion’s years by 1.
                Write a program that uses that stored procedure to increase the age of a minion, whose id will
                be given as input from the console. After that print the name and the age of that minion.
                 */
                default -> {
                    System.out.println(RED + "Invalid Number. Try again.");
                    System.out.print(RESET);
                }
            }
            System.out.println();
            System.out.println();
            System.out.println("Enter task number: ");
        }
    }
}