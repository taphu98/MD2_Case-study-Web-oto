package rikkei.academy.model.role;

import java.io.Serializable;

public class Role implements Serializable {
     int id;
     RoleName roleName;

    public Role()  {
    }

    public Role(int id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "" + roleName;
    }
}
