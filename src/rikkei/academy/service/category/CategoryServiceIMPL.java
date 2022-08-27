package rikkei.academy.service.category;

import rikkei.academy.config.Config;
import rikkei.academy.model.car.Car;
import rikkei.academy.model.category.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CategoryServiceIMPL implements ICategoryService {
    static String PATH_CATEGORY = "C:\\Users\\Asus\\Module2\\case-study\\Website-ban-o-to\\src\\rikkei\\academy\\database\\category.txt";
    static Config<List<Category>> config = new Config<>();
    static List<Category> categoryList = config.read(PATH_CATEGORY);

    static {
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        categoryList.add(category);
        updateData();
    }

    @Override
    public void updateData() {
        config.write(PATH_CATEGORY, categoryList);
    }

    @Override
    public Category findById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id == categoryList.get(i).getId()) {
                return categoryList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id == categoryList.get(i).getId()) {
                categoryList.remove(i);
            }
        }
        updateData();
    }
}
