package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String name;
    private final String description;
    private final List<Item> items;
    private final List<Monster> monsters;
    private Room north, south, east, west;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.monsters = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void setExits(Room north, Room south, Room east, Room west) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public void describeRoom() {
        System.out.println(name);
        System.out.println(description);
        if (!items.isEmpty()) {
            System.out.println("Items in the room:");
            for (Item item: items)
                System.out.println("- " + item.getName());
        }
        if (!monsters.isEmpty()) {
            System.out.println("Monsters in the room:");
            for (Monster monster: monsters)
                System.out.println("- " + monster.getName());
        }
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }

    public List<Item> getItems() { return items; }
    public List<Monster> getMonsters() { return monsters; }

    public Room getNorth() { return north; }
    public Room getSouth() { return south; }
    public Room getEast() { return east; }
    public Room getWest() { return west; }

}
