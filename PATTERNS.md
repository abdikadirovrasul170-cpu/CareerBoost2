# Design patterns plan

| Pattern | Group | Module | Problem solved / planned use |
|---|---|---|---|
| Factory Method | Creational | User | Creates the appropriate user/profile object without exposing construction details to callers. |
| Singleton | Creational | Database configuration | Provides one shared configuration/connection-pool holder for the application lifecycle; avoid a global raw connection. |
| Adapter | Structural | External Services | Converts a third-party job-service API into CareerBoost's internal interface. |
| Observer | Behavioral | Skills | Notifies profile/resume modules when a user's skills change. |
| Strategy | Behavioral | Resume | Allows different resume-export or ranking algorithms to be selected at runtime. |

## Implementation order

1. Define interfaces and domain contracts first.
2. Implement Adapter and Strategy with unit tests.
3. Add Observer events for skill updates.
4. Add Factory Method for user creation.
5. Keep database configuration centralized; use dependency injection where possible rather than unnecessary global state.
