package lt.staupasedvinas.blog.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Comparable<User>, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @Email
    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "user_type_id",
            foreignKey = @ForeignKey(name = "user_user_type_fkey"))
    private UserType userType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return Math.toIntExact(this.getId() - o.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
