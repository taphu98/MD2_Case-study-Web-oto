package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.user.User;

import static rikkei.academy.plugin.ConsoleColors.BLUE;
import static rikkei.academy.plugin.ConsoleColors.RESET;

public class ViewMyProfile {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    public void viewProfile(){
        System.out.println(BLUE+"---------------------------");
        System.out.println("|     ***MY PROFILE***     |");
        System.out.println("|    1. Change password    |");
        System.out.println("|    2. Change role        |");
        System.out.println("|    3. Block user         |");
        System.out.println("|    4. Change profile     |");
        System.out.println("|    5. Log out            |");
        System.out.println("|    6. Back menu          |");
        System.out.println("-----------------------------"+ RESET);
        System.out.println("ENTER YOUR CHOICE : ");
        int choice = Config.getValidInteger();
        switch (choice){
            case 1:
                new ViewUser().formChangePassword();
                break;
            case 2:
                new ViewUser().formChangeRole();
                break;
            case 3:
                new ViewUser().formBlockUser();
                break;
            case 4:
                new ViewUser().formShowListUser();
                formChangeProfile();
                break;
            case 5:
                userController.logout();
                new ViewMenu().menu();
                break;
            case 6:
                new ViewHome();
                break;
        }
    }

    public void formChangeProfile() {
        System.out.println("ENTER USERNAME TO CHANGE PROFILE: ");
        String userName = Config.scanner().nextLine();
        if (userController.detailUser(userName).equals(currentUser.getUsername())){
            System.out.println("USERNAME IS NOT MATCH");
        }else {
            User user = userController.detailUser(userName);
            System.out.println("OLD NAME: "+ currentUser.getName());
            System.out.println("ENTER NEW NAME: ");
            String newName = Config.scanner().nextLine();
            if (newName.matches("[A-Z][a-zA-Z]{1,10}")){
                newName = user.getName();
            }
            System.out.println("ENTER NEW EMAIL: ");
            String newEmail = Config.scanner().nextLine();
            if (newEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                newEmail = user.getEmail();
            }
            User newUser = new User(currentUser.getId(), newName, newEmail, currentUser.getPassword());
            userController.editUser(userName,newUser);
            System.out.println("Update success");
            userController.getListUser();
        }
    }
}
