package lt.staupasedvinas.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogIn {
    private String email;
    private String password;
    private boolean rememberMe;
}
