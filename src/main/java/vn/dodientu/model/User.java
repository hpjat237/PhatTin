package vn.dodientu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")  // Đặt tên bảng trong cơ sở dữ liệu
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động tăng ID
    private Long id;

    private String username;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String image_url;
    private String resetCode;
    private Instant resetCodeExpiry;

    // Constructor có tham số
    public User(String email) {
        this.email = email;
    }
    public User(String username,String password) {
        this.username = username;
        this.password = password;
    }
}
