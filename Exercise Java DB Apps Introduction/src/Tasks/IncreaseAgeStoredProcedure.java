package Tasks;

import classes.*;
import java.sql.SQLException;
import java.util.Scanner;
import static resources.Colors.*;

public class IncreaseAgeStoredProcedure {
    public static final Scanner scanner = new Scanner(System.in);
    public IncreaseAgeStoredProcedure() {
        System.out.println(YELLOW + "Task #9");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);

        System.out.println("Enter minion id:");
        try {
            int minionId = Integer.parseInt(scanner.nextLine());

            Minion.deleteProcedure();
            Minion.createIncreaseAgeProcedure();
            Minion.executeProcedure(minionId);
            Minion.printAllNamesAndAges();
        }
        catch (NumberFormatException e){
            System.out.println(RED + "Incorrect Input.");
            System.out.print(RESET);
        }
        catch (SQLException e){
            System.out.println(RED + "Something went wrong.");
            System.out.print(RESET);
        }
    }
}