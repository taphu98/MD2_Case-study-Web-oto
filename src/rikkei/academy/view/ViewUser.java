package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.user.User;

import java.util.List;

import static rikkei.academy.plugin.ConsoleColors.*;

public class ViewUser {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();

    public void viewUserMenu() {
        System.out.println(BLUE+"---------------------------------");
        System.out.println("|   *****USER MANAGE MENU*****  |");
        System.out.println("|   1. Show list user           |");
        System.out.println("|   2. Delete user              |");
        System.out.println("|   3. Change password          |");
        System.out.println("|   4. Change role              |");
        System.out.println("|   5. Block user               |");
        System.out.println("|   6. Back                     |");
        System.out.println("---------------------------------" + RESET);
        System.out.println("Enter your choice:");
        int choice = Config.getValidInteger();
        switch (choice) {
            case 1:
                formShowListUser();
                break;
            case 2:
                formDeleteUser();
                break;
            case 3:
                formChangePassword();
                break;
            case 4:
                formChangeRole();
                break;
            case 5:
                formBlockUser();
                break;
            case 6:
                new ViewHome();
                break;

        }
        viewUserMenu();
    }

    public void formBlockUser() {
        formShowListUser();
        System.out.println("Enter username to block:");
        String username = Config.scanner().nextLine();
        ResponseMessenger messenger = userController.blockUser(username);
        switch (messenger.getMessage()){
            case "not_found":
                System.out.println("Username not found");
                break;
            case "blocked":
                System.out.println("User is blocked");
                break;
            case "unblocked":
                System.out.println("User is unblocked");
        }

    }

    public void formChangeRole() {
        formShowListUser();
        System.out.println("Enter username of user to change role");
        String username = Config.scanner().nextLine();
        System.out.println("Enter role to change pm/user: ");
        String roleName = Config.scanner().nextLine();
        ResponseMessenger messenger = userController.changeRole(username, roleName);
        switch (messenger.getMessage()){
            case "success" :
                System.out.println("Change role successfully!");
                break;
            case "invalid_role":
                System.err.println("Invalid role!");
            case "not_found":
                System.out.println("Username can't be found!");

        }
    }

    public void formChangePassword() {
        String oldPassword;
        while (true) {
            System.out.println("ENTER YOUR PASSWORD: ");
            oldPassword = Config.scanner().nextLine();
            if (oldPassword.matches("[a-zA-Z\\d]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID PASSWORD! PLEASE TRY AGAIN!");
            }
        }
        String newPassword;
        while (true) {
            System.out.println("ENTER YOUR PASSWORD: ");
            newPassword = Config.scanner().nextLine();
            if (newPassword.matches("[a-zA-Z\\d]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID PASSWORD! PLEASE TRY AGAIN!");
            }
        }
        String newPassword1;
        while (true) {
            System.out.println("ENTER YOUR PASSWORD: ");
            newPassword1 = Config.scanner().nextLine();
            if (newPassword1.matches("[a-zA-Z\\d]{1,10}")) {
                break;
            } else {
                System.err.println("INVALID PASSWORD! PLEASE TRY AGAIN!");
            }
        }
        if (!newPassword.equals(newPassword1)) {
            System.out.println("CONFIRM PASSWORD IS WRONG!");
        } else {
            ResponseMessenger messenger = userController.changePassword(oldPassword, newPassword);
            switch (messenger.getMessage()) {
                case "not_match":
                    System.out.println("Old password does not match!");
                    break;
                case "success":
                    System.out.println("Change password success");
                    break;
            }
        }

    }

    private void formDeleteUser() {
        formShowListUser();
        System.out.println("ENTER USERNAME TO DELETE: ");
        String nameUser = Config.scanner().nextLine();
        if (userController.detailUser(nameUser) == null || userController.detailUser(nameUser).equals("")) {
            System.out.println("NOT EXIST");
        } else {
            System.out.println("1 FOR YES/ 2 FOR NO");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete) {
                case 1:
                    userController.deleteUser(nameUser);
                    formShowListUser();
                    break;
                case 2:
                    new ViewHome();
                    break;
            }
        }
    }

    public void formShowListUser() {
        System.out.printf("| %s  | %-15s | %s |%n", "ID", "USERNAME", "ROLE", "STATUS");
        for (User user : userList) {
            System.out.printf("| %s | %-15s | %s |%n", user.getId(), user.getUsername(), user.getRoleName(), user.isStatus() ? "ACTIVATING" : "BLOCKED");
        }
    }
}
