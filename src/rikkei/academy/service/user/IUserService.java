package rikkei.academy.service.user;

import rikkei.academy.model.user.User;
import rikkei.academy.service.IGenerateService;

public interface IUserService extends IGenerateService<User> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    boolean checkLogin(String username, String password);
    User getCurrentUser();

    void saveCurrentUser(User user);
}
