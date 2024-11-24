package LLD.restaurant;

import java.util.List;

public class RestaurantBranch {
    private String name;
    private Address address;
    private List<Employee> employees;
    private Menu menu;

    public RestaurantBranch(String name, Address address, List<Employee> employees, Menu menu) {
        this.name = name;
        this.address = address;
        this.employees = employees;
        this.menu = menu;
    }
}
