package rikkei.academy.service.user;

import rikkei.academy.config.Config;

import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.model.user.User;
import rikkei.academy.service.role.RoleServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService {
    static String PATH_USER = "C:\\Users\\Asus\\Module2\\case-study\\Website-ban-o-to\\src\\rikkei\\academy\\database\\user.txt";
    static String PATH_USER_LOGIN = "C:\\Users\\Asus\\Module2\\case-study\\Website-ban-o-to\\src\\rikkei\\academy\\database\\user_login.txt";
    static Config<List<User>> config = new Config<>();
    static List<User> userList = config.read(PATH_USER);

    static {
        if (userList == null) {
            userList = new ArrayList<>();
            Set<Role> roles = new HashSet<>();
            roles.add(new RoleServiceIMPL().findByRoleName(RoleName.ADMIN));
            userList.add(new User(1,"Phu", "admin", "phu@gmail.com","admin",roles));
        }
    }

    @Override
    public List<User> findAll() {
        config.write(PATH_USER, userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        config.write(PATH_USER, userList);
        updateData();
    }

    @Override
    public void updateData() {
        config.write(PATH_USER, userList);
    }

    @Override
    public User findByUsername(String username) {
        for (int i = 0; i < userList.size() ; i++) {
            if (userList.get(i).getUsername().equalsIgnoreCase(username)) {
                return userList.get(i);
            }
        }return null;
    }

    @Override
    public void deleteByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equalsIgnoreCase(userList.get(i).getUsername())){
                userList.remove(i);
            }
        }
        updateData();
    }


    @Override
    public boolean existedByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        User user = new Config<User>().read(PATH_USER_LOGIN);
        if (user == null) {
            return null;
        }
        return findByUsername(user.getUsername());
    }

    @Override
    public void saveCurrentUser(User user) {
        new Config<User>().write(PATH_USER_LOGIN, user);
    }

    @Override
    public void changeRole(String username, Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        findByUsername(username).setRoles(roles);
        updateData();
    }

    @Override
    public void changeStatus(String username) {
        User user = findByUsername(username);
        user.setStatus(!user.isStatus());
        updateData();
    }


}
