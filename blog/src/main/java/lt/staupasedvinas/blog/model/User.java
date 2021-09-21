package lt.staupasedvinas.blog.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Comparable<User>{

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

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public int compareTo(User o) {
        return Math.toIntExact(this.getId() - o.getId());
    }
}
