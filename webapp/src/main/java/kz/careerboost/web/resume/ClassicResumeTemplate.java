package kz.careerboost.web.resume;

import org.springframework.stereotype.Component;

@Component
public class ClassicResumeTemplate implements ResumeTemplateStrategy {
    @Override public String templateName() { return "classic"; }
    @Override public String accentColor() { return "#0f766e"; }
}