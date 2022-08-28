package rikkei.academy.controller;

import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.role.Role;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.model.user.User;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();
    User currentUser = userService.getCurrentUser();
    IRoleService roleService = new RoleServiceIMPL();
    public List<User> getListUser(){
       return userService.findAll();
    }
    public void createUser(User user){
        userService.save(user);
    }
    public ResponseMessenger register(SignUpDTO signUpDTO){
        if (userService.existedByUsername(signUpDTO.getUsername())){
            return new ResponseMessenger("user_existed");
        }
        if (userService.existedByEmail(signUpDTO.getEmail())){
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role : strRole){
            switch (role){
                case "admin" :
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                case "pm":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                    break;
                default:
                    return new ResponseMessenger("invalid_role");
            }
        }
        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roles
        );

        userService.save(user);
        return new ResponseMessenger("success");
    }
    public ResponseMessenger login(SignInDTO signInDTO){
        if (!userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())){
            return new ResponseMessenger("login_failure");
        }
        User login = userService.findByUsername(signInDTO.getUsername());
        userService.saveCurrentUser(login);
        return new ResponseMessenger("login_success");
    }
    public User getCurrentUser(){
        return userService.getCurrentUser();
    }
    public void logout(){
        userService.saveCurrentUser(null);
    }
    public User detailUser(String username){
        return userService.findByUsername(username);
    }
    public void deleteUser(String username){

        userService.deleteByUsername(username);
    }
    public ResponseMessenger changePassword(String oldPassword, String newPassword){
        if(!oldPassword.equals(currentUser.getPassword())){
            return new ResponseMessenger("not_match");
        }
        currentUser.setPassword(newPassword);
        userService.updateData();
        return new ResponseMessenger("success");
    }
    public ResponseMessenger changeRole(String username, String roleName){
        if (userService.findByUsername(username)==null){
            return new ResponseMessenger("not_found");
        }
        if (!roleName.equals("user") && !roleName.equals("pm")){
            return new ResponseMessenger("invalid_role");
        }
        Role role = roleName.equals("user")?roleService.findByRoleName(RoleName.USER):roleService.findByRoleName(RoleName.PM);
        userService.changeRole(username,role);
        return new ResponseMessenger("success");
    }

    public ResponseMessenger blockUser(String username) {
        if (userService.findByUsername(username)==null){
            return new ResponseMessenger("not_found");
        }
        userService.changeStatus(username);
        boolean check = userService.findByUsername(username).isStatus();
        if (check){
            return new ResponseMessenger("blocked");
        }else {
            return new ResponseMessenger("unblocked");
        }
    }
}
