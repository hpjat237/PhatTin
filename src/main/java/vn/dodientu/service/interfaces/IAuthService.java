package vn.dodientu.service.interfaces;

import vn.dodientu.dto.Response;
import vn.dodientu.model.User;

public interface IAuthService {
    Response login(String email, String password);
    Response register(User user);
    Response requestPasswordReset(String email);
    Response resetPassword(String email, String resetCode, String password);
}
