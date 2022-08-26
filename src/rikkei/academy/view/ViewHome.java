package rikkei.academy.view;

import rikkei.academy.back.Back;
import rikkei.academy.config.Config;
import rikkei.academy.controller.CarController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.car.Car;
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
        }
    }

    public void menuUser() {
        System.out.println("HELLO " + roleName + ": " + currentUser.getName());
        System.out.println("1. Show list car");
        System.out.println("2. Show detail car");
        System.out.println("3. Log out");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                formShowCarList();
                break;
            case 2:
                formShowDetailCar();
                break;
            case 3:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuUser();
    }

    private void formShowDetailCar() {
        System.out.println("Enter car name: ");
        int idCar = Config.scanner().nextInt();
        if (carController.detailCar(idCar) == null) {
            System.out.println("Not exist");
        } else {
            Car car = carController.detailCar(idCar);
            System.out.println(car);
        }
        new Back();
    }

    public void menuAdmin() {
        System.out.println("Hello ADMIN: " + currentUser.getName());
        System.out.println("1. Show car list");
        System.out.println("2. Create car");
        System.out.println("3. Delete car");
        System.out.println("4. Edit car");
        System.out.println("5. User Manage");
        System.out.println("6. Log out");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                formShowCarList();
                break;
            case 2:
                formCreateCar();
                break;
            case 3:
                formDeleteCar();
                break;
            case 4:
                formEditCar();
                break;
            case 5:
                formUserManage();
                break;
            case 6:
                userController.logout();
                new ViewMenu().menu();
                break;
        }
        menuAdmin();
    }

    private void formEditCar() {
        System.out.println("Nhập vào id để sửa: ");
        int idCar = Config.scanner().nextInt();
        if (carController.detailCar(idCar) == null) {
            System.out.println("Không tồn tại");
        } else {
            Car car = carController.detailCar(idCar);
            System.out.println("OLD CAR COMPANY NAME: " + car.getCarCompany());
            System.out.println("OLD NAME : " + car.getCarName());
            System.out.println("OLD STATUS : "+(car.isStatus() ? "New" : "Old"));
            System.out.println("OLD PRICE: " + car.getPrice());
            System.out.println("ENTER NEW COMPANY NAME: ");
            String newCompanyName = Config.scanner().nextLine();
            if (newCompanyName.trim().equals("")) {
                newCompanyName = car.getCarCompany();
            }
            System.out.println("ENTER NEW CAR NAME: ");
            String newCarName = Config.scanner().nextLine();
            if (newCarName.trim().equals("")) {
                newCarName = car.getCarName();
            }
            System.out.println("ENTER NEW CAR STATUS: ");
            String newStatus = Config.scanner().nextLine();
            if (newStatus.trim().equals("")){
                newStatus = (car.isStatus() ? "New" : "Old");
            }
            System.out.println("ENTER NEW PRICE: ");
            String newPrice = Config.scanner().nextLine();
            if (newPrice.trim().equals("") ){
                newPrice = String.valueOf(car.getPrice());
            }
            Car newCar = new Car( newCompanyName, newCarName,newStatus, newPrice);
            carController.editCar(idCar, newCar);
            System.out.println("Edit success!!");
            carController.showListCar();

        }
        new Back();
    }

    private void formDeleteCar() {
        System.out.println("INSERT CAR'S NAME TO DELETE: ");
        int idCar = Config.scanner().nextInt();
        if (carController.detailCar(idCar) == null) {
            System.out.println("NOT EXIST");
        } else {
            System.out.println("1 FOR YES/ 2 FOR NO");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete) {
                case 1:
                    carController.deleteById(idCar);
                    formShowCarList();
                    carController.showListCar();
                    break;
                case 2:
                    new ViewHome();
                    break;
            }
        }

    }

    private void formCreateCar() {
        System.out.println("---------CREATE CAR---------");
        while (true) {
            int idCar;
            if (carList.size() == 0) {
                idCar = 1;
            } else {
                idCar = carList.get(carList.size() - 1).getId() + 1;
            }
            System.out.println("INSERT CAR'S COMPANY");
            String carCompany = Config.scanner().nextLine();
            System.out.println("INSERT CAR's NAME: ");
            String name = Config.scanner().nextLine();
            System.out.println("INSERT CAR'S STATUS");
            boolean status = false;
            System.out.println("Y FOR NEW OR N FOR OLD?");
            String check = Config.scanner().nextLine();
            if (check.equalsIgnoreCase("y")) {
                status = true;
            } else if (check.equalsIgnoreCase("n")) {
                status = false;
            } else {
                System.out.println("INVALID CHOICE");
            }
            System.out.println("INSERT CAR's PRICE: ");
            long price = Config.scanner().nextLong();
            Car car = new Car(idCar, carCompany, name, status, price);
            carController.createCar(car);
            System.out.println("Create success!");
            carController.showListCar();
            new Back();
            break;

        }
    }

    private void formUserManage() {
        new ViewUser().viewUser();
    }

    private void formShowCarList() {
        System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s | %n", "Car id", "Company name", "Car name", "Status", "Price");
        List<Car> listSort = carController.sortByCompanyName();
        for (int i = 0; i < listSort.size(); i++) {
            int j = i + 1;
            System.out.printf("| %-10d | %-15s | %-15s | %-15s | %-15d |%n", j, listSort.get(i).getCarCompany(), listSort.get(i).getCarName(), listSort.get(i).isStatus() ? "New" : "Old", listSort.get(i).getPrice()+"$");
        }
    }
}
