package vn.dodientu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.dodientu.model.User;
import vn.dodientu.service.impl.UserServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // API để lấy người dùng theo email
    @GetMapping("/get-by-email")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);  // Dùng phương thức findByEmail trong UserService
    }
    // API kiểm tra kết nối MySQL thông qua findByEmail
    @GetMapping("/check-email")
    public Optional<User> checkEmail(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);  // Gọi phương thức findByEmail để kiểm tra
        if (user.isPresent()) {
            return user;  // Trả về người dùng nếu tìm thấy
        } else {
            return null;  // Trả về null nếu không tìm thấy người dùng
        }
    }
}
