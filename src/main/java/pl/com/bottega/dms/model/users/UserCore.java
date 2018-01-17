package pl.com.bottega.dms.model.users;


import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserCore implements User {

    @OneToMany(mappedBy = "userCore", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> roles = new HashSet<>();

    private String login, password;

    private LocalDateTime lastLogin;

    @Id
    @GeneratedValue
    private Long id;

    UserCore() {
    }

    UserCore(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public <T extends UserRole> T getRole(Class<T> roleClass) {
        return (T) roles.stream().
                filter(role -> role.getClass().equals(roleClass)).
                findFirst().
                orElseThrow(() -> new RuntimeException("User has no such role"));
    }

    @Override
    public void addRole(UserRole userRole) {
        roles.add(userRole);
        userRole.setUserCore(this);
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public void saveLastLoginDate(Clock clock) {
        this.lastLogin = LocalDateTime.now(clock);
    }
}