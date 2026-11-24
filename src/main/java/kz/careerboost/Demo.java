package kz.careerboost;

import java.util.List;
import kz.careerboost.model.*;
import kz.careerboost.patterns.*;

public class Demo {
    public static void main(String[] args) {
        CareerBoostApp app = new CareerBoostApp();
        System.out.println("\n========== CAREERBOOST DEMO ==========");

        System.out.println("\n=== US-01. Тіркелу ===");
        String email = app.register("aliya@mail.kz", "secret123");
        System.out.println("✔ Тіркелді: " + email);

        System.out.println("\n=== US-02. Авторизация ===");
        System.out.println("✔ Кіру нәтижесі: " + app.login(email, "secret123"));

        System.out.println("\n=== US-03. Профиль құру — BUILDER ===");
        CandidateProfile profile = app.createProfile("Әлия", "Java Developer", List.of("Java", "SQL", "Git"));
        System.out.println("✔ Профиль: " + profile.getName() + ", ID: " + profile.getId());

        System.out.println("\n=== US-04. Дағдыларды жаңарту ===");
        profile.setSkills(List.of("Java", "SQL", "Git", "Maven"));
        System.out.println("✔ Дағдылар: " + profile.getSkills());

        System.out.println("\n=== US-05. Вакансия жариялау ===");
        Job javaJob = app.publishJob("Junior Java Developer", "Tech KZ", "Java", 300000);
        app.publishJob("Python Developer", "Digital Lab", "Python", 350000);
        System.out.println("✔ Вакансия жарияланды: " + javaJob.title());

        System.out.println("\n=== US-06. Вакансиялар тізімі ===");
        app.listJobs().forEach(j -> System.out.println("• " + j.title() + " — " + j.company()));

        System.out.println("\n=== US-07. Дағды бойынша сүзу ===");
        System.out.println("Java бойынша: " + app.filterJobs("Java").size() + " вакансия");

        System.out.println("\n=== US-08. Өтінім беру ===");
        Application application = app.apply(profile.getId(), javaJob.id());
        System.out.println("✔ Өтінім жіберілді: " + application.getId());

        System.out.println("\n=== US-09. Өтінімді кері қайтару ===");
        app.withdraw(application.getId());
        System.out.println("✔ Мәртебесі: " + application.getStatus());

        System.out.println("\n=== US-10. Ұсыныстар — STRATEGY ===");
        app.recommend(profile.getId()).forEach(j -> System.out.println("• " + j.title() + " [" + j.requiredSkill() + "]"));

        System.out.println("\n=== US-11. Сұхбат шақыру — ABSTRACT FACTORY ===");
        System.out.println(app.interviewInvite(email, "2026-12-01 14:00", false));
        System.out.println("SMS нұсқасы: " + app.interviewInvite("+77000000000", "2026-12-01 14:00", true));

        System.out.println("\n=== US-12. Профиль есебі — VISITOR ===");
        System.out.println(app.profileReport(profile.getId()));

        System.out.println("\n=== ҚОСЫМША DESIGN PATTERNS ===");
        System.out.println("BUILDER: профиль объектісі кезең-кезеңімен құрылды.");
        System.out.println("ABSTRACT FACTORY: Email және SMS хабарлама отбасылары құрылды.");
        System.out.println("TEMPLATE METHOD: " + new ResumeReviewTemplate.StandardReview().review("Әлия"));
        System.out.println("STRATEGY: SkillMatch алгоритмі вакансияларды сұрыптады.");
        System.out.println("ADAPTER: " + new LegacyResumeAdapter(new LegacyResumeAdapter.LegacyResumeSystem()).readResume("Әлия"));
        System.out.println("VISITOR: " + app.profileReport(profile.getId()));

        System.out.println("\n✔ 12 User Story демонстрациясы аяқталды.");
        System.out.println("✔ 6 Design Pattern іске қосылды.");
    }
}
