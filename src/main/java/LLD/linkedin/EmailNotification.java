package LLD.linkedin;

public class EmailNotification extends Notification {
  public EmailNotification(String message, String timestamp, Member receivingMember) {
    super(message, timestamp, receivingMember);
  }
}
