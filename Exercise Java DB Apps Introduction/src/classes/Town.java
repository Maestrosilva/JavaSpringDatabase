package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static resources.Colors.GREEN;
import static resources.Colors.RESET;
import static resources.Connector.connection;
import static resources.Queries.*;

public class Town {
    public static boolean townExists(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_TOWN_ID_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    public static int getTownIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_TOWN_ID_BY_NAME);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }
    public static void addTownToDatabase(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_TOWN);
        preparedStatement.setString(1, name);
        preparedStatement.execute();
        System.out.printf(GREEN + "Town %s was added to the database.%n", name);
        System.out.print(RESET);
    }
    public static int updateTownName(String countryName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_TOWN_NAMES_TO_UPPERCASE);
        preparedStatement.setString(1, countryName);
        return preparedStatement.executeUpdate();
    }
    public static List<String> getTownNamesByCountry(String countryName) throws SQLException {
        List<String> towns = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_TOWN_BY_COUNTRY);
        preparedStatement.setString(1, countryName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String town = resultSet.getString(1);
            towns.add(town);
        }
        return towns;
    }
}