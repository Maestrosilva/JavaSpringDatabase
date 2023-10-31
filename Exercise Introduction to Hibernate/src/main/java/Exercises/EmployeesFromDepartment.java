package Exercises;

import entities.Department;
import entities.Employee;
import java.util.stream.Stream;

import static projectResources.Color.*;

public class EmployeesFromDepartment {
    private static final String DEPARTMENT_NAME = "Research and Development";
    public EmployeesFromDepartment() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.print(RESET);

        try {
            Stream<Employee> employees = Department.getInfoForAllEmployeesWithDepartmentName(DEPARTMENT_NAME);
            employees
                    .map(e -> String.format("%s %s from %s - $%.2f"
                            , e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()))
                    .forEach(System.out::println);
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
        }
        finally {
            System.out.print(RESET);
        }
    }
}
