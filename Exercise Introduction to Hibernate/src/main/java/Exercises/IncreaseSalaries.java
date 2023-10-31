package Exercises;

import entities.Employee;
import java.util.List;
import java.util.Set;

import static projectResources.Color.*;
import static projectResources.StaticEntityManager.entityManager;

public class IncreaseSalaries {
    private static final double PERCENT = 12;
    private static final Set<String> DEPARTMENT_IDS =
            Set.of("Engineering", "Tool Design", "Marketing", "Information Services");
    public IncreaseSalaries() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.print(RESET);

        try {
            List<Employee> employees = Employee.getEmployeeByDepartment(DEPARTMENT_IDS);
            entityManager.getTransaction().begin();
            employees.forEach(e -> e.increaseSalary(PERCENT));
            entityManager.getTransaction().commit();
            System.out.println(GREEN + "Successfully increased salaries");
            System.out.print(RESET);
            employees.stream()
                            .map(e -> String.format("%s %s ($%.2f)"
                                    , e.getFirstName(), e.getLastName(), e.getSalary()))
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