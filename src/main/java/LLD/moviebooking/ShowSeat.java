package LLD.moviebooking;

public class ShowSeat extends CinemaHallSeat{
    private int seatNumber;
    private double price;
    private boolean booked;
    private Show show;

    public ShowSeat(int row, int column, SeatType type, int seatNumber, double price, Show show) {
        super(row, column, type);
        this.seatNumber = seatNumber;
        this.price = price;
        this.show = show;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }
}
