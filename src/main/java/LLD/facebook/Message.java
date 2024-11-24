package LLD.facebook;

import LLD.parkingLot.User;

public class Message {
    private Member sender;
    private Member receiver;
    private String content;
    public Message(Member sender, Member receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
    public void send(){

    }
}
