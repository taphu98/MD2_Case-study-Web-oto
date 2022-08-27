package rikkei.academy.view;

import rikkei.academy.back.Back;
import rikkei.academy.config.Config;
import rikkei.academy.controller.CarController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.car.Car;
import rikkei.academy.model.category.Category;
import rikkei.academy.model.role.RoleName;
import rikkei.academy.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class ViewHome {
    CarController carController = new CarController();
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    List<Car> carList = carController.showListCar();
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
        System.out.println("4. Log out");
        int choice = Integer.parseInt(Config.scanner().nextLine());
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
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuUser();
    }



    private void menuPM() {
        System.out.println("Hello PM: " + currentUser.getName());
        System.out.println("1. Car manage");
        System.out.println("2. Category manage ");
        System.out.println("3. User Manage");
        System.out.println("4. Log out");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                new ViewCar().menuCar();
                break;
            case 2:
                new ViewCategory().ViewCategoryMenu();
                break;
            case 3:

            case 4:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuPM();
    }
    public void menuAdmin() {
        System.out.println("Hello ADMIN: " + currentUser.getName());
        System.out.println("1. Car manage");
        System.out.println("2. Category manage ");
        System.out.println("3. User Manage");
        System.out.println("4. Log out");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                new ViewCar().menuCar();
                break;
            case 2:
                new ViewCategory().ViewCategoryMenu();
                break;
            case 3:

            case 4:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuAdmin();
    }



}
