package model;

import java.util.ArrayList;
import java.util.List;

public class FieldLine {
    private List<List<Card>> cards;
    private Castle hostCastle, guestCastle;

    public FieldLine(Castle hostCastle, Castle guestCastle) {
        this.hostCastle = hostCastle;
        this.guestCastle = guestCastle;
        cards = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            cards.add(new ArrayList<>());
        }
    }

    public Castle getCastle(User user) {
        if (user == hostCastle.owner) {
            return hostCastle;
        } else {
            return guestCastle;
        }
    }

    public void addCard(Card card, int y) {
        cards.get(y).add(card);
    }

    public void removeCard(Card card, int y) {
        cards.get(y).remove(card);
    }

    public void moveCard(Card card, String direction, int y) {
        if (direction.equals("upward")) {
            cards.get(y).remove(card);
            cards.get(y + 1).add(card);
        } else {
            cards.get(y).remove(card);
            cards.get(y - 1).add(card);
        }
    }

    public void act() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < cards.get(i).size(); j++) {
                Card card = cards.get(i).get(j);
                if (i == 0) {
                    card.act(hostCastle);
                }
                if (i == 14) {
                    card.act(guestCastle);
                }
                for (int k = 0; k < j; k++) {
                    card.act(cards.get(i).get(k));
                }
            }
        }
    }

    public void removeDeadCards() {
        for (int i = 0; i < 15; i++) {
            cards.get(i).removeIf(Card::isDead);
        }
    }

    public boolean isCastleDead(User user) {
        if (user == hostCastle.owner) {
            return hostCastle.isDead();
        }
        return guestCastle.isDead();
    }

    public int getCaslteHitpoint(User user) {
        if (user == hostCastle.owner) {
            return hostCastle.getHitpoint();
        }
        return guestCastle.getHitpoint();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < cards.get(i).size(); j++) {
                result = result.concat("row " + (i + 1) + ": " + cards.get(i).get(j).toString() + "\n");
            }
        }
        return result;
    }

    public boolean hasTroop(User user, int y) {
        for (Card card : cards.get(y)) {
            if (card instanceof TroopCard) {
                TroopCard troopCard = (TroopCard) card;
                if (troopCard.owner == user) {
                    return true;
                }
            }
        }
        return false;
    }

    public String moveTroop(User user, String direction, int y) {
        for (Card card : cards.get(y)) {
            if (card instanceof TroopCard) {
                TroopCard troopCard = (TroopCard) card;
                if (troopCard.owner == user) {
                    moveCard(troopCard, direction, y);
                    return troopCard.name;
                }
            }
        }
        return null;
    }
}
