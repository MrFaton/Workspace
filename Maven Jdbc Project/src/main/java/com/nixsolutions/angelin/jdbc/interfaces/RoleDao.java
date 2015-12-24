package com.nixsolutions.angelin.jdbc.interfaces;

import com.nixsolutions.angelin.jdbc.pojo.Role;

public interface RoleDao {
    void create(Role role);

    void update(Role role);

    void remove(Role role);

    Role findByName(String name);
}
