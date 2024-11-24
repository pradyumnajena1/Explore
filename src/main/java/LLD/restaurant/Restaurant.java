package LLD.restaurant;

import java.util.List;

public class Restaurant {
    private String name;
    private List<RestaurantBranch> branches;

    public Restaurant(String name, List<RestaurantBranch> branches) {
        this.name = name;
        this.branches = branches;
    }
}
