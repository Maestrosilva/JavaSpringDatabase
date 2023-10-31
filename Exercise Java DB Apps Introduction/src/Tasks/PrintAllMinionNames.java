package Tasks;

import classes.*;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static resources.Colors.*;

public class PrintAllMinionNames {
    public PrintAllMinionNames() {
        System.out.println(YELLOW + "Task #7");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);
        try {
            List<String> minionNames = Minion.getAllNames();
            System.out.println(minionNames.stream()
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        catch (SQLException e){
            System.out.println(RED + "Something went wrong.");
            System.out.print(RESET);
        }
    }
}