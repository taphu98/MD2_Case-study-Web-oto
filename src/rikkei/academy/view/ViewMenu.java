package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CarController;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;

import rikkei.academy.model.car.Car;
import rikkei.academy.model.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMenu {
    public UserController userController = new UserController();
    public List<User> userList = userController.getListUser();


    public void menu() {
        System.out.println("*****MENU*****");
        System.out.println("1. Register");
        System.out.println("2. Login");

        int choice = Config.scanner().nextInt();
//
        switch (choice) {
            case 1:
                formRegister();
                break;
            case 2:
                formLogIn();
                break;
        }
        menu();
    }

    private void formRegister() {
        System.out.println("-----REGISTER-----");
        int id;
        if (userList.isEmpty()) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }

        String name;
        while (true) {
            System.out.println("Enter your name: ");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z]{1,10}")) {
                break;
            } else {
                System.err.println("Invalid name ! Please try again !");
            }
        }

        String username;
        while (true) {
            System.out.println("Enter your username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,10}")) {
                break;
            } else {
                System.err.println("Invalid username ! Please try again !");
            }
        }

        String email;
        while (true) {
            System.out.println("Enter your email: ");
            email = Config.scanner().nextLine();
            if (email.matches(".+@.+")) {
                break;
            } else {
                System.err.println("Invalid email ! Please try again !");
            }
        }

        String password;
        while (true) {
            System.out.println("Enter your password: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.err.println("Invalid password ! Please try again !");
            }
        }

        System.out.println("Enter your role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, email, password, strRole);
        ResponseMessenger responseMessenger = userController.register(signUpDTO);
        System.out.println(responseMessenger.getMessage());
        switch (responseMessenger.getMessage()) {
            case "user_existed":
                System.out.println("Username already existed!");
                break;
            case "email_existed":
                System.out.println("Email already existed!");
                break;
            case "invalid role":
                System.out.println("Invalid role! Please try again!");
                break;
            case "success":
                System.out.println("Register success!");
                break;
        }


    }


    private void formLogIn() {
        String username;
        while (true) {
            System.out.println("Enter your username: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,10}")) {
                break;
            } else {
                System.err.println("Invalid username ! Please try again !");
            }
        }
        String password;
        while (true) {
            System.out.println("Enter your password: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.err.println("Invalid password ! Please try again !");
            }
        }
        SignInDTO signInDTO = new SignInDTO(username, password);
        ResponseMessenger responseMessenger = userController.login(signInDTO);
        switch (responseMessenger.getMessage()) {
            case "login_success":
                System.out.println("Login successful!");
                new ViewHome();
                break;
            case "login_failure":
                System.out.println("Username or password is incorrect!");
                break;
        }


    }
}

