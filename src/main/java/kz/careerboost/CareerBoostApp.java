package kz.careerboost;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import kz.careerboost.model.*;
import kz.careerboost.patterns.*;

public class CareerBoostApp {
    private final Map<String, String> users = new HashMap<>();
    private final Map<String, CandidateProfile> profiles = new LinkedHashMap<>();
    private final Map<String, Job> jobs = new LinkedHashMap<>();
    private final Map<String, Application> applications = new LinkedHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(1);

    private String id() { return "CB-" + ids.getAndIncrement(); }
    public String register(String email, String password) {
        if (email == null || !email.contains("@") || password == null || password.length() < 6)
            throw new IllegalArgumentException("Email немесе құпиясөз талапқа сай емес");
        if (users.containsKey(email)) throw new IllegalArgumentException("Бұл email тіркелген");
        users.put(email, password); return email;
    }
    public boolean login(String email, String password) {
        return users.containsKey(email) && Objects.equals(users.get(email), password);
    }
    public CandidateProfile createProfile(String name, String profession, List<String> skills) {
        CandidateProfile p = new ProfileBuilder().id(id()).name(name).profession(profession).build();
        if (skills != null && !skills.isEmpty()) p.setSkills(skills);
        profiles.put(p.getId(), p); return p;
    }
    public Job publishJob(String title, String company, String skill, int salary) {
        Job j = new Job(id(), title, company, skill, salary); jobs.put(j.id(), j); return j;
    }
    public List<Job> listJobs() { return List.copyOf(jobs.values()); }
    public List<Job> filterJobs(String skill) {
        return jobs.values().stream().filter(j -> j.requiredSkill().equalsIgnoreCase(skill)).toList();
    }
    public Application apply(String candidateId, String jobId) {
        if (!profiles.containsKey(candidateId) || !jobs.containsKey(jobId))
            throw new IllegalArgumentException("Кандидат немесе вакансия табылмады");
        boolean duplicate = applications.values().stream().anyMatch(a ->
                a.getCandidateId().equals(candidateId) && a.getJobId().equals(jobId) && a.getStatus() == Application.Status.SENT);
        if (duplicate) throw new IllegalArgumentException("Өтінім бұрын жіберілген");
        Application a = new Application(id(), candidateId, jobId); applications.put(a.getId(), a); return a;
    }
    public Application withdraw(String applicationId) {
        Application a = applications.get(applicationId);
        if (a == null) throw new IllegalArgumentException("Өтінім табылмады");
        a.withdraw(); return a;
    }
    public List<Job> recommend(String candidateId) {
        CandidateProfile p = profiles.get(candidateId);
        if (p == null) throw new IllegalArgumentException("Профиль табылмады");
        return new RecommendationStrategy.SkillMatch().recommend(p, listJobs());
    }
    public String interviewInvite(String email, String date, boolean useSms) {
        NotificationFactory factory = useSms ? NotificationFactory.sms() : NotificationFactory.email();
        return factory.createNotification().send(email, "Сұхбат уақыты: " + date);
    }
    public String profileReport(String candidateId) {
        CandidateProfile p = profiles.get(candidateId);
        if (p == null) throw new IllegalArgumentException("Профиль табылмады");
        return new ProfileVisitor.SkillCountVisitor().visit(p);
    }
    public CandidateProfile getProfile(String id) { return profiles.get(id); }
    public List<Application> getApplications() { return List.copyOf(applications.values()); }
}
