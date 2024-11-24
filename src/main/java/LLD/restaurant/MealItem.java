package LLD.restaurant;


import java.awt.*;

public class MealItem {
    private String mealItemId;
    private MenuItem menuItem;
    private int quantity;
    public MealItem(String mealItemId, MenuItem menuItem, int quantity) {
        this.mealItemId = mealItemId;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }
}
