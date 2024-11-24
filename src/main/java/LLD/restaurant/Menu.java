package LLD.restaurant;

import java.util.List;

public class Menu {
    private String menuId;
    private String restaurantName;
    private String description;
    private List<MenuSection> sections;

    public Menu(String menuId, String restaurantName, String description, List<MenuSection> sections) {
        this.menuId = menuId;
        this.restaurantName = restaurantName;
        this.description = description;
        this.sections = sections;
    }
}

