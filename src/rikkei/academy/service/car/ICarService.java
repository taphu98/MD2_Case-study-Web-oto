package rikkei.academy.service.car;

import rikkei.academy.model.car.Car;
import rikkei.academy.service.IGenerateService;

import java.util.List;

public interface ICarService extends IGenerateService<Car> {
    Car findById(int id);

    void deleteById(int id);



    List<Car> sortCarList();
}
