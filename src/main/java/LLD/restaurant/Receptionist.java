package LLD.restaurant;

import java.util.List;

public class Receptionist extends Employee{
    public Receptionist(String name, String email, String phoneNumber, String employeeId, String userName, String password) {
        super(name, email, phoneNumber, employeeId, userName, password);
    }

    public void addTable(Table table) { }
    public void removeTable(Table table) { }
    public void updateTableStatus(Table table, TableStatus status) { }
    public List<Table> findTableByCapacity(int capacity) {
        return null;
    }
}
