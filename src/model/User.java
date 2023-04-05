package model;

import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User> {
    private String username;
    private String password;
    private int level = 1;
    private int experience = 0;
    private int gold = 100;
    private List<CardTypes> battleDeck = new ArrayList<>();
    private List<CardTypes> cards = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        addCardToCards(CardTypes.BARBARIAN);
        addCardToCards(CardTypes.FIRE_BALL);
        addCardToDeck(CardTypes.BARBARIAN);
        addCardToDeck(CardTypes.FIRE_BALL);
    }

    public List<CardTypes> getCards() {
        return cards;
    }

    public List<CardTypes> getBattleDeck() {
        return battleDeck;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addExperience(int amount) {
        while (experienceNeededToLevelUp() <= amount) {
            amount -= experienceNeededToLevelUp();
            level++;
            experience = 0;
        }
        experience += amount;
    }

    private int experienceNeededToLevelUp() {
        return level * level * 160 - experience;
    }

    @Override
    public int compareTo(User user) {
        if (this.level != user.level) {
            return user.level - this.level;
        }
        if (this.experience != user.experience) {
            return user.experience - this.experience;
        }
        return this.username.compareTo(user.username);
    }

    public void addCardToDeck(CardTypes cardTypes) {
        battleDeck.add(cardTypes);
    }

    public void addCardToCards(CardTypes cardTypes) {
        cards.add(cardTypes);
    }

    public void removeCardFromDeck(CardTypes cardTypes) {
        battleDeck.remove(cardTypes);
    }

    public void removeCardFromCards(CardTypes cardTypes) {
        cards.remove(cardTypes);
    }
}
