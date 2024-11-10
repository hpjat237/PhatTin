package vn.dodientu.model;

import java.io.Serializable;
import java.util.Objects;


public class UserRoleId implements Serializable {

    private Long userId;
    private Long roleId;

    // Constructors, getters, setters

    // Override equals and hashCode based on the fields of the composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}


