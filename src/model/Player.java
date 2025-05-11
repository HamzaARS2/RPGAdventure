package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private List<Item> inventory;
    private Room currentRoom;
    private int attackPower;
    private boolean isAlive;

    public Player(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.isAlive = true;
        this.inventory = new ArrayList<Item>();
        this.currentRoom = null;
    }

    public void move(Direction direction) {
        if (currentRoom == null) {
            System.out.println("You are not in any room.");
            return;
        }

        Room nextRoom = switch (direction) {
            case NORTH -> currentRoom.getNorth();
            case SOUTH -> currentRoom.getSouth();
            case EAST -> currentRoom.getEast();
            case WEST -> currentRoom.getWest();
        };
        if (nextRoom != null) {
            setCurrentRoom(nextRoom);
            System.out.println("You moved to: " + nextRoom.getName());
            nextRoom.describeRoom();
        } else
            System.out.println("You can't move in that direction.");
    }

    public void attack(Monster monster) {
        if (!isAlive) return;
        System.out.println(name + " attacks " + monster.getName() + " for " + attackPower + " damage.");
        monster.takeDamage(attackPower);
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            health = 0;
            isAlive = false;
            System.out.println(name + " has died.");
        }
    }

    public void heal(int amount) {
        if (!isAlive) return;
        health += amount;
        if (health > maxHealth) health = maxHealth;
    }
    public void pickUpItem(Item item) {
        inventory.add(item);
        System.out.println("Picked up: " + item.getName());
    }

    public void useItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println("Using: " + item.getName());
                item.use(this);
                inventory.remove(i);
                return;
            }
        }
        System.out.println("Item not found: " + itemName);
    }
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Inventory:");
        for (Item item: inventory)
            System.out.println("- " + item.getName());
    }

    public void increaseAttackPower(int boost) {
        this.attackPower += boost;
    }

    public void combat(Monster monster) {
        System.out.println("Combat started: " + name + " vs " + monster.getName());
        while (monster.isAlive() && isAlive) {
            attack(monster);
            if (monster.isAlive())
                monster.attack(this);
        }
        if (!isAlive)
            System.out.println(name + " has been defeated in combat.");
    }

    public boolean isDead(){return !isAlive;}

    // Getters
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getAttackPower() { return attackPower; }
    public Room getCurrentRoom() { return currentRoom; }
    public List<Item> getInventory() { return inventory; }

    // Setters
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
}
