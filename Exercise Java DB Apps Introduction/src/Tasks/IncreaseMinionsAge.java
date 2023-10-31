package Tasks;

import classes.*;
import java.sql.SQLException;
import java.util.*;

import static resources.Colors.*;

public class IncreaseMinionsAge {
    private static final Scanner scanner = new Scanner(System.in);
    public IncreaseMinionsAge() {
        System.out.println(YELLOW + "Task #8");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);

        System.out.println("Enter minion ids");
        try {
            int[] ids = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int id : ids) {
                Minion.increaseAgeById(id);
            }
            Minion.printAllNamesAndAges();
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