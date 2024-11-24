package LLD.moviebooking;

public class SMSNotification extends Notification {
  public SMSNotification(Person person, Booking booking, String content, String id) {
    super(person, booking, content, id);
  }
}
