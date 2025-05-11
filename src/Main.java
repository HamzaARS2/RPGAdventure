import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        while (!player.isDead()) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.startsWith("go "))
                handleMovement(input, player);
            else if (input.startsWith("pickup")) {
                for (Item item: player.getCurrentRoom().getItems())
                    player.pickUpItem(item);
                player.getCurrentRoom().getItems().clear();
            } else if (input.startsWith("inventory"))
                player.showInventory();
            else if (input.startsWith("use ")) {
                String itemName = input.substring(4);
                player.useItem(itemName);
            } else if (input.startsWith("attack"))
                attackFirstMonster(player);
            else if (input.equals("exit")) {
                System.out.println("Thanks for playing!");
                break;
            } else
                System.out.println("Unknown command.");
        }
        if (player.isDead())
            System.out.println("Game Over!");
        scanner.close();
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
