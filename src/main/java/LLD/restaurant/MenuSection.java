package LLD.restaurant;

import java.util.List;

public class MenuSection {
    private String menuSectionId;
    private String sectionName;
    private String description;
    private List<MenuItem> menuItems;

    public MenuSection(String menuSectionId, String sectionName, String description, List<MenuItem> menuItems) {
        this.menuSectionId = menuSectionId;
        this.sectionName = sectionName;
        this.description = description;
        this.menuItems = menuItems;
    }
}
