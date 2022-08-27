package rikkei.academy.service.company;

import rikkei.academy.config.Config;
import rikkei.academy.model.company.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyServiceIMPL implements ICompanyService{
    static String PATH_COMPANY = "C:\\Users\\Asus\\Module2\\case-study\\Website-ban-o-to\\src\\rikkei\\academy\\database\\company.txt";
    static Config<List<Company>> config= new Config<>();
    static List<Company> companyList = config.read(PATH_COMPANY);
    static {
        if (companyList== null){
            companyList = new ArrayList<>();
        }
    }
    @Override
    public List<Company> findAll() {
        return companyList;

    }

    @Override
    public void save(Company company) {
        companyList.add(company);
    }

    @Override
    public void updateData() {
        config.write(PATH_COMPANY,companyList);
    }

    @Override
    public Company findById(int id) {
        for (int i = 0; i < companyList.size(); i++) {
            if (id == companyList.get(i).getId()){
                return companyList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < companyList.size(); i++) {
            if (id == companyList.get(i).getId()){
                companyList.remove(i);
            }
        }
        updateData();
    }
}
