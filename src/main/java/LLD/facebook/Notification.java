package LLD.facebook;

import LLD.parkingLot.User;

public class Notification {
    String content;
    private String id;
    private Member recipient;

    public Notification(String content, String id, Member recipient) {
        this.content = content;
        this.id = id;
        this.recipient = recipient;
    }

    public boolean send(){
        return true;
    }
}
