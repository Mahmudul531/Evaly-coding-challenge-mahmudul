package com.example.codingtaskfromevaly.Features.Products;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.codingtaskfromevaly.Repository.ProductRepository;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Category;
import com.example.codingtaskfromevaly.Repository.RoomPersistance.entity.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    MutableLiveData<ProductModel> productModelMutableLiveData;
    ProductRepository productRepository;
    LiveData<List<Products>> allproduct;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
    }

    public void insert(Products products) {
        productRepository.insert(products);
    }

    public void deleteallproducts(String category) {
        productRepository.deleteproducts(category);
    }

    public void updateAllCategoryToLocal(boolean isFirstTimeThisCategory,String categorySlug) {
        if (isFirstTimeThisCategory)
            deleteallproducts(categorySlug);
        if (productModelMutableLiveData != null && productModelMutableLiveData.getValue() != null) {
            for (ProductModel.Results results : productModelMutableLiveData.getValue().getResults()
            ) {
                Products category = new Products(results.getName(), results.getSlug(), results.getProduct_image(), results.getPrice_type(), results.getMax_price(), results.getMin_price(), results.getMin_discounted_price(),categorySlug);
                productRepository.insert(category);
            }
            //   allcategory.getValue().addAll(categoryList);
        }


    }

    public LiveData<List<Products>> getAllproduct(String category) {
        allproduct = productRepository.getGetallproduct(category);

        return allproduct;
    }

    public MutableLiveData<ProductModel> getProductModelMutableLiveData(String category, int page, int limit) {
        productModelMutableLiveData = productRepository.getProductFromServer(category,page,limit);

        return productModelMutableLiveData;
    }
}
