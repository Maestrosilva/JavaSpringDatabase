package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static resources.Connector.connection;
import static resources.Queries.*;
import static resources.Colors.*;

public class Villain {
    public static boolean villainExists(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_VILLAIN_ID_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public static void addVillainToDatabase(String name, String evilnessFactor) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_VILLAIN);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, evilnessFactor);
        preparedStatement.execute();
        System.out.printf(GREEN + "Villain %s was added to the database.%n", name);
        System.out.print(RESET);
    }
    public static String getVillainNameById(int villainId) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        preparedStatement.setInt(1, villainId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        try{
            return resultSet.getString("name");
        }
        catch (SQLException e){
            return null;
        }
    }
    public static ResultSet getVillainNamesAndNumberOfMinions(int maxNumberOfMinions) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(GET_VILLAIN_NAMES_AND_NUMBER_OF_MINIONS_BY_MAXIMUM_MINION_COUNT);
        preparedStatement.setInt(1, maxNumberOfMinions);
        return preparedStatement.executeQuery();
    }
    public static int getVillainIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_VILLAIN_ID_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }
    public static void deleteVillainById(int villainId) throws SQLException {
        String villainName = getVillainNameById(villainId);
        if(villainName != null){
            System.out.println(villainName + " was deleted");
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VILLAIN_BY_ID);
            int releasedMinions = Minion.getReleasedMinionsCount(villainId);
            Minion.releaseMinionsOfVillainWithId(villainId);
            preparedStatement.setInt(1, villainId);
            preparedStatement.execute();
            System.out.println(releasedMinions + " minions released");
        }
        else{
            System.out.println(RED + "No such villain was found");
            System.out.print(RESET);
        }
    }
}