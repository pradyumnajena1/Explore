package LLD.linkedin;

import java.util.Date;

public class ConnectionInvitation {
    private Member sender;
    private Member recipient;
    private Date creationDate;
    private InvitationStatus status;

    public ConnectionInvitation(Member sender, Member recipient, Date creationDate) {
        this.sender = sender;
        this.recipient = recipient;
        this.creationDate = creationDate;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
