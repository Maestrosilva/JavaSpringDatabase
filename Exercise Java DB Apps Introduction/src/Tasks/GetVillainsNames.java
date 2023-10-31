package Tasks;

import classes.*;
import java.sql.*;
import static resources.Colors.*;

public class GetVillainsNames {
    public GetVillainsNames(){
        System.out.println(YELLOW + "Task #2");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);
        try {
            ResultSet resultSet = Villain.getVillainNamesAndNumberOfMinions(15);
            while (resultSet.next()) {
                String villainName = resultSet.getString("name");
                int numberOfMinions = resultSet.getInt("number_of_minions");
                System.out.println(villainName + " " + numberOfMinions);
            }
        }
        catch (SQLException e){
            System.out.println(RED + "Something went wrong.");
            System.out.print(RESET);
        }
    }
}