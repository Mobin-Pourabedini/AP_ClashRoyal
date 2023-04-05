package model;

public enum CardTypes {
    FIRE_BALL("Fireball", 1600, 0, 100),
    HEAL("Heal", 0, 1000, 150),
    BARBARIAN("Barbarian", 900, 2000, 100),
    ICE_WIZARD("Ice Wizard", 1500, 3500, 180),
    BABY_DRAGON("Baby Dragon", 1200, 3300, 200);

    private String type;
    private int damage;
    private int hitpoint;
    private int price;

    CardTypes(String type, int damage, int hitpoint, int price) {
        this.type = type;
        this.damage = damage;
        this.hitpoint = hitpoint;
        this.price = price;
    }

    public Card createCard(User user) {
        switch (type) {
            case "Fireball":
                return new SpellCard(user, 0, damage, 1, type);
            case "Heal":
                return new SpellCard(user, hitpoint, 0, 1, type);
            case "Barbarian":
                return new TroopCard(user, damage, hitpoint, type);
            case "Ice Wizard":
                return new TroopCard(user, damage, hitpoint, type);
            case "Baby Dragon":
                return new TroopCard(user, damage, hitpoint, type);
            default:
                return null;
        }
    }

    public int getPrice() {
        return this.price;
    }
}
