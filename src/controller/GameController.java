package controller;

import model.*;

import java.util.Arrays;
import java.util.Formatter;
import java.util.regex.Matcher;

public class GameController {
    private User hostUser;
    private User guestUser;
    private Field field;
    private int turn = 1;
    private int totalTurns;

    private int remainingCardToPlay = 1;
    private int remainingMoves = 3;

    public GameController(User hostUser, User guestUser, int totalTurns) {
        this.hostUser = hostUser;
        this.guestUser = guestUser;
        this.totalTurns = totalTurns;
        this.field = new Field(hostUser, guestUser);
    }

    public String showHitPoint() {
        User opponent = (turn % 2 == 1) ? guestUser : hostUser;
        String result = "";
        result = result.concat("middle castle: "
                + field.getMiddleFieldLine().getCaslteHitpoint(opponent) + "\n");
        result = result.concat("left castle: "
                + field.getLeftFieldLine().getCaslteHitpoint(opponent) + "\n");
        result = result.concat("right castle: "
                + field.getRightFieldLine().getCaslteHitpoint(opponent) + "\n");
        return result;
    }

    public String showLine(Matcher matcher) {
        matcher.matches();
        String direction = matcher.group("direction");

        switch (direction) {
            case "left":
                return "left line:\n" + field.getLeftFieldLine().toString();
            case "right":
                return "right line:\n" + field.getRightFieldLine().toString();
            case "middle":
                return "middle line:\n" + field.getMiddleFieldLine().toString();
            default:
                return "Incorrect line direction!\n";
        }
    }

    public String cardsToPlay() {
        return "You can play " + remainingCardToPlay + " cards more!\n";
    }


    public String movesLeft() {
        return "You have " + remainingMoves + " moves left!\n";
    }

    public String moveTroop(Matcher matcher) {
        matcher.matches();
        String lineDirection = matcher.group("lineDirection");
        String direction = matcher.group("direction");
        int y = Integer.parseInt(matcher.group("rowNumber"));

        if (!Arrays.asList("left", "right", "middle").contains(lineDirection)) {
            return "Incorrect line direction!\n";
        }
        if (y > 15 || y < 1) {
            return "Invalid row number!\n";
        }
        y--;
        if (!Arrays.asList("upward", "downward").contains(direction)) {
            return "you can only move troops upward or downward!\n";
        }
        if (remainingMoves == 0) {
            return "You are out of moves!\n";
        }
        User currentPlayer = (turn % 2 == 1) ? hostUser : guestUser;
        FieldLine fieldLineByDirection = field.getFieldLineByDirection(lineDirection);
        if (!fieldLineByDirection.hasTroop(currentPlayer, y)) {
            return "You don't have any troops in this place!\n";
        }
        if (y == 0 && direction.equals("downward") || y == 14 && direction.equals("upward")) {
            return "Invalid move!\n";
        }
        String cardName = fieldLineByDirection.moveTroop(currentPlayer, direction, y);
        int newY = (direction.equals("upward")) ? y + 1 : y - 1;
        remainingMoves--;
        return new Formatter().format("%s moved successfully to row %d in line %s\n",
                cardName, newY + 1, lineDirection).toString();
    }

    public String deployTroop(Matcher matcher) {
        matcher.matches();
        String lineDirection = matcher.group("lineDirection");
        String rowNumber = matcher.group("rowNumber");
        String cardName = matcher.group("cardName");
        int y = Integer.parseInt(rowNumber);

        if (!Arrays.asList("Barbarian", "Ice Wizard", "Baby Dragon").contains(cardName)) {
            return "Invalid troop name!\n";
        }
        CardTypes cardTypes = Card.stringToCard.get(cardName);
        User currentPlayer = (turn % 2 == 1) ? hostUser : guestUser;
        if (!currentPlayer.getBattleDeck().contains(cardTypes)) {
            return "You don't have " + cardName + " card in your battle deck!\n";
        }
        if (!Arrays.asList("left", "right", "middle").contains(lineDirection)) {
            return "Incorrect line direction!\n";
        }
        if (y > 15 || y < 1) {
            return "Invalid row number!\n";
        }
        if (currentPlayer == hostUser && y > 4 || currentPlayer == guestUser && y < 12) {
            return "Deploy your troops near your castles!\n";
        }
        y--;
        if (remainingCardToPlay <= 0) {
            return "You have deployed a troop or spell this turn!\n";
        }
        FieldLine fieldLineByDirection = field.getFieldLineByDirection(lineDirection);
        fieldLineByDirection.addCard(cardTypes.createCard(currentPlayer), y);
        remainingCardToPlay--;
        return "You have deployed " + cardName + " successfully!\n";
    }

