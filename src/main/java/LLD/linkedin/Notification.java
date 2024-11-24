package LLD.linkedin;

public class Notification {
    String message;
    String timestamp;
    Member receivingMember;

    public Notification(String message, String timestamp, Member receivingMember) {
        this.message = message;
        this.timestamp = timestamp;
        this.receivingMember = receivingMember;
    }
    public boolean sendMessage(){
        return false;
    }
}
