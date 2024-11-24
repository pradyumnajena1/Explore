package LLD.moviebooking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Show {
    private String showId;
    private CinemaHall cinemaHall;
    private Movie movie;
    private Date date;
    private Date startTime;
    private Date endTime;
    private List<ShowSeat> seats = new ArrayList<>();



    public Show(String showId, CinemaHall cinemaHall, Movie movie, Date date, Date startTime, Date endTime ) {
        this.showId = showId;
        this.cinemaHall = cinemaHall;
        this.movie = movie;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public List<ShowSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<ShowSeat> seats) {
        this.seats = seats;
    }
}
