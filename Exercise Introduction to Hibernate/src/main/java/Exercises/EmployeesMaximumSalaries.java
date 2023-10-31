package Exercises;

import entities.Department;
import java.util.List;

import static projectResources.Color.*;

public class EmployeesMaximumSalaries {
    private static final double moneyMoreThan = 70_000;
    private static final double moneyLessThan = 30_000;
    public EmployeesMaximumSalaries() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.print(RESET);

        List<Object[]> departmentsInfo = Department.getDepartmentNamesWithMaxSalaries(moneyLessThan, moneyMoreThan);
        departmentsInfo.forEach(d -> System.out.printf("%s %.2f%n", d[0].toString(), Double.parseDouble(d[1].toString()))
        );
    }
}