package rikkei.academy.service.category;

import rikkei.academy.model.category.Category;
import rikkei.academy.service.IGenerateService;

import java.util.List;
import java.util.Locale;

public interface ICategoryService extends IGenerateService<Category> {
    Category findById(int id);
    void deleteById(int id);

}
