package LLD.linkedin;





import LLD.facebook.Recomendation;

import java.util.List;

public class Profile {
    private String profileId;

    private ProfileStats profileStats;
    private List<WorkExperience> workExperiences;
    private List<Skill> skills;
    private List<Education> educations;
    private List<Recomendation> recomendations;
    public void addSkill(Skill skill) {}
    public void addEducation(Education education) {}
    public void addWorkExperience(WorkExperience experience) {}
}
