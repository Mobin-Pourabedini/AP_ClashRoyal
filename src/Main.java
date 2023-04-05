import controller.LoginController;
import view.LoginMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginController loginController = new LoginController();
        LoginMenu loginMenu = new LoginMenu(scanner, loginController);
        loginMenu.run();
    }
}