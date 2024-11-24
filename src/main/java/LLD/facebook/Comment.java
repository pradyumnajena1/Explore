package LLD.facebook;

public class Comment {
    private String id;
    private String content;
    private Post post;
    private int totalLikes;
    public Comment(String id, String content, Post post) {
        this.id = id;
        this.content = content;
        this.post = post;
    }
    public int getTotalLikes() {
        return totalLikes;
    }
    public boolean updateContent(String content){
        return false;
    }
}
