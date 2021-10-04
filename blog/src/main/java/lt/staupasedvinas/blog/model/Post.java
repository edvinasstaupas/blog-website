package lt.staupasedvinas.blog.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "user_post_author_fkey"))
    private User author;

    @Column(name = "post_date", columnDefinition = "timestamp")
    private Date postDate;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();
}
