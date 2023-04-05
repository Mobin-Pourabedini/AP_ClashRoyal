package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Card {
    protected final User owner;
    protected final String name;
    public static Map<String, CardTypes> stringToCard = new HashMap<>();
    public static Map<CardTypes, String> cardToString = new HashMap<>();

    static {
        stringToCard.put("Fireball", CardTypes.FIRE_BALL);
        stringToCard.put("Heal", CardTypes.HEAL);
        stringToCard.put("Barbarian", CardTypes.BARBARIAN);
        stringToCard.put("Baby Dragon", CardTypes.BABY_DRAGON);
        stringToCard.put("Ice Wizard", CardTypes.ICE_WIZARD);
        cardToString.put(CardTypes.FIRE_BALL, "Fireball");
        cardToString.put(CardTypes.HEAL, "Heal");
        cardToString.put(CardTypes.BARBARIAN, "Barbarian");
        cardToString.put(CardTypes.BABY_DRAGON, "Baby Dragon");
        cardToString.put(CardTypes.ICE_WIZARD, "Ice Wizard");
    }

    public Card(User owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public abstract void act(Card c);

    public abstract boolean isDead();

    @Override
    public String toString() {
        return name + ": " + owner.getUsername();
    }
}
