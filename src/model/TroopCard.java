package model;

public class TroopCard extends Card {
    private final int damage;
    private int hitpoint;

    public TroopCard(User owner, int damage, int hitpoint, String name) {
        super(owner, name);
        this.damage = damage;
        this.hitpoint = hitpoint;
    }

    @Override
    public boolean isDead() {
        return hitpoint <= 0;
    }

    public void damage(int amount) {
        hitpoint -= amount;
    }

    public void heal(int amount) {
        hitpoint += amount;
    }

    @Override
    public void act(Card card) {
        if (card instanceof SpellCard) return;
        if (card instanceof TroopCard) {
            TroopCard troopCard = (TroopCard) card;
            if (this.damage > troopCard.damage && this.owner != troopCard.owner) {
                troopCard.damage(this.damage - troopCard.damage);
            } else {
                this.damage(troopCard.damage - this.damage);
            }
        }
        if (card instanceof Castle) {
            Castle castle = (Castle) card;
            if (castle.owner != this.owner) {
                this.damage(castle.getDamage());
                castle.damage(this.damage);
            }
        }
    }
}
