package rikkei.academy.view;


import rikkei.academy.config.Config;
import rikkei.academy.controller.CarController;
import rikkei.academy.model.car.Car;

import java.util.List;

public class ViewCar {
    CarController carController = new CarController();
    List<Car> carList = carController.showListCar();

    public void menuCar() {
        System.out.println("***CAR MANAGE***");
        System.out.println("1. Show car list");
        System.out.println("2. Create car");
        System.out.println("3. Show detail car");
        System.out.println("4. Update car");
        System.out.println("5. Delete car");
        System.out.println("6. Back");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                formShowCarList();
                break;
            case 2:
                formCreateCar();
                break;
            case 3:
                formShowDetailCar();
                break;
            case 4:
                formEditCar();
                break;
            case 5:
                formDeleteCar();
                break;
            case 6:
                new ViewHome();
                break;
        }


    }

    private void formEditCar() {
        System.out.println("ENTER CAR'S ID TO UPDATE: ");
        int idCar = Config.scanner().nextInt();
        if (carController.detailCar(idCar) == null) {
            System.out.println("NOT EXIST");
        } else {
            Car car = carController.detailCar(idCar);
            System.out.println("OLD CAR COMPANY NAME: " + car.getCarCompany());
            System.out.println("OLD NAME : " + car.getCarName());
            System.out.println("OLD STATUS : " + (car.isStatus() ? "NEW" : "OLD"));
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
            if (newStatus.trim().equals("")) {
                newStatus = (car.isStatus() ? "NEW" : "OLD");
            }
            System.out.println("ENTER NEW PRICE: ");
            String newPrice = Config.scanner().nextLine();
            if (newPrice.trim().equals("")) {
                newPrice = String.valueOf(car.getPrice());
            }
            Car newCar = new Car(newCompanyName, newCarName, newStatus, newPrice);
            carController.editCar(idCar, newCar);
            System.out.println("Update success!!");
            carController.showListCar();

        }
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCar().menuCar();
        }
    }

    private void formDeleteCar() {
        System.out.println("ENTER CAR'S ID TO DELETE: ");
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
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCar().menuCar();
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
            System.out.println("ENTER CAR'S COMPANY");
            String carCompany = Config.scanner().nextLine();
            System.out.println("ENTER CAR's NAME: ");
            String name = Config.scanner().nextLine();
            System.out.println("ENTER CAR'S STATUS");
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
            System.out.println("ENTER CAR'S PRICE: ");
            int price = Config.scanner().nextInt();
            Car car = new Car(idCar, carCompany, name, status, price);
            carController.createCar(car);
            System.out.println("Create success!");
            carController.showListCar();
            System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new ViewCar().menuCar();
                break;
            }
        }
    }

    public void formShowDetailCar() {
        System.out.println("ENTER CAR ID: ");
        int idCar = Config.scanner().nextInt();
        if (carController.detailCar(idCar) == null) {
            System.out.println("NOT EXIST");
        } else {
            Car car = carController.detailCar(idCar);
            System.out.println(car);
        }
    }

    public void formShowCarList() {
        System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-15s | %n", "Car id", "Company name", "Car name", "Status", "Price");
        List<Car> listSort = carController.sortByCompanyName();
        for (int i = 0; i < listSort.size(); i++) {
            int j = i + 1;
            System.out.printf("| %-10d | %-15s | %-15s | %-15s | %-15s |%n", j, listSort.get(i).getCarCompany(), listSort.get(i).getCarName(), listSort.get(i).isStatus() ? "New" : "Old", listSort.get(i).getPrice() + "$");
        }
    }

}
