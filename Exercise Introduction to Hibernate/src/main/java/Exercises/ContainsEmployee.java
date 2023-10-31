package Exercises;

import entities.Employee;
import java.util.Scanner;

import static projectResources.Color.*;

public class ContainsEmployee {
    private static final Scanner scanner = new Scanner(System.in);
    public ContainsEmployee() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.println(CYAN + "Enter employee full name:");
        try {
            String[] employeeFullName = scanner.nextLine().split(" ");
            String firstName = employeeFullName[0];
            String lastName = employeeFullName[1];
            Employee employee = Employee.findEmployeeWithName(firstName, lastName);
            System.out.println(employee != null ? (GREEN + "Yes") : (RED + "No"));
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalArgumentException("Invalid Input");
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
        }
        finally {
            System.out.print(RESET);
        }
    }
}