package Exercises;

import entities.Address;
import entities.Employee;
import entities.Town;
import java.util.Scanner;

import static projectResources.Color.*;
import static projectResources.StaticEntityManager.entityManager;

public class RemoveTowns {
    private static final Scanner scanner = new Scanner(System.in);
    public RemoveTowns() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.println(CYAN + "Enter a town to delete from the database:");
        System.out.print(RESET);

        String townName = scanner.nextLine();
        Town townToDelete = Town.townWithName(townName);

        entityManager.getTransaction().begin();
        Employee.setNullAddressesOfEmployeesWithTown(townToDelete);
        int deletedAddresses = Address.deleteAddressesOfTown(townToDelete);
        Town.deleteTown(townToDelete);
        entityManager.getTransaction().commit();
        System.out.printf("%d address%s in %s deleted"
                , deletedAddresses, deletedAddresses == 1 ? "" : "es", townToDelete.getName());
    }
}
