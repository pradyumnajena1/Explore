package LLD.facebook;

import java.util.List;

public class Post {
  private String id;
  private String content;
  private Member member;
  private int totalLikes;
  private int totalShares;
  private List<Comment> comments;

  public boolean updateContent(String content) {
    return true;
  }

  ;
}
