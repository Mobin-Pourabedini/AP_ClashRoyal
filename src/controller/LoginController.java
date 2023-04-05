package controller;

import java.util.Scanner;
import java.util.regex.Matcher;

import model.ClashRoyal;
import model.User;
import view.MainMenu;

import static controller.MyUtilities.isPasswordValid;
import static controller.MyUtilities.isUsernameValid;

public class LoginController {
    public String register(Matcher matcher) {
        matcher.matches();
        String username = matcher.group("username");
        String password = matcher.group("password");

        if (!isUsernameValid(username)) {
            return "Incorrect format for username!\n";
        }

        if (!isPasswordValid(password)) {
            return "Incorrect format for password!\n";
        }

        if (ClashRoyal.getUserByUsername(username) != null) {
            return "Username already exists!\n";
        }

        User user = new User(username, password);
        ClashRoyal.addUser(user);
        return "User " + username + " created successfully!\n";
    }

    public String login(Matcher matcher, Scanner scanner) {
        matcher.matches();
        String username = matcher.group("username");
        String password = matcher.group("password");

        if (!isUsernameValid(username)) {
            return "Incorrect format for username!\n";
        }

        if (!isPasswordValid(password)) {
            return "Incorrect format for password!\n";
        }

        User user = ClashRoyal.getUserByUsername(username);
        if (user == null) {
            return "Username doesn't exist!\n";
        }

        if (!user.getPassword().equals(password)) {
            return "Password is incorrect!\n";
        }

        System.out.print("User " + username + " logged in!\n");
        MainController mainController = new MainController(user);
        MainMenu mainMenu = new MainMenu(scanner, mainController);
        mainMenu.run();
        return null;
    }
}
