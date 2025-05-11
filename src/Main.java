import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Room entrance = new Room("Entrance", "The grand entrance of the dungeon.");
        Room armory = new Room("Armory", "Old rusty weapons are scattered around");
        Room dungeon = new Room("Dungeon", "It's dark. You feel a chill run down your spine.");

        entrance.setExits(null, armory, null, null);
        armory.setExits(entrance, dungeon, null, null);
        dungeon.setExits(armory, null, null, null);

        entrance.addItem(new HealingPotion("Small Potion","Small healing potion", 40));
        armory.addItem(new Weapon("Rusty Sword", "An old rusted sword", 5, false));

        dungeon.addMonster(new Monster("Goblin", 30, 8));
        dungeon.addMonster(new Monster("Armored Goblin", 80, 12));

        Player player = new Player("Hero", 100, 15);
        player.setCurrentRoom(entrance);

        System.out.println("Welcome to RPG Adventure!");
        player.getCurrentRoom().describeRoom();

        System.out.println("Initial attack power: " + player.getAttackPower());

        Weapon sword = new Weapon("Iron Sword", "A sharp iron sword", 15, false);
        Weapon axe = new Weapon("Battle Axe", "Heavy two-handed axe", 25, true);

        // Pick up and use the sword
        player.pickUpItem(sword);
        player.useItem("Iron Sword");
        System.out.println("Attack power after equipping sword: " + player.getAttackPower());

        // Pick up and use the axe
        player.pickUpItem(axe);
        player.useItem("Battle Axe");
        System.out.println("Attack power after equipping axe: " + player.getAttackPower());

        // Create a monster and attack
        Monster goblin = new Monster("Goblin", 30, 5);
        player.attack(goblin);

    }

    private static void handleMovement(String input, Player player) {
        String directionStr = input.substring(3).toUpperCase();
        try {
            Direction dir = Direction.valueOf(directionStr);
            player.move(dir);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid direction. Try north, south, east, or west.");
        }
    }

    private static void attackFirstMonster(Player player) {
        for (Monster monster: player.getCurrentRoom().getMonsters()) {
            player.combat(monster);
        }
        System.out.println("There are no monsters to attack.");
    }
}
