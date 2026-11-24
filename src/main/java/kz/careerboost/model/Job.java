package kz.careerboost.model;

public record Job(String id, String title, String company, String requiredSkill, int salary) {
    public Job {
        if (title == null || title.isBlank() || company == null || company.isBlank())
            throw new IllegalArgumentException("Вакансия атауы мен компания міндетті");
        if (salary < 0) throw new IllegalArgumentException("Жалақы теріс болмауы керек");
    }
}
