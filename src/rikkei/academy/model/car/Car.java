package rikkei.academy.model.car;

import java.io.Serializable;

public class Car implements Serializable, Comparable<Car> {
    private int id;
    private String carCompany;
    private String carName;
    private boolean status;
    private int price;

    public Car( String newCompanyName, String newCarName, String newStatus, String newPrice) {
        this.carCompany = newCompanyName;
        this.carName = newCarName;
        this.status = Boolean.parseBoolean(newStatus);
        this.price = Integer.parseInt((newPrice));
    }

    public Car(int id, String carCompany, String carName, boolean status, int price) {
        this.id = id;
        this.carCompany = carCompany;
        this.carName = carName;
        this.status = status;
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarCompany() {
        return carCompany;
    }

    public void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "| " +
                "id: " + id +
                ", carCompany: " + carCompany + '\'' +
                ", carName: " + carName + '\'' +
                ", status: " + (status?"New" : "Old") +
                ", price: " + price + "$" +
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
