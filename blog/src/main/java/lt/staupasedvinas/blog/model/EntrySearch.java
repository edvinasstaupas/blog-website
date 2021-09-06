package lt.staupasedvinas.blog.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrySearch {
    private String keyword;
}
