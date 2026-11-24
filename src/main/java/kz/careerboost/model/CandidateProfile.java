package kz.careerboost.model;

import java.util.ArrayList;
import java.util.List;

public class CandidateProfile {
    private final String id;
    private final String name;
    private final String profession;
    private final List<String> skills;

    public CandidateProfile(String id, String name, String profession, List<String> skills) {
        this.id = id;
        this.name = name;
        this.profession = profession;
        this.skills = new ArrayList<>(skills);
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getProfession() { return profession; }
    public List<String> getSkills() { return List.copyOf(skills); }
    public void setSkills(List<String> newSkills) {
        if (newSkills == null || newSkills.isEmpty() || newSkills.stream().anyMatch(s -> s == null || s.isBlank()))
            throw new IllegalArgumentException("Дағдылар бос болмауы керек");
        skills.clear();
        skills.addAll(newSkills);
    }
}
