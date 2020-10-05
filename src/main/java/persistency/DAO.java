package persistency;

import app.ConsoleMenu;
import constants.Constants;
import domain.User;
import utils.UtilsFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    Connection conn;
    public DAO(String userName, String password,
               String serverName, String portNumber, String database){
        conn = DatabaseConnector.getConnection(userName,password,serverName,portNumber, database);
    }

    public void addUser(String name, int age, float salary)
    {
        String query = "INSERT INTO users (name, age, salary) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setFloat(3, salary);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.toString());
        }
    }

    public void updateUser(int id, String name, int age, float salary)
    {
        String query = "UPDATE users SET name = ? , age = ? , salary = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setFloat(3, salary);
            preparedStatement.setInt(4, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void filterUser(Constants.Filter filter)
    {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next())
            {
                User user = new User(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getFloat("salary"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> filterUsers = new ArrayList<>();
        switch (filter)
        {
            case name:
                filterUsers = UtilsFilter.filterByName(users, ConsoleMenu.askForName("Name: "));
                break;
            case age:
                filterUsers = UtilsFilter.filterByAge(users, ConsoleMenu.askForInteger("Age: "));
                break;
            case salary:
                filterUsers = UtilsFilter.filterBySalary(users, ConsoleMenu.askForFloat("Salary: "));
                break;
            case highestAge:
                filterUsers = UtilsFilter.filterByHighestAge(users);
                break;
            case highestSalary:
                filterUsers = UtilsFilter.filterByHighestSalary(users);
                break;
            case ageRange:
                filterUsers = UtilsFilter.filterByAgeRange(users,
                        ConsoleMenu.askForInteger("Min age: "), ConsoleMenu.askForInteger("Max age: "));
                break;
            case salaryRange:
                filterUsers = UtilsFilter.filterBySalaryRange(users,
                        ConsoleMenu.askForFloat("Min salary: "), ConsoleMenu.askForFloat("Max salary: "));
                break;
        }
        ConsoleMenu.showFilterList(filterUsers);
    }

    public void removeUser(int id)
    {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error closing connection: " + e.toString());
        }
    }
}
