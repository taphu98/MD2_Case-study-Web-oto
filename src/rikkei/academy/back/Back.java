package rikkei.academy.back;

import rikkei.academy.config.Config;
import rikkei.academy.view.ViewHome;

public class Back {
    public static void back() {
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewHome();
        }
    }
}
