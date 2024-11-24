package LLD.restaurant;

public class MenuItem {
    private String menuItemId;
    private String name;
    private double price;
    private String description;

    public MenuItem(String menuItemId, String name, double price, String description) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
