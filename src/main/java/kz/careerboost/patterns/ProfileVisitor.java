package kz.careerboost.patterns;

import kz.careerboost.model.CandidateProfile;

// VISITOR: профиль құрылымын өзгертпей есептеу операциясын қосады.
public interface ProfileVisitor {
    String visit(CandidateProfile profile);
    class SkillCountVisitor implements ProfileVisitor {
        public String visit(CandidateProfile profile) {
            return profile.getName() + " профиліндегі дағдылар саны: " + profile.getSkills().size();
        }
    }
}
