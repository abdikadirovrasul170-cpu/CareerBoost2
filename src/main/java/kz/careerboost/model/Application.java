package kz.careerboost.model;

public class Application {
    public enum Status { SENT, WITHDRAWN }
    private final String id;
    private final String candidateId;
    private final String jobId;
    private Status status = Status.SENT;
    public Application(String id, String candidateId, String jobId) {
        this.id = id; this.candidateId = candidateId; this.jobId = jobId;
    }
    public String getId() { return id; }
    public String getCandidateId() { return candidateId; }
    public String getJobId() { return jobId; }
    public Status getStatus() { return status; }
    public void withdraw() { status = Status.WITHDRAWN; }
}
