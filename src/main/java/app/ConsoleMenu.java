package app;

import domain.User;
import utils.Checker;
import utils.UtilsFilter;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    public static Scanner scanner = new Scanner(System.in);
    private Database database;

    public void showMenu()
    {
        if(database == null)
        database = new Database("root", "admin",
                "localhost","3306", "database");

        int input = askForMenuInput
                ("Select what you want to do: " +
                "\n1.- Add user. " +
                "\n2.- Update user." +
                "\n3.- Filter user. " +
                "\n4.- Remove user." +
                "\n5.- Exit", 6);

        int id;
        String name;
        int age;
        float salary;
        switch (input)
        {
            case 1:
                System.out.println("User data is needed: ");

                name = askForName("Name: ");
                age = askForInteger("Age: ");
                salary = askForFloat("Salary: ");

                database.addUser(name, age, salary);
                break;

            case 2:
                id = askForInteger("ID of user you want to modify: ");
                name = askForName("New name: ");
                age = askForInteger("New age: ");
                salary = askForFloat("New salary: ");

                database.updateUser(id, name, age, salary);
                break;

            case 3:
                int filterInput = askForMenuInput("Filter user by: \n1.- Name. \n2.- Age." +
                                "\n3.- Salary. \n4.- Age Range. \n5.- Salary Range" +
                                "\n6.- Oldest User. \n7.- Highest Salary. \n8.- Back.", 9);
                if(filterInput != 8){
                    database.filterUser(UtilsFilter.selectFilter(filterInput));
                }
                break;

            case 4:
                id = askForInteger("ID of user you want to remove.");
                database.removeUser(id);
                break;

            default:
                System.out.println("Exiting application.");
                database.closeConnection();
                System.exit(0);
                break;
        }
    }

    public int askForMenuInput(String feedback, int options)
    {
        String input = "";
        while (!Checker.checkClientMenuInput(input, options))
        {
            System.out.println(feedback);
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    public static String askForName(String feedback)
    {
        System.out.println(feedback);
        String name = scanner.nextLine();
        return name;
    }

    public static int askForInteger(String feedback)
    {
        String input = "";
        while(!Checker.checkClientIntegerInput(input))
        {
            System.out.println(feedback);
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    public static float askForFloat(String feedback)
    {
        String input = "";
        while(!Checker.checkClientFloatInput(input))
        {
            System.out.println(feedback);
            input = scanner.nextLine();
        }
        return Float.parseFloat(input);
    }

    public static void showFilterList(List<User> users)
    {
        for(User user : users)
        {
            System.out.println("User ID: " + user.getId() + " - Name: " +user.getName()
                    + " - Age: " +user.getAge()+ " - Salary: " +user.getSalary());
        }
    }
}
