package rikkei.academy.controller;

import rikkei.academy.model.company.Company;
import rikkei.academy.service.company.CompanyServiceIMPL;
import rikkei.academy.service.company.ICompanyService;

import java.util.List;

public class CompanyController {
    ICompanyService companyService = new CompanyServiceIMPL();
    public List<Company> showCompanyList(){
        return companyService.findAll();
    }
    public void createCompany(Company company){
        companyService.save(company);
    }
    public Company detailCompany(int id){
        return companyService.findById(id);
    }
    public void updateCompany(int id, Company newCompany){
        Company company = companyService.findById(id);
        company.setCompanyName(newCompany.getCompanyName());
    }
    public void deleteCompany(int id){
        companyService.deleteById(id);
    }
}
