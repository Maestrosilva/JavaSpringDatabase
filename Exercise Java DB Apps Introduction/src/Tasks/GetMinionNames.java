package Tasks;

import classes.*;
import java.sql.*;
import java.util.*;
import static resources.Colors.*;

public class GetMinionNames {
    private static final Scanner scanner = new Scanner(System.in);
    public GetMinionNames() {
        System.out.println(YELLOW + "Task #3");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);

        System.out.println("Enter villain id:");
        try {
            int villainId = Integer.parseInt(scanner.nextLine());

            ResultSet resultSetInfo = Minion.getMinionNamesAndAgesByVillainId(villainId);
            String villainName = Villain.getVillainNameById(villainId);
            if (villainName == null) {
                System.out.println(RED + "No villain with ID " + villainId + " exists in the database.");
                System.out.print(RESET);
                return;
            }
            System.out.println("Villain: " + villainName);

            int count = 1;
            while (resultSetInfo.next()) {
                String name = resultSetInfo.getString("name");
                int age = resultSetInfo.getInt("age");
                System.out.printf("%d. %s %d%n", count++, name, age);
            }
        }
        catch (NumberFormatException e){
            System.out.println(RED + "Invalid input");
            System.out.print(RESET);
        }
        catch (SQLException e){
            System.out.println(RED + "Something went wrong.");
            System.out.print(RESET);
        }
    }
}