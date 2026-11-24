package kz.careerboost.patterns;

// ADAPTER: ескі түйіндеме жүйесін жаңа интерфейске бейімдеу.
public class LegacyResumeAdapter {
    public interface ResumeReader { String readResume(String candidateName); }
    private final LegacyResumeSystem legacy;
    public LegacyResumeAdapter(LegacyResumeSystem legacy) { this.legacy = legacy; }
    public String readResume(String candidateName) { return legacy.loadOldFormat(candidateName); }

    public static class LegacyResumeSystem {
        public String loadOldFormat(String name) { return "LEGACY_RESUME[" + name + "]"; }
    }
}
