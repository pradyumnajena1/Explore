package LLD.linkedin;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String id;
    private String name;
    private Address address;
    private String description;
    private String type;
    private List<JobPosting> postings;

    public Company(String id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        postings = new ArrayList<JobPosting>();
    }
    public void addJobPosting(JobPosting posting) {
        postings.add(posting);
    }

}
