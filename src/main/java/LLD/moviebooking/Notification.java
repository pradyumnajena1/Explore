package LLD.moviebooking;

public class Notification {
  private Person person;
  private Booking booking;
  private String content;
  private String id;

  public Notification(Person person, Booking booking, String content, String id) {
    this.person = person;
    this.booking = booking;
    this.content = content;
    this.id = id;
  }

  public boolean sendNotification() {
    return true;
  }
}
