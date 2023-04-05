package view;

import controller.MainController;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final MainController mainController;

    public MainMenu(Scanner scanner, MainController mainController) {
        this.scanner = scanner;
        this.mainController = mainController;
    }

    public void run() {
        String command = this.scanner.nextLine();

        while (true) {
            if (Commands.getMatcher(command, Commands.LOGOUT).matches()) {
                String output = mainController.logout();
                System.out.print(output);
                break;
            }
            if (Commands.getMatcher(command, Commands.PROFILE_MENU).matches()) {
                System.out.print("Entered profile menu!\n");
                mainController.enterProfileMenu(this.scanner);
                System.out.print("Entered main menu!\n");
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.SHOP_MENU).matches()) {
                System.out.print("Entered shop menu!\n");
                mainController.enterShopMenu(this.scanner);
                System.out.print("Entered main menu!\n");
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.START_GAME).matches()) {
                String output = mainController.startGame(
                        Commands.getMatcher(command, Commands.START_GAME), this.scanner);
                if (output != null) System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.LIST_USERS).matches()) {
                String output = mainController.showListUsers();
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.SCOREBOARD).matches()) {
                String output = mainController.showScoreboard();
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.SHOW_CURRENT_MENU).matches()) {
                System.out.print("Main Menu\n");
                command = this.scanner.nextLine();
                continue;
            }
            System.out.print("Invalid command!\n");
            command = this.scanner.nextLine();
        }
    }
}
