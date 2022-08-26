package rikkei.academy.service.role;

import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;

import java.util.List;

public interface IRoleService {
List<Role> findAll();
Role findByRoleName(RoleName name);
}
