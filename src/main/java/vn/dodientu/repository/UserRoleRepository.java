package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}