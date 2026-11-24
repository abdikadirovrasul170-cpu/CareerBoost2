package kz.careerboost.patterns;

import java.util.Comparator;
import java.util.List;
import kz.careerboost.model.CandidateProfile;
import kz.careerboost.model.Job;

// STRATEGY: вакансияны бағалау алгоритмін ауыстыруға болады.
public interface RecommendationStrategy {
    List<Job> recommend(CandidateProfile profile, List<Job> jobs);

    class SkillMatch implements RecommendationStrategy {
        public List<Job> recommend(CandidateProfile profile, List<Job> jobs) {
            return jobs.stream()
                .sorted(Comparator.comparing((Job j) ->
                    profile.getSkills().stream().anyMatch(s -> s.equalsIgnoreCase(j.requiredSkill()))).reversed())
                .toList();
        }
    }
}
