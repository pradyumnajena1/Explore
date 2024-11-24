package LLD.moviebooking;

public class EmailNotification extends Notification{
    public EmailNotification(Person person, Booking booking, String content, String id) {
        super(person, booking, content, id);
    }
}
