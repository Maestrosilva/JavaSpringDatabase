package Exercises;

import entities.Project;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static projectResources.Color.*;

public class FindLatest10Projects {
    private static final int LIMIT = 10;
    public FindLatest10Projects() {
        System.out.println(YELLOW + this.getClass().getSimpleName());
        System.out.print(RESET);

        try {
            List<Project> projects = Project.getLatestStartedProjects(LIMIT);
            projects.stream()
                    .sorted(Comparator.comparing(Project::getName))
                    .map(p -> {
                        String sb = "Project name: " + p.getName() +
                                System.lineSeparator() +
                                "        " + "Project Description:" + formatted(p.getDescription()) +
                                System.lineSeparator() +
                                "        " + "Project Start Date:" + (p.getStartDate() == null ? "null" :
                                p.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS"))) +
                                System.lineSeparator() +
                                "        " + "Project End Date:" + (p.getEndDate() == null ? "null" :
                                p.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS"))) +
                                System.lineSeparator();
                        return sb;
                    })
                    .forEach(System.out::println);
        }
        catch (Exception e){
            System.out.println(RED + e.getMessage());
            System.out.println(RESET);
        }
        finally {
            System.out.print(RESET);
        }
    }

    private String formatted(String description) {
        StringBuilder sb = new StringBuilder();
        String[] words = description.split(" ");
        int wordCounter = 0;
        int rowCount = 1;
        while (wordCounter < words.length){
            if(rowCount > 1){
                sb.append("                ");
            }
            sb.append(String.join(" ", Arrays
                    .copyOfRange(words, wordCounter, Math.min(wordCounter + 8, words.length - 1))));
            sb.append(System.lineSeparator());
            wordCounter += 8;
            rowCount++;
        }
        return sb.toString().trim();
    }
}
//Project name: All-Purpose Bike Stand
// 	Project Description: Research, design and development of …
// 	Project Start Date:2005-09-01 00:00:00.0
// 	Project End Date: null
