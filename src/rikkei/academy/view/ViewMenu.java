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

import static rikkei.academy.plugin.ConsoleColors.*;;

public class ViewMenu {
    public UserController userController = new UserController();
    public List<User> userList = userController.getListUser();
    public void menu() {
        System.out.println(BLUE+"-----------------------------");
        System.out.println("|      ***** MENU *****     |");
        System.out.println("|  1. Register              |");
        System.out.println("|  2. Login                 |");
        System.out.println("|  3. Category product      |");
        System.out.println("|  4. Search product        |");
        System.out.println("|  5. Sort product by price |");
        System.out.println("-----------------------------" + RESET);
        System.out.println("Enter your choice:");
        int choice = Config.getValidInteger();
        switch (choice) {
            case 1:
                formRegister();
                break;
            case 2:
                formLogIn();
                break;
            case 3:
                new ViewCategory().formShowCategoryList();
                break;
            case 4:
                new ViewCar().formShowDetailCar();
                break;
            case 5:
                new ViewCar().formShowCarList();
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
            System.out.println("ENTER YOUR NAME: ");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID NAME! PLEASE TRY AGAIN!");
            }
        }

        String username;
        while (true) {
            System.out.println("ENTER YOUR USERNAME: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID USERNAME! PLEASE TRY AGAIN!");
            }
        }

        String email;
        while (true) {
            System.out.println("ENTER YOUR EMAIL: ");
            email = Config.scanner().nextLine();
            if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                break;
            } else {
                System.err.println("INVALID EMAIL! PLEASE TRY AGAIN!");
            }
        }

        String password;
        while (true) {
            System.out.println("ENTER YOUR PASSWORD: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z\\d]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID PASSWORD! PLEASE TRY AGAIN!");
            }
        }
        String role = "user";
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
            case "invalid_role":
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
            System.out.println("ENTER YOUR USERNAME: ");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID PASSWORD! PLEASE TRY AGAIN!");
            }
        }
        String password;
        while (true) {
            System.out.println("ENTER YOUR PASSWORD: ");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z\\d]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID PASSWORD! PLEASE TRY AGAIN!");
            }
        }
        SignInDTO signInDTO = new SignInDTO(username, password);
        ResponseMessenger responseMessenger = userController.login(signInDTO);
        switch (responseMessenger.getMessage()) {
            case "blocked":
                System.out.println("This user is blocked");
                break;

            case "login_success":
                System.out.println("Login successful!");
                new ViewHome();
                break;
            case "login_failure":
                System.out.println("Username or password is incorrect!");

        }


    }
}

