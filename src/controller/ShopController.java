package controller;

import model.Card;
import model.CardTypes;
import model.User;

import java.util.regex.Matcher;

public class ShopController {
    private User user;

    public ShopController(User user) {
        this.user = user;
    }

    public String buyCard(Matcher matcher) {
        matcher.matches();
        String cardName = matcher.group("cardName");

        if (!Card.stringToCard.containsKey(cardName)) {
            return "Invalid card name!\n";
        }
        CardTypes cardTypes = Card.stringToCard.get(cardName);

        if (user.getGold() < cardTypes.getPrice()) {
            return "Not enough gold to buy " + Card.cardToString.get(cardTypes) + "!\n";
        }

        if (user.getCards().contains(cardTypes)) {
            return "You have this card!\n";
        }

        user.addCardToCards(cardTypes);
        user.setGold(user.getGold() - cardTypes.getPrice());
        return "Card " + cardName + " bought successfully!\n";
    }

    public String sellCard(Matcher matcher) {
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
            return "You cannot sell a card from your battle deck!\n";
        }

        user.removeCardFromCards(cardTypes);
        user.setGold(user.getGold() + (cardTypes.getPrice() * 4) / 5);
        return "Card " + cardName + " sold successfully!\n";
    }
}
