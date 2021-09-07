package lt.staupasedvinas.blog.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostSearch {

    @NotBlank
    private String keyword;
}
