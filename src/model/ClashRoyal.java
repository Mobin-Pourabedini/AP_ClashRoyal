package model;

import java.util.ArrayList;
import java.util.List;

public class ClashRoyal {
    private static final List<User> users = new ArrayList<>();

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static int getRank(User user) {
        int result = 1;
        for (User userInList : users) {
            if (user.compareTo(userInList) > 0) result++;
        }
        return result;
    }
}
