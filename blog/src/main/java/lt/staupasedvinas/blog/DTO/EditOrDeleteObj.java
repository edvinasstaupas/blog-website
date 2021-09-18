package lt.staupasedvinas.blog.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EditOrDeleteObj {

    private Long objId;

    private String action;
}
