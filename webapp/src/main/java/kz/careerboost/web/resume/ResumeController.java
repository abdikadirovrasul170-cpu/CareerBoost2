package kz.careerboost.web.resume;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin
public class ResumeController {
    private final ResumeRepository repository;
    private final List<ResumeTemplateStrategy> templates;

    public ResumeController(ResumeRepository repository, List<ResumeTemplateStrategy> templates) {
        this.repository = repository;
        this.templates = templates;
    }

    @GetMapping
    public List<Resume> all() { return repository.findAll(); }

    @GetMapping("/{id}")
    public Resume one(@PathVariable Long id) { return find(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resume create(@Valid @RequestBody Resume resume) {
        normalizeTemplate(resume);
        resume.setId(null);
        return repository.save(resume);
    }

    @PutMapping("/{id}")
    public Resume update(@PathVariable Long id, @Valid @RequestBody Resume input) {
        Resume current = find(id);
        current.setTitle(input.getTitle());
        current.setFullName(input.getFullName());
        current.setEmail(input.getEmail());
        current.setPhone(input.getPhone());
        current.setSummary(input.getSummary());
        current.setSkills(input.getSkills());
        current.setExperience(input.getExperience());
        current.setEducation(input.getEducation());
        current.setTemplate(input.getTemplate());
        normalizeTemplate(current);
        return repository.save(current);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repository.delete(find(id)); }

    private Resume find(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume not found"));
    }

    private void normalizeTemplate(Resume resume) {
        boolean supported = templates.stream().anyMatch(t -> t.templateName().equals(resume.getTemplate()));
        if (!supported) resume.setTemplate("modern");
    }
}