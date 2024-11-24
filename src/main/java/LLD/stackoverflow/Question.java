package LLD.stackoverflow;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {
    private String id;
    private String title;
    private String content;
    private int upVotes;
    private int downVotes;
    private int viewCount;
    private Date creationTime;
    private Date updateTime;
    private QuestionStatus status;
    private ClosingRemark closingRemark;

    private List<Comment> comments = new ArrayList<Comment>();
    private Answer answer;
    private Photo photo;

    public void close() {}
    public void undelete() {}
}
