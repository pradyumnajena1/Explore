package LLD.moviebooking;

public class Admin extends Person{
    private String userName;
    private String password;

    public Admin(String name, String email, String phone, String userName, String password) {
        super(name, email, phone);
        this.userName = userName;
        this.password = password;
    }

    public boolean addCinema(){
        return true;
    }
    public boolean removeCinema(){
        return true;
    }

    public boolean updateCinema(){
        return true;
    }

    public boolean addShow(){
        return true;
    }
    public boolean removeShow(){
        return true;
    }

    public boolean updateShow(){
        return true;
    }
}
