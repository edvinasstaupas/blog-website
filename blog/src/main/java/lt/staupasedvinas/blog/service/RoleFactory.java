package lt.staupasedvinas.blog.service;

import lt.staupasedvinas.blog.model.Role;

public class RoleFactory {

    public static Role getUserRole() {
        return new Role(1L, "USER");
    }

    public static Role getAdminRole() {
        return new Role(2L, "ADMIN");
    }
}
