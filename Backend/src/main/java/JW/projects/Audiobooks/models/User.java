package JW.projects.Audiobooks.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(
        name = "users"
)
public class User extends AbstractIdentifiableModel {
    @Column
    private String username;
    @Getter
    @Column
    private String email;
    @Column
    private String password;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
    }

    public String getUserName() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    public static BCryptPasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    public boolean isPasswordEmpty() {
        return this.password == null || this.password.isEmpty();
    }

    public boolean isMatchingPassword(String password) {
        return getEncoder().matches(password, this.password);
    }
}
