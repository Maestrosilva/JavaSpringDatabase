package Exercises;

import entities.Address;
import java.util.List;

import static projectResources.Color.*;

public class AddressesWithEmployeeCount {
    public AddressesWithEmployeeCount() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.print(RESET);

        try {
            List<Address> addressesInfo = Address.getAllAddressesAndTownsWithPeopleLivingThere();
            addressesInfo.stream()
                    .map(a -> String.format("%s, %s - %d addresses"
                            , a.getText(), a.getTown() == null ? "Unknown" : a.getTown().getName(), a.getEmployees().size()))
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