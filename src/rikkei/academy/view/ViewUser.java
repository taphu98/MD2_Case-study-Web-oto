package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.user.User;

import java.util.List;

public class ViewUser {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
    public void viewUser(){
        System.out.println("*****USER MANAGE MENU*****");
        System.out.println("1. Show list user");
        System.out.println("2. Delete user");
        System.out.println("3. Back");
        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                formShowListUser();
                break;
            case 2:
                formDeleteUser();
                break;
            case 3:
                new ViewHome();
                break;

        }
    }

    private void formDeleteUser() {
        System.out.println("ENTER USERNAME TO DELETE: ");
        String nameUser = Config.scanner().nextLine();
        if (userController.detailUser(nameUser) == null) {
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
        }  }

    private void formShowListUser() {
        System.out.printf("%-15s%s%n", "USERNAME", "ROLE");
        for (User user : userList) {
            System.out.printf("%-15s%s%n", user.getUsername(), user.getRoles());
        }
    }
}
