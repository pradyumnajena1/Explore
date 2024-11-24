package LLD.moviebooking;

public class CinemaHallSeat {
    private int row;
    private int column;
    private SeatType type;

    public CinemaHallSeat(int row, int column, SeatType type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }
}
