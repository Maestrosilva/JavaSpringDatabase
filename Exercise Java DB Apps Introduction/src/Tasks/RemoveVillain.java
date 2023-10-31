package Tasks;

import classes.*;
import java.sql.SQLException;
import java.util.Scanner;
import static resources.Colors.*;

public class RemoveVillain {
    Scanner scanner = new Scanner(System.in);
    public RemoveVillain() {
        System.out.println(YELLOW + "Task #6");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);
        System.out.println("Enter villain id:");
        try {
            int villainId = Integer.parseInt(scanner.nextLine());
            Villain.deleteVillainById(villainId);
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