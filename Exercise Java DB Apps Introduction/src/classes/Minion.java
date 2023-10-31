package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static resources.Connector.connection;
import static resources.Colors.*;
import static resources.Queries.*;

public class Minion {
    public static boolean minionsExists(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_MINION_ID_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public static void addMinionToDatabase(String minionName, int age, String townName, String villainName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_MINION);
        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, age);
        int townId = Town.getTownIdByName(townName);
        preparedStatement.setInt(3, townId);
        preparedStatement.execute();
        System.out.printf(GREEN + "Successfully added %s to be minion of %s", minionName, villainName);
        System.out.print(RESET);
    }
    public static ResultSet getMinionNamesAndAgesByVillainId(int villainId) throws SQLException {
        PreparedStatement preparedStatement
                = connection.prepareStatement(GET_MINION_NAMES_AND_MINION_AGE);
        preparedStatement.setInt(1, villainId);
        return preparedStatement.executeQuery();
    }
    public static int getMinionIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_MINION_ID_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    public static void releaseMinionsOfVillainWithId(int villainId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(RELEASE_MINIONS_OF_VILLAIN_WITH_ID);
        preparedStatement.setInt(1, villainId);
        preparedStatement.execute();
    }

    public static List<String> getAllNames() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MINION_NAMES);
        ArrayDeque<String> minionNames = new ArrayDeque<>();
        List<String> output = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            minionNames.add(resultSet.getString(1));
        }
        try {
            while (!minionNames.isEmpty()){
                output.add(minionNames.removeFirst());
                output.add(minionNames.removeLast());
            }
        }
        catch (NoSuchElementException ignored){}
        return output.stream().toList();
    }

    public static void increaseAgeById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INCREASE_MINION_AGE);
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
    }

    public static void printAllNamesAndAges() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MINION_NAMES_AND_AGES);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String minionName = resultSet.getString(1);
            int minionAge = resultSet.getInt(2);
            System.out.println(minionName + " " + minionAge);
        }
    }

    public static void createIncreaseAgeProcedure() throws SQLException {
        PreparedStatement createProcedure = connection.prepareStatement(CREATE_PROCEDURE);
        try {

        createProcedure.execute();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void deleteProcedure() throws SQLException {
        PreparedStatement createProcedure = connection.prepareStatement(DROP_PROCEDURE);
        createProcedure.execute();
    }

    public static void executeProcedure(int minionId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(EXECUTE_PROCEDURE);
        preparedStatement.setInt(1, minionId);
        preparedStatement.execute();
    }

    public static int getReleasedMinionsCount(int villainId) throws SQLException {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_NUMBER_OF_MINIONS_BY_VILLAIN_ID);
            preparedStatement.setInt(1, villainId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 1;
    }
}