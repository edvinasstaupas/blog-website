package lt.staupasedvinas.blog.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post implements Comparable<Post>{

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

    @PastOrPresent
    @Column(name = "post_date", columnDefinition = "timestamp")
    private Date postDate;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    public void addComment(Comment comment) {
        commentList.add(comment);
    }

    @Override
    public int compareTo(Post o) {
        return this.getPostDate().compareTo(o.getPostDate());
    }
}
