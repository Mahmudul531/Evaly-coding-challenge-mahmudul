package com.example.codingtaskfromevaly.Features.Categories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingtaskfromevaly.Repository.CategoryRepository;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends AndroidViewModel {
    CategoryRepository productRepository;
    LiveData<List<Category>> allcategory;

    MutableLiveData<List<CategoriModel>> categoriModelMutableLiveData;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        productRepository = new CategoryRepository(application);
        allcategory = productRepository.getGetallcategory();
        categoriModelMutableLiveData = productRepository.getCategoryserver();
    }

    public void insert(Category category) {
        productRepository.insert(category);
    }

    public void deleteall() {
        productRepository.deleteCategory();
    }

    public LiveData<List<Category>> getAllcategory() {
        return allcategory;
    }

    public void updateAllCategoryToLocal() {
        deleteall();
        if (categoriModelMutableLiveData != null && categoriModelMutableLiveData.getValue() != null) {
            List<Category> categoryList = new ArrayList<>();
            for (CategoriModel catModel : categoriModelMutableLiveData.getValue()
            ) {
                Category category = new Category(catModel.getName(), catModel.getSlug(), catModel.getImage_url());
                productRepository.insert(category);
            }
         //   allcategory.getValue().addAll(categoryList);
        }


    }

    public MutableLiveData<List<CategoriModel>> getCategoriModelMutableLiveData() {

        return categoriModelMutableLiveData;
    }
}
