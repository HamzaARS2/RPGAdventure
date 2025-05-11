package model;

public class Weapon extends Item {

    private final int damageBoost;
    private final boolean twoHanded;

    public Weapon(String name, String description, int damageBoost, boolean twoHanded) {
        super(name, description);
        this.damageBoost = damageBoost;
        this.twoHanded = twoHanded;
    }

    @Override
    public void use(Player player) {
        System.out.println(player.getName() + " equips " + getName() + " (+" + damageBoost + " attack power)");
        player.increaseAttackPower(damageBoost);
    }

    public int getDamageBoost() { return damageBoost; }
}
