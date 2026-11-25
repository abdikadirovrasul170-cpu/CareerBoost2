package kz.careerboost.web.resume;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ResumeTemplateStrategyTest {
    private final ResumeTemplateStrategy modern = new ModernResumeTemplate();
    private final ResumeTemplateStrategy classic = new ClassicResumeTemplate();

    @Test void modernTemplateHasModernName() { assertEquals("modern", modern.templateName()); }
    @Test void classicTemplateHasClassicName() { assertEquals("classic", classic.templateName()); }
    @Test void modernTemplateHasHexAccent() { assertTrue(modern.accentColor().matches("#[0-9a-fA-F]{6}")); }
    @Test void classicTemplateHasHexAccent() { assertTrue(classic.accentColor().matches("#[0-9a-fA-F]{6}")); }
    @Test void templatesHaveDifferentNames() { assertNotEquals(modern.templateName(), classic.templateName()); }
    @Test void templatesHaveDifferentColors() { assertNotEquals(modern.accentColor(), classic.accentColor()); }
    @Test void modernColorIsViolet() { assertEquals("#6c63ff", modern.accentColor()); }
    @Test void classicColorIsTeal() { assertEquals("#0f766e", classic.accentColor()); }
    @Test void modernNameIsNotBlank() { assertFalse(modern.templateName().isBlank()); }
    @Test void classicNameIsNotBlank() { assertFalse(classic.templateName().isBlank()); }
}