package model;

public class Monster {
    private final String name;
    private int health;
    private int attackPower;
    private boolean isAlive;

    public Monster(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.isAlive = true;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            isAlive = false;
            System.out.println(name + " has been defeated!");
        }
    }

    public void attack(Player player) {
        if (!isAlive) return;
        System.out.println(name + " attacks " + player.getName() + " for " + attackPower + " damage.");
        player.takeDamage(attackPower);
    }

    public boolean isAlive() { return isAlive; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }
}
