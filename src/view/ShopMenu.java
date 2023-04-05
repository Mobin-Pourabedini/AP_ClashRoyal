package view;

import controller.ShopController;

import java.util.Scanner;

public class ShopMenu {
    private Scanner scanner;
    private ShopController shopController;

    public ShopMenu(Scanner scanner, ShopController shopController) {
        this.scanner = scanner;
        this.shopController = shopController;
    }

    public void run() {
        String command = this.scanner.nextLine();

        while (!Commands.getMatcher(command, Commands.BACK).matches()) {
            if (Commands.getMatcher(command, Commands.BUY_CARD).matches()) {
                String output = shopController.buyCard(
                        Commands.getMatcher(command, Commands.BUY_CARD));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.SELL_CARD).matches()) {
                String output = shopController.sellCard(
                        Commands.getMatcher(command, Commands.SELL_CARD));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }
            if (Commands.getMatcher(command, Commands.SHOW_CURRENT_MENU).matches()) {
                System.out.print("Shop Menu\n");
                command = this.scanner.nextLine();
                continue;
            }
            System.out.print("Invalid command!\n");
            command = this.scanner.nextLine();
        }
    }
}
