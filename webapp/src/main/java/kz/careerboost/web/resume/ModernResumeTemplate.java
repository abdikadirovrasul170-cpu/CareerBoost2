package kz.careerboost.web.resume;

import org.springframework.stereotype.Component;

@Component
public class ModernResumeTemplate implements ResumeTemplateStrategy {
    @Override public String templateName() { return "modern"; }
    @Override public String accentColor() { return "#6c63ff"; }
}