package Exercises;

import entities.Town;

import static projectResources.Color.*;

public class ChangeCasing {
    private static final int LENGTH = 5;
    public ChangeCasing() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.print(RESET);

        try {
            int affectedRows = Town.changeToUpperCaseWhenNameLengthIsMoreThan(LENGTH);
            System.out.printf(GREEN + (affectedRows != 0 ? "Successfully updated database.%n" : ""));
            System.out.printf(GREEN + "Affected rows: %d%n", affectedRows);
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
        }
        finally {
            System.out.print(RESET);
        }
    }
}