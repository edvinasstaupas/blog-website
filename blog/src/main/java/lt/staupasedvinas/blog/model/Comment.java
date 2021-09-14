package lt.staupasedvinas.blog.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "user_comment_author_fkey")
    )
    private User author;

    @PastOrPresent
    @Column(name = "post_date", columnDefinition = "timestamp")
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "post_id",
            foreignKey = @ForeignKey(name = "post_comment_fkey")
    )
    private Post post;
}
