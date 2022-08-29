package rikkei.academy.model.car;

import java.io.Serializable;

public class Car implements Serializable, Comparable<Car> {
    private int id;

    private String carName;
    private String category;
    private int price;
    private String carCompany;

    public Car( String newCarName, String newCategory, String newPrice,String newCompanyName) {

        this.carName = newCarName;
        this.category = newCategory;
        this.price = Integer.parseInt((newPrice));
        this.carCompany = newCompanyName;
    }

    public Car(int id, String carName, String category, int price, String carCompany) {
        this.id = id;
        this.carName = carName;
        this.category = category;
        this.price = price;
        this.carCompany = carCompany;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCarCompany() {
        return carCompany;
    }

    public void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    @Override
    public String toString() {
        return "| " +
                "Id: " + id +
                " | Car Company: " + carName  +
                " | Car Name: " + category +
                " | Status: " + price+
                " | Price: " + carCompany + "$" +
                " | ";
    }

    @Override
    public int compareTo(Car car) {
        int temp = this.getPrice()-(car.getPrice());
        if (temp != 0) {
            return temp;
        }
        temp = this.getCarName().compareTo(car.getCarName());
        return temp;
    }
}
