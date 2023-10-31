package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Connector {
    public static Connection connection;
    private static final Scanner scanner = new Scanner(System.in);
    public static void connect() throws SQLException {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", properties);
    }
}