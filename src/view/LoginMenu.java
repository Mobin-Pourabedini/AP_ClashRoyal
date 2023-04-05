package view;

import controller.LoginController;

import java.util.Scanner;

public class LoginMenu {
    private Scanner scanner;
    private LoginController loginController;

    public LoginMenu(Scanner scanner, LoginController loginController) {
        this.scanner = scanner;
        this.loginController = loginController;
    }

    public void run() {
        String command = this.scanner.nextLine();

        while (!Commands.getMatcher(command, Commands.EXIT).matches()) {
            if (Commands.getMatcher(command, Commands.REGISTER).matches()) {
                String output = loginController.register(
                        Commands.getMatcher(command, Commands.REGISTER));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.LOGIN).matches()) {
                String output = loginController.login(
                        Commands.getMatcher(command, Commands.LOGIN), this.scanner);
                if (output != null) System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.SHOW_CURRENT_MENU).matches()) {
                System.out.print("Register/Login Menu\n");
                command = this.scanner.nextLine();
                continue;
            }

            System.out.print("Invalid command!\n");
            command = this.scanner.nextLine();
        }
    }
}
