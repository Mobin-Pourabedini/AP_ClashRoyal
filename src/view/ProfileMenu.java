package view;

import controller.ProfileController;

import java.util.Scanner;

public class ProfileMenu {
    private Scanner scanner;
    private ProfileController profileController;

    public ProfileMenu(Scanner scanner, ProfileController profileController) {
        this.scanner = scanner;
        this.profileController = profileController;
    }

    public void run() {
        String command = this.scanner.nextLine();

        while (!Commands.getMatcher(command, Commands.BACK).matches()) {
            if (Commands.getMatcher(command, Commands.CHANGE_PASSWORD).matches()) {
                String output = profileController.changePassword(
                        Commands.getMatcher(command, Commands.CHANGE_PASSWORD));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.INFO).matches()) {
                String output = profileController.getInfo();
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.REMOVE_CARD).matches()) {
                String output = profileController.removeCard(
                        Commands.getMatcher(command, Commands.REMOVE_CARD));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.ADD_CARD).matches()) {
                String output = profileController.addCard(
                        Commands.getMatcher(command, Commands.ADD_CARD));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.SHOW_CARD).matches()) {
                String output = profileController.showCard();
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.SHOW_CURRENT_MENU).matches()) {
                System.out.print("Profile Menu\n");
                command = this.scanner.nextLine();
                continue;
            }
            System.out.print("Invalid command!\n");
            command = this.scanner.nextLine();
        }
    }
}
