package Tasks;

import classes.*;
import java.sql.SQLException;
import java.util.*;
import static resources.Colors.*;
public class ChangeTownNamesCasing {
    private static final Scanner scanner = new Scanner(System.in);
    public ChangeTownNamesCasing() {
        System.out.println(YELLOW + "Task #5");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);

        System.out.println("Enter country name:");
        try {
            String countryName = scanner.nextLine();

            int changes = Town.updateTownName(countryName);
            if(changes == 0){
                System.out.println(RED + "No town names were affected.");
                System.out.print(RESET);
                return;
            }
            System.out.printf(GREEN + "%d town names were affected.\n", changes);
            System.out.print(RESET);
            List<String> updatedTowns = Town.getTownNamesByCountry(countryName);
            System.out.printf("[%s]%n", String.join(", ", updatedTowns));
        }
        catch (SQLException e){
            System.out.println(RED + "Something went wrong.");
            System.out.print(RESET);
        }
    }
}