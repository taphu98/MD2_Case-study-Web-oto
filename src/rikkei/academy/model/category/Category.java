package rikkei.academy.model.category;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable, Comparable<Category>{
    private int id;
    private String name;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String newCategoryName) {
        this.name = newCategoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Category o) {
        return this.getName().compareTo(o.getName());
    }
}
