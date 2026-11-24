package kz.careerboost.patterns;

// TEMPLATE METHOD: түйіндемені тексерудің тұрақты алгоритмі.
public abstract class ResumeReviewTemplate {
    public final String review(String candidateName) {
        String result = validate(candidateName);
        return result + " | " + score() + " | " + recommendation();
    }
    protected String validate(String name) {
        return name == null || name.isBlank() ? "Қате: кандидат аты жоқ" : "Кандидат: " + name;
    }
    protected abstract String score();
    protected abstract String recommendation();
    public static class StandardReview extends ResumeReviewTemplate {
        protected String score() { return "Бастапқы бағалау: 75/100"; }
        protected String recommendation() { return "Ұсыныс: портфолионы толықтыру"; }
    }
}
