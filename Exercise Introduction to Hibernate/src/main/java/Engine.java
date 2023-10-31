import Exercises.*;
import java.util.Scanner;
import static projectResources.Color.*;

public class Engine implements Runnable{
    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public void run() {
        System.out.println(CYAN + "Enter 'stop' to stop the program.");
        System.out.println(CYAN + "Enter task number (from 2 to 13): ");
        String taskNumber;
        while (!(taskNumber = scanner.nextLine()).equals("stop")){
            try {
                switch (taskNumber) {
                    case "2" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new ChangeCasing();
                        /*
                        Use the soft_uni database. Persist all towns from the database.
                        Detach those whose name length is more than 5 symbols.
                        Then transform the names of all attached towns
                        to uppercase and save them to the database.
                         */
                    }
                    case "3" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new ContainsEmployee();
                        /*
                        Use the soft_uni database. Write a program that checks
                        if a given employee name is contained in the database.
                         */
                    }
                    case "4" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new EmployeesWithSalaryOver50_000();
                        /*
                        Write a program that gets the first name of all employees
                        who have salary over 50 000.
                         */
                    }
                    case "5" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new EmployeesFromDepartment();
                        /*
                        Extract all employees from the Research and Development department.
                        Order them by salary (in ascending order), then by id (in ascending order).
                        Print only their first name, last name, department name and salary.
                         */
                    }
                    case "6" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new AddingANewAddressAndUpdatingEmployee();
                        /*
                        Create a new address with text "Vitoshka 15".
                        Set that address to an employee with a last name, given as an input.
                         */
                    }
                    case "7" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new AddressesWithEmployeeCount();
                        /*
                        Find all addresses, ordered by the number of employees who live
                        there (descending). Take only the first 10 addresses and print
                        their address text, town name and employee count.
                         */
                    }
                    case "8" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new GetEmployeeWithProject();
                        /*
                        Get an employee by his/her id. Print only his/her first name, last name,
                        job title and projects (only their names). The projects should be ordered
                        by name (ascending).
                         */
                    }
                    case "9" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new FindLatest10Projects();
                        /*
                        Write a program that prints the last 10 started projects.
                        Print their name, description, start and end date and
                        sort them by name lexicographically.
                         */
                    }
                    case "10" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new IncreaseSalaries();
                        /*
                        Write a program that increases the salaries of all employees,
                        who are in the Engineering, Tool Design, Marketing or Information
                        Services departments by 12%. Then print the first name, the last name
                        and the salary for the employees, whose salary was increased.
                         */
                    }
                    case "11" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new FindEmployeesByFirstName();
                        /*
                        Write a program that finds all employees, whose first name start with
                        a pattern given as an input from the console. Print their first and
                        last names, their job title and salary in the format given in the example below.
                         */
                    }
                    case "12" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new EmployeesMaximumSalaries();
                        /*
                        Write a program that finds the max salary for each department.
                        Filter the departments, which max salaries are not
                        in the range between 30000 and 70000.
                         */
                    }
                    case "13" -> {
                        System.out.printf(YELLOW + "Task #%s:%n", taskNumber);
                        new RemoveTowns();
                        /*
                        Write a program that deletes a town, which name is given as an input.
                        The program should delete all addresses that are in the given town. Print on
                        the console the number of addresses that were deleted.
                         */
                    }
                    default -> {
                        System.out.println(RED + "Incorrect Task Number.");
                        System.out.print(RESET);
                    }
                }
            }
            catch (Exception e){
                System.out.println(RED + e.getMessage());
                System.out.print(RESET);
            }
            System.out.println();
            System.out.println();
            System.out.println(GREEN + "Enter task number: ");
        }
    }
}