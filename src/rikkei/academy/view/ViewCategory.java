package rikkei.academy.view;


import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.model.category.Category;

import java.util.List;

import static rikkei.academy.plugin.ConsoleColors.BLUE;
import static rikkei.academy.plugin.ConsoleColors.RESET;

public class ViewCategory {
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.showCategoryList();

    public void categoryMenu() {
        System.out.println(BLUE+"------------------------------");
        System.out.println("|     ***CATEGORY MANAGE***    |");
        System.out.println("|  1. Category product list    |");
        System.out.println("|  2. Create category product  |");
        System.out.println("|  3. Edit category product    |");
        System.out.println("|  4. Delete category product  |");
        System.out.println("|  5. Back to menu             |");
        System.out.println("-------------------------------" + RESET);
        System.out.println("Enter your choice:");
        int choice = Config.getValidInteger();
        switch (choice) {
            case 1:
                formShowCategoryList();
                break;
            case 2:
                formCreateCategory();
                break;
            case 3:
                formEditCategory();
                break;
            case 4:
                formDeleteCategory();
                break;
            case 5:
                new ViewHome();
                break;
        }
    }

    private void formDeleteCategory() {
        System.out.println("ENTER ID TO DELETE: ");
        int idCategory = Config.getValidInteger();
        if (categoryController.findCategory(idCategory) == null) {
            System.out.println("NOT EXIST");
        } else {
            System.out.println("1 FOR YES/ 2 FOR NO");
            int chooseDelete = Config.getValidInteger();
            switch (chooseDelete) {
                case 1:
                    categoryController.deleteCategory(idCategory);
                    formShowCategoryList();
                    categoryController.showCategoryList();
                    break;
                case 2:
                    new ViewHome();
                    break;
            }


        }
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCategory().categoryMenu();
        }
    }

    private void formEditCategory() {
        System.out.println("ENTER ID TO UPDATE: ");
        int idCategory = Config.getValidInteger();
        if (categoryController.findCategory(idCategory) == null) {
            System.out.println("NOT EXIST");
        } else {
            Category category = categoryController.findCategory(idCategory);
            System.out.println("OLD CATEGORY NAME: " + category.getName());
            System.out.println("ENTER NEW CATEGORY NAME: ");
            String newCategoryName = Config.scanner().nextLine();
            if (newCategoryName.matches("[a-zA-Z]{1,10}")) {
                newCategoryName = category.getName();
                Category newCategory = new Category(newCategoryName);
                categoryController.editCategory(idCategory, newCategory);
                System.out.println("UPDATE SUCCESS");
                categoryController.showCategoryList();
            }else {
                System.out.println("PLEASE TRY AGAIN!");
            }


        }
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCompany().menuCompany();
        }
    }

    private void formCreateCategory() {
        System.out.println("-----CREATE CATEGORY-----");
        while (true) {
            int idCategory;
            if (categoryList.size() == 0) {
                idCategory = 1;
            } else {
                idCategory = categoryList.get(categoryList.size() - 1).getId() + 1;
            }
            System.out.println("INSERT CATEGORY NAME : ");
            String categoryName = Config.scanner().nextLine();
            if (categoryName.matches("[a-zA-Z\\d]{1,10}")){
                Category category = new Category(idCategory, categoryName);
                categoryController.createCategory(category);
                System.out.println("Create success");
                categoryController.showCategoryList();
            }else {

                System.out.println("PLEASE ENTER CATEGORY NAME!");
            }
            System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new ViewCategory().categoryMenu();
                break;
            }
        }
    }

    public void formShowCategoryList() {
        System.out.printf("| %-15s | %-15s |%n", " CATEGORY ID ", " CATEGORY NAME ");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf("|      %-10d |       %-5s     |%n", categoryList.get(i).getId(), categoryList.get(i).getName());

        }
    }


}
