package LLD.moviebooking;

public class FrontDesk extends Person{
    private String userName;
    private String password;

    public FrontDesk(String name, String email, String phone, String userName, String password) {
        super(name, email, phone);
        this.userName = userName;
        this.password = password;
    }

    public boolean bookMovie(){
        // Booking logic
        return true;
    };
    public boolean cancelMovie(){
        // Cancellation logic
        return true;
    };
}
