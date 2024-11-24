package LLD.carrental;

import java.util.Date;

public class Notifications {
    private int id;
    private String message;
    private Date createdOn;
    public Notifications(int id, String message, Date createdOn) {
        this.id = id;
        this.message = message;
        this.createdOn = createdOn;
    }

}
