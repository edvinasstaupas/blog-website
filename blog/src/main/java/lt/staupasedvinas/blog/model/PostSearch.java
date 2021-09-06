package lt.staupasedvinas.blog.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSearch {
    private String keyword;
}
