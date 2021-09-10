package lt.staupasedvinas.blog.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @NotNull
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id",
            foreignKey = @ForeignKey(name = "user_post_author_fkey")
    )
    private User author;

    @Column(name = "post_date", columnDefinition = "timestamp")
    private Date postDate;
/*
    @ManyToOne
    @JoinColumn(name = "language_id",
            foreignKey = @ForeignKey(name = "post_language_fkey"))
    private Language language;*/

}
