package kz.careerboost;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import kz.careerboost.model.*;
import kz.careerboost.patterns.*;

class CareerBoostAppTest {
    @Test void registerAndLogin() {
        CareerBoostApp app = new CareerBoostApp();
        app.register("test@mail.kz", "secret1");
        assertTrue(app.login("test@mail.kz", "secret1"));
        assertFalse(app.login("test@mail.kz", "wrong"));
    }
    @Test void duplicateEmailRejected() {
        CareerBoostApp app = new CareerBoostApp();
        app.register("test@mail.kz", "secret1");
        assertThrows(IllegalArgumentException.class, () -> app.register("test@mail.kz", "secret1"));
    }
    @Test void createProfileAndReport() {
        CareerBoostApp app = new CareerBoostApp();
        CandidateProfile p = app.createProfile("Айжан", "QA", List.of("Java", "SQL"));
        assertTrue(app.profileReport(p.getId()).contains("2"));
    }
    @Test void filterJobsBySkill() {
        CareerBoostApp app = new CareerBoostApp();
        app.publishJob("Java Dev", "Company", "Java", 250000);
        app.publishJob("Python Dev", "Company", "Python", 300000);
        assertEquals(1, app.filterJobs("Java").size());
    }
    @Test void applicationCanBeWithdrawn() {
        CareerBoostApp app = new CareerBoostApp();
        CandidateProfile p = app.createProfile("Айжан", "Developer", List.of("Java"));
        Job j = app.publishJob("Java Dev", "Company", "Java", 250000);
        Application a = app.apply(p.getId(), j.id());
        assertEquals(Application.Status.SENT, a.getStatus());
        app.withdraw(a.getId());
        assertEquals(Application.Status.WITHDRAWN, a.getStatus());
    }
    @Test void recommendationPrioritizesMatchingSkill() {
        CareerBoostApp app = new CareerBoostApp();
        CandidateProfile p = app.createProfile("Айжан", "Developer", List.of("Java"));
        app.publishJob("Python Dev", "A", "Python", 300000);
        app.publishJob("Java Dev", "B", "Java", 250000);
        assertEquals("Java Dev", app.recommend(p.getId()).get(0).title());
    }
    @Test void builderPatternCreatesProfile() {
        var p = new ProfileBuilder().id("1").name("A").profession("Dev").skill("Java").build();
        assertEquals(List.of("Java"), p.getSkills());
    }
    @Test void adapterConvertsLegacyResume() {
        var adapter = new LegacyResumeAdapter(new LegacyResumeAdapter.LegacyResumeSystem());
        assertEquals("LEGACY_RESUME[A]", adapter.readResume("A"));
    }
}
