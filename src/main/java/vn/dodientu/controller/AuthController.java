package vn.dodientu.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.dodientu.dto.Response;
import vn.dodientu.model.User;
import vn.dodientu.service.interfaces.IAuthService;
import vn.dodientu.service.interfaces.IEmailService;
import vn.dodientu.service.interfaces.IUserService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IEmailService emailService;

    // Hiển thị trang đăng nhập
    //@GetMapping("/login")
    //public String showLoginPage() {
    //    return "login"; // Trả về trang login.html
    //}

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            session.setAttribute("user", user); // Lưu thông tin người dùng vào session
            return "redirect:/home"; // Chuyển hướng đến trang chủ
        }
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác");
        return "login"; // Quay lại trang đăng nhập nếu thất bại
    }

    // Hiển thị trang đăng ký
    //@GetMapping("/register")
    //public String showRegisterPage(Model model) {
    //    model.addAttribute("user", new User());
    //    return "register"; // Trả về trang register.html
    //}

    // Xử lý đăng ký
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "register"; // Quay lại trang đăng ký nếu tên đăng nhập đã tồn tại
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
        userService.saveUser(user); // Lưu thông tin người dùng mới vào cơ sở dữ liệu
        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
    }

    // Hiển thị trang quên mật khẩu
    //@GetMapping("/forgot-password")
    //public String showForgotPasswordPage() {
    //   return "forgot-password"; // Trả về trang forgot-password.html
    //}

    // Xử lý yêu cầu đặt lại mật khẩu
    @PostMapping("/forgot-password")
    public ResponseEntity<Response> sendForgotPasswordRequest(@RequestParam String email) {
        try{
            return ResponseEntity.ok().body(authService.requestPasswordReset(email));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new Response(null, e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(null, "An unexpected error occurred. Please try again later."));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response> resetPassword(@RequestParam String email,
                                                  @RequestParam String resetCode,
                                                  @RequestParam String password) {
        try{
            return ResponseEntity.ok().body(authService.resetPassword(email, resetCode, password));
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(new Response(null, e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(null, "An unexpected error occurred. Please try again later."));
        }
    }
}