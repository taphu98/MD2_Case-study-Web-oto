package rikkei.academy.service.company;

import rikkei.academy.model.car.Car;
import rikkei.academy.model.company.Company;
import rikkei.academy.service.IGenerateService;

public interface ICompanyService extends IGenerateService<Company> {
    Company findById(int id);

    void deleteById(int id);

}
