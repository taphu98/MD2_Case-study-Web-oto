package rikkei.academy.view;

import rikkei.academy.back.Back;
import rikkei.academy.config.Config;
import rikkei.academy.controller.CompanyController;
import rikkei.academy.model.company.Company;

import java.util.List;

public class ViewCompany {
    CompanyController companyController = new CompanyController();
    List<Company> companyList = companyController.showCompanyList();

    public void menuCompany(){
        System.out.println("-----MENU COMPANY-----");
        System.out.println("1. Show company list");
        System.out.println("2. Create company");
        System.out.println("3. Update company");
        System.out.println("4. Delete company");
        System.out.println("5. Back");
        int choice = Config.scanner().nextInt();
        switch (choice){
            case 1:
                formShowCompanyList();
                break;
            case 2:
                formCreateCompany();
                break;
            case 3:
                formUpdateCompany();
                break;
            case 4:
                formDeleteCompany();
                break;
            case 5:
                Back.backHome();
                break;
        }
    }

    private void formDeleteCompany() {
        System.out.println("ENTER COMPANY ID TO DELETE: ");
        int idCompany = Config.scanner().nextInt();
        if (companyController.detailCompany(idCompany) == null){
            System.out.println("NOT EXIST");
        }else {
            System.out.println("1 FOR YES / 2 FOR NO");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete){
                case 1:
                    companyController.deleteCompany(idCompany);
                    formShowCompanyList();
                    break;
                case 2:
                    new ViewHome();
                    break;
            }
        }
        Back.backCompanyMenu();
    }

    private void formUpdateCompany() {
        System.out.println("ENTER COMPANY TO UPDATE: ");
        int idCompany = Config.scanner().nextInt();
        if (companyController.detailCompany(idCompany)==null){
            System.out.println("NOT EXIST");
        }else {
            Company company = companyController.detailCompany(idCompany);
            System.out.println("OLD COMPANY NAME: " + company.getCompanyName());
            String newCompanyName = Config.scanner().nextLine();
            Company newCompany = new Company(newCompanyName);
            companyController.updateCompany(idCompany, newCompany);
            System.out.println("Update success");
            companyController.showCompanyList();
        }
        Back.backCompanyMenu();
    }


    private void formCreateCompany() {
        System.out.println("-----CREATE COMPANY-----");
        while (true){
            int idCompany;
            if (companyList.size() == 0){
                idCompany =1;
            }else{
                idCompany = companyList.get(companyList.size()-1).getId()+1;

            }
            System.out.println("ENTER COMPANY NAME:" );
            String companyName = Config.scanner().nextLine();
            Company company = new Company(idCompany,companyName);
            companyController.createCompany(company);
            System.out.println("Create success");
            companyController.showCompanyList();
            Back.backCompanyMenu();
        }
    }

    private void formShowCompanyList() {
        System.out.printf("| %-15s | %-15s |%n", " COMPANY ID ", " COMPANY NAME");
        for (int i = 0; i < companyList.size(); i++) {
            System.out.printf("| %-15d | %-15s |%n", companyList.get(i).getId(), companyList.get(i).getCompanyName());

        }
    }

}
