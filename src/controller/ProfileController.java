package controller;

import model.Card;
import model.CardTypes;
import model.ClashRoyal;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class ProfileController {
    private User user;

    public ProfileController(User user) {
        this.user = user;
    }

    public String changePassword(Matcher matcher) {
        matcher.matches();
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");

        if (!user.getPassword().equals(oldPassword)) {
            return "Incorrect password!\n";
        }

        if (!MyUtilities.isPasswordValid(newPassword)) {
            return "Incorrect format for new password!\n";
        }

        user.setPassword(newPassword);
        return "Password changed successfully!\n";
    }

    public String getInfo() {
        String result = "";
        result = result.concat("username: " + user.getUsername() + "\n");
        result = result.concat("password: " + user.getPassword() + "\n");
        result = result.concat("level: " + user.getLevel() + "\n");
        result = result.concat("experience: " + user.getExperience() + "\n");
        result = result.concat("gold: " + user.getGold() + "\n");
        result = result.concat("rank: " + ClashRoyal.getRank(user) + "\n");
        return result;
    }

    public String removeCard(Matcher matcher) {
        matcher.matches();
        String cardName = matcher.group("cardName");

        if (!Card.stringToCard.containsKey(cardName)) {
            return "Invalid card name!\n";
        }
        CardTypes cardTypes = Card.stringToCard.get(cardName);
        if (!user.getBattleDeck().contains(cardTypes)) {
            return "This card isn't in your battle deck!\n";
        }

        if (user.getBattleDeck().size() == 1) {
            return "Invalid action: your battle deck will be empty!\n";
        }

        user.removeCardFromDeck(cardTypes);
        return "Card " + cardName + " removed successfully!\n";
    }

    public String addCard(Matcher matcher) {
        matcher.matches();
        String cardName = matcher.group("cardName");

        if (!Card.stringToCard.containsKey(cardName)) {
            return "Invalid card name!\n";
        }
        CardTypes cardTypes = Card.stringToCard.get(cardName);
        if (!user.getCards().contains(cardTypes)) {
            return "You don't have this card!\n";
        }
        if (user.getBattleDeck().contains(cardTypes)) {
            return "This card is already in your battle deck!\n";
        }
        if (user.getBattleDeck().size() == 4) {
            return "Invalid action: your battle deck is full!\n";
        }

        user.addCardToDeck(cardTypes);
        return "Card " + cardName + " added successfully!\n";
    }

    public String showCard() {
        String result = "";
        List<String> names = user.getBattleDeck().stream()
                .map(cardTypes -> Card.cardToString.get(cardTypes)).sorted().collect(Collectors.toList());
        for (String name : names) {
            result = result.concat(name + "\n");
        }
        return result;
    }
}
