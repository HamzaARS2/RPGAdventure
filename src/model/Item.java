package model;

public abstract class Item {
    private final String name;
    private final String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void use(Player player);

    public String getName() { return name; }
    public String getDescription() { return description; }

}
