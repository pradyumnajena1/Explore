package LLD.facebook;

import java.util.List;

public class Page {
    private Photo coverPhoto;
    private Photo profile;
    private String title;
    private String description;
    private int totalNumUsers;
    private String type;
    private List<Recomendation> recomendations;

    public Page(Photo coverPhoto, Photo profile, String title, String description, String type) {
        this.coverPhoto = coverPhoto;
        this.profile = profile;
        this.title = title;
        this.description = description;
        this.type = type;
    }
    public Photo getCoverPhoto() {
        return coverPhoto;
    }
    public Photo getProfile() {
        return profile;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void addRecomendation(Recomendation aRecomendation) {

    }
}
