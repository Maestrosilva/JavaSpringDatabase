package Exercises;

import entities.Address;
import entities.Employee;
import java.util.Scanner;

import static projectResources.Color.*;
import static projectResources.StaticEntityManager.entityManager;

public class AddingANewAddressAndUpdatingEmployee {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ADDRESS_TEXT = "Vitoshka 15";
    public AddingANewAddressAndUpdatingEmployee() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.println(CYAN + "Enter last name of employee:");
        String lastName = scanner.nextLine();

        try{
            entityManager.getTransaction().begin();
            Address newAddress = new Address();
            newAddress.setText(ADDRESS_TEXT);
            entityManager.persist(newAddress);
            System.out.printf(GREEN + "Successfully created a new address with text: %s%n", newAddress.getText());
            Employee employeeToUpdate;
            try {
                employeeToUpdate = Employee.getEmployeeByLastName(lastName);
                employeeToUpdate.setAddress(newAddress);
            }
            catch (IllegalArgumentException e){
                System.out.println(RED + e.getMessage());
                return;
            }
            System.out.printf(GREEN + "Successfully changed %s's address to %s"
                    , employeeToUpdate.getLastName(), newAddress.getText());
            System.out.print(RESET);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
        }
        finally {
            System.out.print(RESET);
        }
    }
}