package Exercises;

import entities.Employee;
import java.util.List;

import static projectResources.Color.*;

public class EmployeesWithSalaryOver50_000 {
    private static final double minSalary = 50_000;
    public EmployeesWithSalaryOver50_000() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.print(RESET);

        try {
            List<String> employeeNames = Employee.getAllNamesOfEmployeesWithSalaryMoreThan(minSalary);
            employeeNames.forEach(System.out::println);
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
        }
        finally {
            System.out.print(RESET);
        }
    }
}
