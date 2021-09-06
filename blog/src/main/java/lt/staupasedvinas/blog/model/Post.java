package lt.staupasedvinas.blog.model;


import lombok.*;

import javax.persistence.*;

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

    private String title;

    @Column(columnDefinition="TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "user_post_author_fkey")
    )
    private User author;
}
