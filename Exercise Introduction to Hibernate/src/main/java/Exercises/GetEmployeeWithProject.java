package Exercises;

import entities.Employee;
import entities.Project;
import java.util.Comparator;
import java.util.Scanner;

import static projectResources.Color.*;

public class GetEmployeeWithProject {
    private static final Scanner scanner = new Scanner(System.in);
    public GetEmployeeWithProject() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.println(CYAN + "Enter employee id:");
        System.out.print(RESET);
        try {
            int employeeId = Integer.parseInt(scanner.nextLine());
            Employee employee = Employee.getEmployeeById(employeeId);
            System.out.printf("%s %s - %s%n",
                    employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
            employee.getProjects().stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .forEach(p -> System.out.println("   " + p.getName()));
        }
        catch (NumberFormatException e){
            System.out.println(RED + "Incorrect Input");
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
        }
        finally {
            System.out.print(RESET);
        }
    }
}