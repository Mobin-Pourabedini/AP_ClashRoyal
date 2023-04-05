package model;

public class SpellCard extends Card {
    private int heal;
    private int damage;
    private int remainingTurns;

    public SpellCard(User owner, int heal, int damage, int remainingTurns, String name) {
        super(owner, name);
        this.heal = heal;
        this.damage = damage;
        this.remainingTurns = remainingTurns;
    }

    @Override
    public void act(Card card) {
        if (card instanceof SpellCard) return;
        if (card instanceof TroopCard) {
            TroopCard troopCard = (TroopCard) card;
            if (this.owner == troopCard.owner) {
                troopCard.heal(this.heal);
            }
        }
        if (card instanceof Castle) {
            Castle castle = (Castle) card;
            if (castle.owner != this.owner) {
                castle.damage(this.damage);
            }
        }
    }

    @Override
    public boolean isDead() {
        this.remainingTurns--;
        return remainingTurns < 0;
    }
}
