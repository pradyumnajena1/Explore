package LLD.restaurant;

import java.util.List;

public class Table {
    private String tableId;
    private String location;
    private int maxCapacity;
    private TableStatus status;
    private List<TableSeat> seats;

    public Table(String tableId, String location, int maxCapacity, TableStatus status) {
        this.tableId = tableId;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.status = status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }
}
