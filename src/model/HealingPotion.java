package model;

public class HealingPotion extends Item {

    private final int healAmount;

    public HealingPotion(String name, String description, int healAmount) {
        super(name, description);
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player player) {
        if (player.isDead()) {
            System.out.println("Cannot use potion. " + player.getName() + " is dead.");
            return;
        }
        int before = player.getHealth();
        player.heal(healAmount);
        int after = player.getHealth();
        System.out.println(player.getName() + " used " + getName() + " and healed from " + before + " to " + after + ".");
    }
}
