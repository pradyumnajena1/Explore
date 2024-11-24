package LLD.facebook;

import java.util.List;

public class Profile {
    private List<WorkExperience> workExperiences;
    private List<Education> education;
    private List<Place> places;

    public Profile(List<WorkExperience> workExperiences, List<Education> education, List<Place> places) {
        this.workExperiences = workExperiences;
        this.education = education;
        this.places = places;
    }
}
