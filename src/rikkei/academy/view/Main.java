package rikkei.academy.view;

import rikkei.academy.controller.UserController;
import rikkei.academy.model.user.User;

import javax.swing.text.View;

public class Main {
    UserController userController = new UserController();
    public Main(){
        User currentUser = userController.getCurrentUser();
        if (currentUser == null){
            new ViewMenu().menu();
        }else {
            new ViewHome().menuUser();
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}