    public String deployHeal(Matcher matcher) {
        matcher.matches();
        String lineDirection = matcher.group("lineDirection");
        String rowNumber = matcher.group("rowNumber");
        int y = Integer.parseInt(rowNumber);

        if (!Arrays.asList("left", "right", "middle").contains(lineDirection)) {
            return "Incorrect line direction!\n";
        }
        User currentPlayer = (turn % 2 == 1) ? hostUser : guestUser;
        if (!currentPlayer.getBattleDeck().contains(CardTypes.HEAL)) {
            return "You don't have Heal card in your battle deck!\n";
        }
        if (y > 15 || y < 1) {
            return "Invalid row number!\n";
        }
        y--;
        if (remainingCardToPlay <= 0) {
            return "You have deployed a troop or spell this turn!\n";
        }
        FieldLine fieldLineByDirection = field.getFieldLineByDirection(lineDirection);
        fieldLineByDirection.addCard(CardTypes.HEAL.createCard(currentPlayer), y);
        remainingCardToPlay--;
        return "You have deployed Heal successfully!\n";
    }

    public String deployFireBall(Matcher matcher) {
        matcher.matches();
        String lineDirection = matcher.group("lineDirection");

        if (!Arrays.asList("left", "right", "middle").contains(lineDirection)) {
            return "Incorrect line direction!\n";
        }
        User currentPlayer = (turn % 2 == 1) ? hostUser : guestUser;
        if (!currentPlayer.getBattleDeck().contains(CardTypes.FIRE_BALL)) {
            return "You don't have Fireball card in your battle deck!\n";
        }
        if (remainingCardToPlay <= 0) {
            return "You have deployed a troop or spell this turn!\n";
        }
        FieldLine fieldLineByDirection = field.getFieldLineByDirection(lineDirection);
        User opponent = (turn % 2 == 1) ? guestUser : hostUser;
        if (fieldLineByDirection.isCastleDead(opponent)) {
            return "This castle is already destroyed!\n";
        }

        fieldLineByDirection.getCastle(opponent).damage(1600);
        remainingCardToPlay--;
        return "You have deployed Fireball successfully!\n";
    }

    public String nextTurn() {
        if (turn % 2 == 0) {
            field.act();
            field.removeDeadCards();
            boolean defeatedHost = field.isDefeated(hostUser);
            boolean defeatedGuest = field.isDefeated(guestUser);
            if (defeatedGuest || defeatedHost) {
                updateResults();
            }
            if (defeatedGuest && defeatedHost) {
                return "Game has ended. Result: Tie\n";
            }
            if (defeatedGuest) {
                return "Game has ended. Winner: " + hostUser.getUsername() + "\n";
            }
            if (defeatedHost) {
                return "Game has ended. Winner: " + guestUser.getUsername() + "\n";
            }
            if (turn / 2 == totalTurns) {
                updateResults();
                if (field.sumOfHitPoint(hostUser) > field.sumOfHitPoint(guestUser)) {
                    return "Game has ended. Winner: " + hostUser.getUsername() + "\n";
                } else if (field.sumOfHitPoint(hostUser) < field.sumOfHitPoint(guestUser)) {
                    return "Game has ended. Winner: " + guestUser.getUsername() + "\n";
                } else {
                    return "Game has ended. Result: Tie\n";
                }
            }
        }
        turn++;
        remainingMoves = 3;
        remainingCardToPlay = 1;
        User currentUser = (turn % 2 == 1) ? hostUser : guestUser;
        return "Player " + currentUser.getUsername() + " is now playing!\n";
    }

    public void updateResults() {
        hostUser.addExperience(field.sumOfHitPoint(hostUser));
        guestUser.addExperience(field.sumOfHitPoint(guestUser));
        hostUser.setGold(hostUser.getGold() +
                field.numberOfDestroyedCastles(guestUser) * 25);
        guestUser.setGold(guestUser.getGold() +
                field.numberOfDestroyedCastles(hostUser) * 25);
    }
}
