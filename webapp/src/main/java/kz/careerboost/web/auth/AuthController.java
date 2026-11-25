package kz.careerboost.web.auth;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccountRepository accounts;
    private final PasswordEncoder encoder;

    public AuthController(AccountRepository accounts, PasswordEncoder encoder) {
        this.accounts = accounts;
        this.encoder = encoder;
    }

    public record RegisterRequest(@NotBlank String fullName, @Email @NotBlank String email,
                                  @NotBlank @Size(min = 8) String password, String role) { }
    public record LoginRequest(@Email @NotBlank String email, @NotBlank String password) { }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, HttpSession session) {
        if (accounts.existsByEmailIgnoreCase(request.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Email is already registered"));
        }
        String role = "EMPLOYER".equalsIgnoreCase(request.role()) ? "EMPLOYER" : "CANDIDATE";
        Account account = new Account();
        account.setFullName(request.fullName().trim());
        account.setEmail(request.email().trim().toLowerCase());
        account.setPasswordHash(encoder.encode(request.password()));
        account.setRole(role);
        Account saved = accounts.save(account);
        session.setAttribute("accountId", saved.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(publicAccount(saved));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        Account account = accounts.findByEmailIgnoreCase(request.email().trim())
            .orElse(null);
        if (account == null || !encoder.matches(request.password(), account.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid email or password"));
        }
        session.setAttribute("accountId", account.getId());
        return ResponseEntity.ok(publicAccount(account));
    }

    @PostMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate();
        return Map.of("message", "Signed out");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object id = session.getAttribute("accountId");
        if (!(id instanceof Long accountId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Not signed in"));
        }
        return accounts.findById(accountId).<ResponseEntity<?>>map(a -> ResponseEntity.ok(publicAccount(a)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Session expired")));
    }

    private Map<String, Object> publicAccount(Account account) {
        return Map.of("id", account.getId(), "fullName", account.getFullName(),
            "email", account.getEmail(), "role", account.getRole());
    }
}