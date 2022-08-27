package rikkei.academy.service.role;

import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService {
    public static List<Role> roleList = new ArrayList<>();

    static {
        roleList.add(new Role(1, RoleName.USER));
        roleList.add(new Role(2, RoleName.ADMIN));
        roleList.add(new Role(3, RoleName.PM));
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public Role findByRoleName(RoleName name) {
        for (int i = 0; i < roleList.size(); i++) {
            if (name == roleList.get(i).getRoleName()){
                return roleList.get(i);
            }
        }

        return null;
    }
}


