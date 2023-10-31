package Tasks;

import classes.*;
import java.sql.*;
import java.util.*;
import static resources.Colors.*;
import static resources.Queries.*;
import static resources.Connector.connection;

public class AddMinion {
    private static final Scanner scanner = new Scanner(System.in);
    public AddMinion() {
        System.out.println(YELLOW + "Task #4");
        System.out.println(CYAN + this.getClass().getSimpleName());
        System.out.print(RESET);
        System.out.println("Enter minion data and villain name:");
        try {
            String minionData = scanner.nextLine().split(": ")[1];

            String minionName = minionData.split(" ")[0];
            int minionAge = Integer.parseInt(minionData.split(" ")[1]);
            String minionTown = minionData.split(" ")[2];

            String villainName = scanner.nextLine().split(": ")[1];

            if (!Town.townExists(minionTown)) {
                Town.addTownToDatabase(minionTown);
            }
            if (!Villain.villainExists(villainName)) {
                Villain.addVillainToDatabase(villainName, "evil");
            }
            if (!Minion.minionsExists(minionName)) {
                Minion.addMinionToDatabase(minionName, minionAge, minionTown, villainName);
            }
            PreparedStatement connect = connection.prepareStatement(CONNECT_MINIONS_AND_VILLAINS);
            int minionId = Minion.getMinionIdByName(minionName);
            int villainId = Villain.getVillainIdByName(villainName);
            connect.setInt(1, minionId);
            connect.setInt(2, villainId);
            if (!connectionExist(minionId, villainId)) {
                connect.execute();
            }
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println(RED + "Incorrect Input.");
            System.out.print(RESET);
        }
        catch (SQLException e){
            System.out.println(RED + "Something went wrong.");
            System.out.print(RESET);
        }
    }

    private boolean connectionExist(int minionId, int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IF_CONNECTION_EXISTS);
        preparedStatement.setInt(1, minionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Set<Integer> connections = new LinkedHashSet<>();
        while (resultSet.next()){
            connections.add(resultSet.getInt(1));
        }
        return connections.contains(villainId);
    }
}