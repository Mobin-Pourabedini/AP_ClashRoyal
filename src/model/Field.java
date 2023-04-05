package model;

import controller.GameController;

public class Field {
    private User hostUser, guestUser;
    private FieldLine leftFieldLine, rightFieldLine, middleFieldLine;

    public Field(User hostUser, User guestUser) {
        this.hostUser = hostUser;
        this.guestUser = guestUser;
        this.leftFieldLine =
                new FieldLine(new Castle(hostUser, false), new Castle(guestUser, false));
        this.rightFieldLine =
                new FieldLine(new Castle(hostUser, false), new Castle(guestUser, false));
        this.middleFieldLine =
                new FieldLine(new Castle(hostUser, true), new Castle(guestUser, true));
    }

    public FieldLine getLeftFieldLine() {
        return leftFieldLine;
    }

    public FieldLine getRightFieldLine() {
        return rightFieldLine;
    }

    public FieldLine getMiddleFieldLine() {
        return middleFieldLine;
    }

    public void addCard(Card card, String direction, int y) {
        if (direction.equals("left")) {
            leftFieldLine.addCard(card, y);
        } else if (direction.equals("right")) {
            rightFieldLine.addCard(card, y);
        } else {
            middleFieldLine.addCard(card, y);
        }
    }

    public void removeDeadCards() {
        leftFieldLine.removeDeadCards();
        middleFieldLine.removeDeadCards();
        rightFieldLine.removeDeadCards();
    }

    public void act() {
        leftFieldLine.act();
        middleFieldLine.act();
        rightFieldLine.act();
    }

    public void removeCard(Card card, String direction, int y) {
        if (direction.equals("left")) {
            leftFieldLine.removeCard(card, y);
        } else if (direction.equals("right")) {
            rightFieldLine.removeCard(card, y);
        } else {
            middleFieldLine.removeCard(card, y);
        }
    }

    public FieldLine getFieldLineByDirection(String lineDirection) {
        if (lineDirection.equals("left")) {
            return leftFieldLine;
        } else if (lineDirection.equals("right")) {
            return rightFieldLine;
        } else {
            return middleFieldLine;
        }
    }

    public boolean isDefeated(User user) {
        return leftFieldLine.isCastleDead(user) &&
                rightFieldLine.isCastleDead(user) &&
                middleFieldLine.isCastleDead(user);
    }

    public int sumOfHitPoint(User user) {
        int result = 0;
        if (!leftFieldLine.isCastleDead(user)) result += leftFieldLine.getCaslteHitpoint(user);
        if (!rightFieldLine.isCastleDead(user)) result += rightFieldLine.getCaslteHitpoint(user);
        if (!middleFieldLine.isCastleDead(user)) result += middleFieldLine.getCaslteHitpoint(user);
        return result;
    }

    public int numberOfDestroyedCastles(User user) {
        int result = 0;
        if (middleFieldLine.isCastleDead(user)) result++;
        if (leftFieldLine.isCastleDead(user)) result++;
        if (rightFieldLine.isCastleDead(user)) result++;
        return result;
    }
}
