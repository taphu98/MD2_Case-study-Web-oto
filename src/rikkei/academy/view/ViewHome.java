package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.model.user.User;

import java.util.ArrayList;

import static rikkei.academy.plugin.ConsoleColors.*;

public class ViewHome {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();


    public ViewHome() {
        switch (roleName) {
            case ADMIN:
                menuAdmin();
                break;
            case USER:
                menuUser();
                break;
            case PM:
                menuPM();
                break;
        }
    }


    public void menuUser() {
        System.out.println("HELLO " + roleName + ": " + currentUser.getName());
        System.out.println("1. Show category list");
        System.out.println("2. Show car list");
        System.out.println("3. Show detail car");
        System.out.println("4. My profile");
        System.out.println("5. Change password");
        System.out.println("6. Log out");
        System.out.println("Enter your choice:");
        int choice = Config.getValidInteger();
        switch (choice) {
            case 1:
                new ViewCategory().formShowCategoryList();
                break;
            case 2:
                new ViewCar().formShowCarList();
                break;
            case 3:
                new ViewCar().formShowDetailCar();
                break;
            case 4:
                System.out.println("Current User: " + currentUser);
                new ViewMyProfile().formChangeProfile();
                break;
            case 5:
                new ViewUser().formChangePassword();
                break;
            case 6:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuUser();
    }


    private void menuPM() {
        System.out.println("Hello PM: " + currentUser.getName());
        System.out.println("1. Car manage ");
        System.out.println("2. Category manage  ");
        System.out.println("3. Company manage ");
        System.out.println("4. My profile ");
        System.out.println("5. Change password");
        System.out.println("6. Block user ");
        System.out.println("7. Log out ");
        System.out.println("Enter your choice:");
        int choice = Config.getValidInteger();
        switch (choice) {
            case 1:
                new ViewCar().menuCar();
                break;
            case 2:
                new ViewCategory().categoryMenu();
                break;
            case 3:
                new ViewCompany().menuCompany();
                break;
            case 4:
                System.out.println("Current User: " + currentUser);
                new ViewMyProfile().formChangeProfile();
                break;
            case 5:
                new ViewUser().formChangePassword();
                break;
            case 6:
                new ViewUser().formBlockUser();
                break;
            case 7:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuPM();
    }

    public void menuAdmin() {
        System.out.println("Hello ADMIN: " + currentUser.getName());
        System.out.println(BLUE+"------------------------");
        System.out.println("|   1. Car manage      |");
        System.out.println("|   2. Category manage |");
        System.out.println("|   3. Company manage  |");
        System.out.println("|   4. User manage     |");
        System.out.println("|   5. My profile      |");
        System.out.println("|   6. Log out         |");
        System.out.println("------------------------");
        System.out.println("Enter your choice:");
        int choice = Config.getValidInteger();
        switch (choice) {
            case 1:
                new ViewCar().menuCar();
                break;
            case 2:
                new ViewCategory().categoryMenu();
                break;
            case 3:
                new ViewCompany().menuCompany();
                break;
            case 4:
                new ViewUser().viewUserMenu();
                break;
            case 5:
                new ViewMyProfile().viewProfile();
                break;
            case 6:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuAdmin();
    }


}
