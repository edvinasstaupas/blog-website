package lt.staupasedvinas.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostEditDTO {

    private String title;

    private String text;
}
