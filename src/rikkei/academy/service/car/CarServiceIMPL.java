package rikkei.academy.service.car;

import rikkei.academy.config.Config;
import rikkei.academy.model.car.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CarServiceIMPL implements ICarService {
    static String PATH_CAR = "C:\\Users\\Asus\\Module2\\case-study\\Website-ban-o-to\\src\\rikkei\\academy\\database\\car.txt";
    static Config<List<Car>> config = new Config<>();
    public static List<Car> carList = config.read(PATH_CAR);

    static {
        if (carList == null) {
            carList = new ArrayList<>();
        }
    }
//    static List<Car> carList = new ArrayList<>();
//    static {
//        carList.add(new Car(1, "Toyota", "Camry", true,250000));
//        carList.add(new Car(2, "Mercedes", "G63", true,1440000));
//        carList.add(new Car(3, "Huyndai", "Accent", true,250000));
//        carList.add(new Car(4, "Toyota", "Camry2", false,130000));
//    }

    @Override
    public List<Car> findAll() {
        return carList;
    }

    @Override
    public void save(Car car) {
        carList.add(car);
        updateData();
    }

    @Override
    public void updateData() {
        config.write(PATH_CAR, carList);
    }

    @Override
    public Car findById(int id) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getId() == id) {
                return carList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < carList.size(); i++) {
            if (id == (carList.get(i).getId())) {
                carList.remove(i);
            }
        }
        updateData();
    }

    @Override
    public List<Car> sortCarList() {
        List<Car> listSort = new ArrayList<>();
        for (int i = 0; i < carList.size(); i++) {
            listSort.add(carList.get(i));
        }
        Collections.sort(listSort);
        return listSort;
    }
}

