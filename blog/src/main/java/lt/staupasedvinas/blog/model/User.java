package lt.staupasedvinas.blog.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public abstract class User {
    private final String username;
    private final String password;
}
