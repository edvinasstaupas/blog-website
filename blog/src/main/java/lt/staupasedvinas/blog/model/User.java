package lt.staupasedvinas.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "user_type_id",
    foreignKey = @ForeignKey(name = "user_user_type_fkey"))
    private UserType usertype;

    @OneToMany(mappedBy = "author")
    private List<Entry> entries;
}
