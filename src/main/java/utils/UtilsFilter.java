package utils;

import domain.User;
import enums.Filter;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UtilsFilter {

    public static List<User> filterByName(List<User> users, final String name)
    {
        return users.stream().filter(user -> user.getName().equals(name)).collect(Collectors.toList());
    }

    public static List<User> filterByAge(List<User> users, int age)
    {
        return users.stream().filter(user -> user.getAge() == age).collect(Collectors.toList());
    }

    public static List<User> filterBySalary(List<User> users, float salary)
    {
        return users.stream().filter(user -> user.getSalary() == salary).collect(Collectors.toList());
    }
    public static List<User> filterByHighestAge(List<User> users)
    {
        List<User> filterUser = new ArrayList<>();
        filterUser.add(users.stream().max((userOne, userTwo) ->
                Utils.numberComparator(userOne.getAge(), userTwo.getAge())).get());
        return filterUser;
    }
    public static List<User> filterByHighestSalary(List<User> users)
    {
        List<User> filterUser = new ArrayList<>();
        filterUser.add(users.stream().max((userOne, userTwo) ->
                Utils.numberComparator(userOne.getSalary(), userTwo.getSalary())).get());
        return filterUser;
    }
    public static List<User> filterByAgeRange(List<User> users, int minAge, int maxAge)
    {
        return users.stream().filter(user ->
                user.getAge() >= minAge && user.getAge() <= maxAge).collect(Collectors.toList());
    }
    public static List<User> filterBySalaryRange(List<User> users, float minSalary, float maxSalary)
    {
        return users.stream().filter(user ->
                user.getSalary() >= minSalary && user.getSalary() <= maxSalary).collect(Collectors.toList());
    }

    public static Filter selectFilter(int input)
    {
        switch (input)
        {
            case 1: return Filter.name;
            case 2: return Filter.age;
            case 3: return Filter.salary;
            case 4: return Filter.ageRange;
            case 5: return Filter.salaryRange;
            case 6: return Filter.highestAge;
            default: return Filter.highestSalary;
        }
    }

}
