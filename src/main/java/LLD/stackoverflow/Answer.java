package LLD.stackoverflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer {
    private String id;
    private String content;
    private Photo photo;
    private Date createdTime;
    private int voteCount;
    private int flagCount;

    private List<Comment> comments = new ArrayList<>();

    public Answer(String id, String content) {
        this.id = id;
        this.content = content;
    }
    public void incrementVoteCount() {}
    public void incrementFlagCount() {}
}
