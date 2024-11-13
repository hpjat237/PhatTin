package vn.dodientu.service.interfaces;

import vn.dodientu.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User saveUser(User user);
    void updatePassword(User user, String newPassword);
}
