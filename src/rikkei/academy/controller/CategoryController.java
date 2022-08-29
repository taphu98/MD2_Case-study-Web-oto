package rikkei.academy.controller;

import rikkei.academy.model.category.Category;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;

import java.util.List;

public class CategoryController {
    ICategoryService categoryService = new CategoryServiceIMPL();

    public List<Category> showCategoryList(){
        return categoryService .findAll();
    }
    public void createCategory(Category category){
        categoryService.save(category);
    }
    public Category findCategory(int id){
        return categoryService.findById(id);
    }
    public void deleteCategory(int id){
        categoryService.deleteById(id);
    }
    public void editCategory(int id, Category newCategoryName){
        Category category1 = categoryService.findById(id);
        category1.setName(newCategoryName.getName());
    }
}
