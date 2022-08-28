package rikkei.academy.service.user;

import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.role.Role;
import rikkei.academy.model.user.User;
import rikkei.academy.service.IGenerateService;

public interface IUserService extends IGenerateService<User> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    boolean checkLogin(String username, String password);
    User getCurrentUser();
//    void changPassword();
    void saveCurrentUser(User user);

    void changeRole(String username, Role role);
    void changeStatus(String username);
}
