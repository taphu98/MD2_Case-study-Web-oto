package rikkei.academy.controller;

import rikkei.academy.model.car.Car;
import rikkei.academy.service.car.CarServiceIMPL;
import rikkei.academy.service.car.ICarService;

import java.util.List;

public class CarController {
    ICarService carService = new CarServiceIMPL();
    public List<Car> showListCar()  {
        return carService.findAll();
    }
    public void createCar(Car car){
        carService.save(car);
    }
    public Car detailCar(int  id){
        return carService.findById(id);
    }
    public void editCar(int id, Car newCar){
        Car car1 = carService.findById(id);
        car1.setCarCompany(newCar.getCarCompany());
        car1.setCarName(newCar.getCarName());
        car1.setCategory(newCar.getCategory());
        car1.setPrice(newCar.getPrice());
    }
    public void deleteById(int id){
        carService.deleteById(id);
    }
    public List<Car> sortByCompanyName(){
        return carService.sortCarList();
    }

}
