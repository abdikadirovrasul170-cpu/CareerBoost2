package kz.careerboost.patterns;

import java.util.ArrayList;
import java.util.List;
import kz.careerboost.model.CandidateProfile;

// BUILDER: профильді кезең-кезеңімен құрастыру.
public class ProfileBuilder {
    private String id, name, profession;
    private final List<String> skills = new ArrayList<>();
    public ProfileBuilder id(String v) { id = v; return this; }
    public ProfileBuilder name(String v) { name = v; return this; }
    public ProfileBuilder profession(String v) { profession = v; return this; }
    public ProfileBuilder skill(String v) { if (v != null && !v.isBlank()) skills.add(v); return this; }
    public CandidateProfile build() {
        if (id == null || name == null || profession == null) throw new IllegalStateException("Профильдің міндетті өрістері толтырылмаған");
        return new CandidateProfile(id, name, profession, skills);
    }
}
