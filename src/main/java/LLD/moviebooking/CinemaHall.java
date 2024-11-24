package LLD.moviebooking;

import java.util.List;

public class CinemaHall {
    private String name;
    private int seatingCapacity;

    private List<CinemaHallSeat> seats;

    public CinemaHall(String name, int seatingCapacity, List<CinemaHallSeat> seats) {
        this.name = name;
        this.seatingCapacity = seatingCapacity;
        this.seats = seats;
    }
}
