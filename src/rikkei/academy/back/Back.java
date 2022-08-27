package rikkei.academy.back;

import rikkei.academy.config.Config;
import rikkei.academy.view.ViewCar;
import rikkei.academy.view.ViewCompany;
import rikkei.academy.view.ViewHome;

public class Back {
    public static void backHome() {
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewHome();
        }
    }
    public static void backCarMenu(){
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCar();
        }
    }
    public static void backCompanyMenu(){
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCompany();
        }
    }
}
