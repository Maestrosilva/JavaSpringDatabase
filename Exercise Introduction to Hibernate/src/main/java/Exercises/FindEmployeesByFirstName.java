package Exercises;

import entities.Employee;
import java.util.List;
import java.util.Scanner;

import static projectResources.Color.*;

public class FindEmployeesByFirstName {
    private static final Scanner scanner = new Scanner(System.in);
    public FindEmployeesByFirstName() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.println(CYAN + "Enter first name filter start:");
        System.out.print(RESET);
        String start = scanner.nextLine();
        try {
            List<Employee> employees = Employee.getEmployeeByFirstNamePattern(start.toLowerCase());
            if(employees.isEmpty()){
                System.out.println(RED + "No employee found with such first name start.");
                return;
            }
            employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n"
                    , e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
        }
        finally {
            System.out.print(RESET);
        }
    }
}