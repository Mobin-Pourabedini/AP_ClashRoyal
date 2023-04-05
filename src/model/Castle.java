package model;

public class Castle extends Card {
    private int level;
    private int damage;
    private int hitpoint;

    public Castle(User owner, boolean isKing) {
        super(owner, "castle");
        this.level = owner.getLevel();
        this.damage = 500 * owner.getLevel();
        this.hitpoint = isKing ? 3600 * owner.getLevel() : 2500 * owner.getLevel();
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getHitpoint() {
        if (hitpoint <= 0) {
            return -1;
        } else {
            return hitpoint;
        }
    }

    public void setHitpoint(int hitpoint) {
        this.hitpoint = hitpoint;
    }

    public void damage(int amount) {
        this.hitpoint -= amount;
    }

    @Override
    public void act(Card c) {
        return;
    }

    @Override
    public boolean isDead() {
        return hitpoint <= 0;
    }
}
