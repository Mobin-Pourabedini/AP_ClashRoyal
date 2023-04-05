package view;

import controller.GameController;
import model.User;

import java.util.Scanner;

public class GameMenu {
    private Scanner scanner;
    private GameController gameController;

    public GameMenu(Scanner scanner, GameController gameController) {
        this.scanner = scanner;
        this.gameController = gameController;
    }

    public void run() {
        String command = this.scanner.nextLine();

        while (true) {
            if (Commands.getMatcher(command, Commands.SHOW_HITPOINT).matches()) {
                String output = this.gameController.showHitPoint();
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.SHOW_LINE).matches()) {
                String output = this.gameController.showLine(
                        Commands.getMatcher(command, Commands.SHOW_LINE));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.CARDS_TO_PLAY).matches()) {
                String output = this.gameController.cardsToPlay();
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.MOVES_LEFT).matches()) {
                String output = this.gameController.movesLeft();
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.MOVE_TROOP).matches()) {
                String output = this.gameController.moveTroop(
                        Commands.getMatcher(command, Commands.MOVE_TROOP));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.DEPLOY_TROOP).matches()) {
                String output = this.gameController.deployTroop(Commands.getMatcher(command, Commands.DEPLOY_TROOP));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.DEPLOY_HEAL).matches()) {
                String output = this.gameController.deployHeal(Commands.getMatcher(command, Commands.DEPLOY_HEAL));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.DEPLOY_FIRE_BALL).matches()) {
                String output = this.gameController.deployFireBall(Commands.getMatcher(command, Commands.DEPLOY_FIRE_BALL));
                System.out.print(output);
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.NEXT_TURN).matches()) {
                String output = this.gameController.nextTurn();
                System.out.print(output);
                if (output.startsWith("Game has ended.")) {
                    break;
                }
                command = this.scanner.nextLine();
                continue;
            }

            if (Commands.getMatcher(command, Commands.SHOW_CURRENT_MENU).matches()) {
                System.out.print("Game Menu\n");
                command = this.scanner.nextLine();
                continue;
            }

            System.out.print("Invalid command!\n");
            command = this.scanner.nextLine();
        }
    }
}
