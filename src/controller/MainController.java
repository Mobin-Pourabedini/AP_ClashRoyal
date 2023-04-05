package controller;

import model.ClashRoyal;
import model.User;
import view.Commands;
import view.GameMenu;
import view.ProfileMenu;
import view.ShopMenu;

import java.util.*;
import java.util.regex.Matcher;

public class MainController {
    private final User user;

    public MainController(User user) {
        this.user = user;
    }

    public String logout() {
        return "User " + user.getUsername() + " logged out successfully!\n";
    }

    public void enterProfileMenu(Scanner scanner) {
        ProfileController profileController = new ProfileController(this.user);
        ProfileMenu profileMenu = new ProfileMenu(scanner, profileController);
        profileMenu.run();
    }

    public void enterShopMenu(Scanner scanner) {
        ShopController shopController = new ShopController(this.user);
        ShopMenu shopMenu = new ShopMenu(scanner, shopController);
        shopMenu.run();
    }

    public String startGame(Matcher matcher, Scanner scanner) {
        matcher.matches();
        String username = matcher.group("username");
        String turnsCountString = matcher.group("turnsCount");
        int turnCnt = Integer.parseInt(turnsCountString);

        if (turnCnt < 5 || turnCnt > 30) {
            return "Invalid turns count!\n";
        }

        if (!MyUtilities.isUsernameValid(username)) {
            return "Incorrect format for username!\n";
        }

        User user = ClashRoyal.getUserByUsername(username);
        if (user == null) {
            return "Username doesn't exist!\n";
        }
        GameController gameController = new GameController(this.user, user, turnCnt);
        GameMenu gameMenu = new GameMenu(scanner, gameController);
        System.out.print("Battle started with user " + user.getUsername() + "\n");
        gameMenu.run();
        return null;
    }

    public String showListUsers() {
        String result = "";
        int index = 0;
        for (User userInList : ClashRoyal.getUsers()) {
            index++;
            result = result.concat(new Formatter()
                    .format("user %d: %s\n", index, userInList.getUsername()).toString());
        }
        return result;
    }

    public String showScoreboard() {
        String result = "";

        List<User> orderedList = new ArrayList<>(ClashRoyal.getUsers());
        Collections.sort(orderedList);
        int index = 0;
        for (User userInList : orderedList) {
            index++;
            if (index > 5) break;
            result = result.concat(new Formatter()
                    .format("%d- username: %s level: %d experience: %d\n",
                            index, userInList.getUsername(), userInList.getLevel(),
                            userInList.getExperience())
                    .toString());
        }
        return result;
    }
}
